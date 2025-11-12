module.exports = {
    testEnvironment: 'jsdom',
    transform: { '^.+\\.m?js$': 'babel-jest' },
    collectCoverage: true,
    collectCoverageFrom: ['src/lib/graph/**/*.js', '!src/**/__tests__/**'],
    coverageReporters: ['text', 'lcov', 'html'],
    coverageThreshold: {
        global: { lines: 0.90, statements: 0.90, functions: 0.90, branches: 0.80 }
    }
};