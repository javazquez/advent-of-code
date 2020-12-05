//https://adventofcode.com/2020/day/1

const puzzle = require('../resources/day01_input.js');

let solvepart1 = () => {
  for (let j = 0; j < puzzle.input.length - 1; j++) {
    for (let k = 1; k < puzzle.input.length; k++)
      if (puzzle.input[j] + puzzle.input[k] === 2020) {
        return puzzle.input[j] * puzzle.input[k]
      }
  }
}

let solvepart2 = () => {
  for (let i = 0; i < puzzle.input.length - 3; i++) {
    for (let j = 1; j < puzzle.input.length - 2; j++) {
      for (let k = 2; k < puzzle.input.length - 1; k++)
        if (puzzle.input[i] + puzzle.input[j] + puzzle.input[k] === 2020) {
          return puzzle.input[i] * puzzle.input[j] * puzzle.input[k]
        }
    }
  }
}

export {
  solvepart1 // 866436
  , solvepart2
} // 276650720

