module.exports = {
  preset: 'ts-jest',
    testEnvironment: 'node',
    roots: ['<rootDir>/test'],
    testPathIgnorePatterns:[
        "/node_modules/"
    ],
    testMatch: [
    "**/__tests__/**/*.+(ts|tsx|js)",
    "**/?(*.)+(spec|test).+(ts|tsx|js)"
  ],
    transform: {
        '^.+\\.tsx?$': 'ts-jest'
    },
    moduleDirectories: ['node_modules', 'src']
};
