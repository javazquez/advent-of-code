const fs = require("fs").promises;
const path = require("path");
console.log(__dirname);
const DAY1_INPUT = path.join(__dirname, "../resources/input_day1.txt");

const getData = () => {
  return fs.readFile(DAY1_INPUT);
};
const fuelHelper = function(numerator) {
  return Math.floor(parseInt(numerator / 3) - 2);
};

const solveDay1 = function(body) {
  let temp = body.map(x => fuelHelper(x));
  const reducer = (acc, val) => acc + val;
  return temp.reduce(reducer);
};

const calculateFuel = function(fuelAmt) {
  let remainingFuel = fuelHelper(fuelAmt);
  let totalFuel = 0;
  while (remainingFuel > 0) {
    totalFuel += remainingFuel;
    remainingFuel = fuelHelper(remainingFuel);
  }
  return totalFuel;
};

const solveDay2 = function(body) {
  let fuelList = body.map(x => calculateFuel(x));
  const reducer = (acc, val) => acc + val;
  return fuelList.reduce(reducer);
};

module.exports = { getData, solveDay1, solveDay2 };
