const { solveDay1, solveDay2 } = require("../src/day2");

test("solve Day 2 A", () => {
  expect(solveDay1()).toBe(5434663);
});

test("solve Day 2 B", () => {
  expect(solveDay2(19690720)).toBe(4559);
});
