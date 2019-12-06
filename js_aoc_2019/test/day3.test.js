const fs = require("fs");
const path = require("path");
const INPUT_FILE = path.join(__dirname, "../resources/input_day3.txt");

const {
  generateRPoints,
  mapPoints,
  closestNeighbor,
  numberOfStepsforPoint
} = require("../src/day3");

test("generate X coords", () => {
  expect(generateRPoints([0, 0], 15)[0].length).toBe(16);
});

test("given use case", () => {
  const inputA = ["R8", "U5", "L5", "D3"];
  const inputB = ["U7", "R6", "D4", "L4"];
  const aPoints = mapPoints(inputA);
  const bPoints = mapPoints(inputB);

  let intersection = aPoints.filter(
    x => bPoints.find(y => y === x) && x !== "0,0"
  );
  let min = Number.MAX_SAFE_INTEGER;
  intersection.forEach(el => {
    let stepsA = numberOfStepsforPoint(el, aPoints);
    let stepsB = numberOfStepsforPoint(el, bPoints);
    if (stepsA + stepsB < min) {
      min = stepsA + stepsB;
    }
  });

  expect(intersection.length).toBe(2);
  expect(Math.min(...closestNeighbor(intersection))).toBe(6);
  expect(min).toBe(30);
});

test("use test input for wires", () => {
  const testLineA = [
    "R75",
    "D30",
    "R83",
    "U83",
    "L12",
    "D49",
    "R71",
    "U7",
    "L72"
  ];
  const testLineB = ["U62", "R66", "U55", "R34", "D71", "R55", "D58", "R83"];
  const aPoints = mapPoints(testLineA);
  const bPoints = mapPoints(testLineB);

  let intersection = aPoints.filter(
    x => bPoints.find(y => y === x) && x !== "0,0"
  );
  let min = Number.MAX_SAFE_INTEGER;
  intersection.forEach(el => {
    let stepsA = numberOfStepsforPoint(el, aPoints);
    let stepsB = numberOfStepsforPoint(el, bPoints);
    if (stepsA + stepsB < min) {
      min = stepsA + stepsB;
    }
  });
  expect(Math.min(...closestNeighbor(intersection))).toBe(159);
  expect(min).toBe(610);
});

test("second input ", () => {
  const wireA = [
    "R98",
    "U47",
    "R26",
    "D63",
    "R33",
    "U87",
    "L62",
    "D20",
    "R33",
    "U53",
    "R51"
  ];
  const wireB = [
    "U98",
    "R91",
    "D20",
    "R16",
    "D67",
    "R40",
    "U7",
    "R15",
    "U6",
    "R7"
  ];
  const aPoints = mapPoints(wireA);
  const bPoints = mapPoints(wireB);
  let intersection = aPoints.filter(
    x => bPoints.find(y => y === x) && x !== "0,0"
  );

  let min = Number.MAX_SAFE_INTEGER;
  intersection.forEach(el => {
    let stepsA = numberOfStepsforPoint(el, aPoints);
    let stepsB = numberOfStepsforPoint(el, bPoints);
    let sum = stepsA + stepsB;
    if (sum < min) {
      min = stepsA + stepsB;
    }
  });
  expect(Math.min(...closestNeighbor(intersection))).toBe(135);
  expect(min).toBe(410);
});

test("Solve Day 3 A", done => {
  fs.readFile(INPUT_FILE, (err, txt) => {
    const [lineA, lineB] = txt.toString().split(/\n/);
    const aPoints = mapPoints(lineA.split(/,/));
    const bPoints = mapPoints(lineB.split(/,/));
    const intersection = aPoints.filter(
      x => bPoints.find(y => y === x) && x !== "0,0"
    );

    let min = Number.MAX_SAFE_INTEGER;
    intersection.forEach(el => {
      const stepsA = numberOfStepsforPoint(el, aPoints);
      const stepsB = numberOfStepsforPoint(el, bPoints);
      const sum = stepsA + stepsB;
      if (sum < min) {
        min = sum;
      }
      const day3Answer = Math.min(...closestNeighbor(intersection));
      expect(day3Answer).toBe(557);
    });
    done();
  });
});
