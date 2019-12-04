const fs = require("fs").promises;
const data = require("../resources/input_day2");

const readOPCode = function(input) {
  let idx = 0;
  let posA = 1;
  let posB = 2;
  let replacePos = 3;
  while (parseInt(input[idx]) !== 99 && idx < 10000) {
    let sentinal = parseInt(input[idx]);
    let x = parseInt(input[input[posA]]);
    let y = parseInt(input[input[posB]]);
    if (sentinal === 1) {
      input[input[replacePos]] = x + y;
    } else if (sentinal === 2) {
      input[input[replacePos]] = x * y;
    } else {
      throw new Error("unknown opcode");
    }
    idx += 4;
    posA = 1 + idx;
    posB = 2 + idx;
    replacePos = 3 + idx;
  }
  return input;
};

function solveDay1(repA = 12, repB = 2) {
  let t = data.toString().split(/,/);
  t[1] = repA;
  t[2] = repB;
  return readOPCode(t)[0];
}

function solveDay2(output) {
  for (const verb of Array(100).keys()) {
    for (const noun of Array(100).keys()) {
      const result = solveDay1(noun, verb);
      if (result === output) {
        return 100 * noun + verb;
      }
    }
  }
}

module.exports = { solveDay1, solveDay2 };
