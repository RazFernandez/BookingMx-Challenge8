/**
 * cities: [{ id, name, lat, lon }]
 * edges:  [{ fromId, toId, distanceKm }]
 */

export function validateData(cities, edges) {
    if (!Array.isArray(cities) || !Array.isArray(edges)) {
        throw new TypeError('cities and edges must be arrays');
    }
    const ids = new Set();
    for (const c of cities) {
        if (!c?.id || !c?.name) throw new Error('City must have id and name');
        if (ids.has(c.id)) throw new Error(`Duplicated city id: ${c.id}`);
        ids.add(c.id);
    }
    for (const e of edges) {
        if (e.distanceKm == null || e.distanceKm <= 0) {
            throw new Error('Edge must have positive distanceKm');
        }
        if (!ids.has(e.fromId) || !ids.has(e.toId)) {
            throw new Error('Edge references missing city id');
        }
    }
    return true;
}

export function buildAdjacency(edges) {
    /** returns Map<id, Array<{toId, distanceKm}>> */
    const adj = new Map();
    for (const e of edges) {
        if (!adj.has(e.fromId)) adj.set(e.fromId, []);
        if (!adj.has(e.toId)) adj.set(e.toId, []);
        adj.get(e.fromId).push({ toId: e.toId, distanceKm: e.distanceKm });
        adj.get(e.toId).push({ toId: e.fromId, distanceKm: e.distanceKm }); // no dirigido
    }
    return adj;
}

export function nearbyWithinRadius(sourceId, radiusKm, edges) {
    if (radiusKm <= 0) return [];
    const adj = buildAdjacency(edges);
    const list = [];
    for (const edge of (adj.get(sourceId) ?? [])) {
        if (edge.distanceKm <= radiusKm) list.push(edge.toId);
    }
    return Array.from(new Set(list));
}

export function formatGraphForSvg(cities, edges) {
    // Proyección mínima a plano (lat/lon → x/y) normalizada
    const lats = cities.map(c => c.lat), lons = cities.map(c => c.lon);
    const minLat = Math.min(...lats), maxLat = Math.max(...lats);
    const minLon = Math.min(...lons), maxLon = Math.max(...lons);
    const nx = v => (maxLon === minLon) ? 50 : ((v - minLon) / (maxLon - minLon)) * 600 + 50;
    const ny = v => (maxLat === minLat) ? 50 : (1 - (v - minLat) / (maxLat - minLat)) * 400 + 50;

    const nodes = cities.map(c => ({
        id: c.id, name: c.name, x: Math.round(nx(c.lon)), y: Math.round(ny(c.lat))
    }));
    const nodeById = new Map(nodes.map(n => [n.id, n]));
    const links = edges.map(e => ({
        x1: nodeById.get(e.fromId).x, y1: nodeById.get(e.fromId).y,
        x2: nodeById.get(e.toId).x, y2: nodeById.get(e.toId).y,
        distanceKm: e.distanceKm
    }));
    return { nodes, links };
}