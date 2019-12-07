const fs = require("fs");
const closestNeighbor = function(points) {
  return points.map(x => {
    const [a, b] = x.split(/,/);
    return Math.abs(parseInt(a)) + Math.abs(parseInt(b));
  });
};
//return the last point and update the accumulator
const helper = function(amt, secretsauce, acc) {
  let lastPoint = [];
  for (let i = 1; i <= amt; i++) {
    lastPoint = secretsauce(i);
    acc.push(lastPoint.toString());
  }
  return lastPoint;
};

const generateRPoints = function([x, y], moves, acc) {
  return helper(moves, i => [x + i, y], acc);
};
const generateLPoints = function([x, y], moves, acc) {
  return helper(moves, i => [x - i, y], acc);
};
const generateUPoints = function([x, y], moves, acc) {
  return helper(moves, i => [x, y + i], acc);
};
const generateDPoints = function([x, y], moves, acc) {
  return helper(moves, i => [x, y - i], acc);
};
//instructions example -> R122
const mapPoints = function(inputs, currentMapPoints = new Array()) {
  let lastPoint = [0, 0];
  let acc = new Array(lastPoint.toString());
  for (const instructions of inputs) {
    const moves = instructions.slice(1);

    if (instructions[0] === "U") {
      lastPoint = generateUPoints(lastPoint, parseInt(moves), acc);
    } else if (instructions[0] === "D") {
      lastPoint = generateDPoints(lastPoint, parseInt(moves), acc);
    } else if (instructions[0] === "L") {
      lastPoint = generateLPoints(lastPoint, parseInt(moves), acc);
    } else {
      lastPoint = generateRPoints(lastPoint, parseInt(moves), acc);
    }
  }
  return acc;
};

const numberOfStepsforPoint = function(point, coordinates) {
  return coordinates.indexOf(point);
};

module.exports = {
  generateRPoints,
  generateUPoints,
  generateLPoints,
  generateDPoints,
  mapPoints,
  closestNeighbor,
  numberOfStepsforPoint
};
