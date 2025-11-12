import { validateData, buildAdjacency, nearbyWithinRadius, formatGraphForSvg } from '../citiesGraph';

const cities = [
    { id: 1, name: 'Toluca', lat: 19.28, lon: -99.65 },
    { id: 2, name: 'CDMX', lat: 19.43, lon: -99.13 },
    { id: 3, name: 'Puebla', lat: 19.04, lon: -98.21 }
];

const edges = [
    { fromId: 1, toId: 2, distanceKm: 65 },
    { fromId: 2, toId: 3, distanceKm: 130 }
];

describe('validateData', () => {
    test('ok when cities/edges are valid', () => {
        expect(validateData(cities, edges)).toBe(true);
    });

    test('fails on duplicate city ids', () => {
        const bad = [{ id: 1, name: 'A' }, { id: 1, name: 'B' }];
        expect(() => validateData(bad, [])).toThrow(/Duplicated city id/);
    });

    test('fails on negative or zero distance', () => {
        const e = [{ fromId: 1, toId: 2, distanceKm: 0 }];
        expect(() => validateData(cities, e)).toThrow(/positive/);
    });

    test('fails if edge references missing city', () => {
        const e = [{ fromId: 9, toId: 2, distanceKm: 10 }];
        expect(() => validateData(cities, e)).toThrow(/missing city/);
    });
});

describe('buildAdjacency / nearbyWithinRadius', () => {
    test('adjacency is symmetric', () => {
        const adj = buildAdjacency(edges);
        expect(adj.get(1).some(e => e.toId === 2)).toBe(true);
        expect(adj.get(2).some(e => e.toId === 1)).toBe(true);
    });

    test('nearbyWithinRadius finds neighbors inside radius', () => {
        const res = nearbyWithinRadius(2, 100, edges);
        expect(res).toEqual(expect.arrayContaining([1]));
        expect(res).not.toEqual(expect.arrayContaining([3])); // 130km > 100
    });

    test('radius <= 0 returns empty array', () => {
        expect(nearbyWithinRadius(1, 0, edges)).toEqual([]);
    });
});

describe('formatGraphForSvg', () => {
    test('returns nodes and links with projected coords', () => {
        const { nodes, links } = formatGraphForSvg(cities, edges);
        expect(nodes.length).toBe(3);
        expect(links.length).toBe(2);
        expect(nodes[0]).toHaveProperty('x');
        expect(links[0]).toHaveProperty('x1');
    });
});