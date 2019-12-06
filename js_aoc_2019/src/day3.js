const closestNeighbor = function(points) {
  //  console.log(points);
  return points.map(x => {
    const [a, b] = x.split(/,/);
    return Math.abs(parseInt(a)) + Math.abs(parseInt(b));
  });
};

const helper = function(amt, secretsauce) {
  let newmap = new Array();
  let lastPoint = [];
  for (let i = 0; i <= amt; i++) {
    newmap.push(secretsauce(i).toString());
    lastPoint = secretsauce(i);
  }
  return [newmap, lastPoint];
};

const generateRPoints = function([startX, startY], amt, acc) {
  return helper(amt, i => [startX + i, startY]);
};
const generateLPoints = function([startX, startY], amt, acc) {
  return helper(amt, i => [startX - i, startY]);
};
const generateUPoints = function([startX, startY], amt, acc) {
  return helper(amt, i => [startX, startY + i]);
};
const generateDPoints = function([startX, startY], amt, acc) {
  return helper(amt, i => [startX, startY - i]);
};

const mapPoints = function(inputs, currentMapPoints = new Array()) {
  //console.log(inputs);
  let lastPoint = [0, 0];
  let points = new Array();
  for (const instructions of inputs) {
    const [x, y] = lastPoint;
    if (instructions[0] === "U") {
      [points, lastPoint] = generateUPoints([x, y], instructions.slice(1));
    } else if (instructions[0] === "D") {
      [points, lastPoint] = generateDPoints([x, y], instructions.slice(1));
    } else if (instructions[0] === "L") {
      [points, lastPoint] = generateLPoints([x, y], instructions.slice(1));
    } else {
      [points, lastPoint] = generateRPoints([x, y], instructions.slice(1));
    }
    currentMapPoints = currentMapPoints.concat(points.slice(1)); //[...currentMapPoints, ...points.slice(1)];
  }

  return ["0,0"].concat(currentMapPoints);
};

const numberOfStepsforPoint = function(point, coordinates) {
  //   console.log("coords steps", coordinates);
  const temp = new Set(coordinates);
  return Array.from(temp).indexOf(point);
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
