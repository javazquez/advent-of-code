import * as fs from 'fs';

const fileday3rows = fs.readFileSync('resources/day03_input.txt', 'utf-8').split('\n')
let fileday3: any = []

for (const item of fileday3rows) {
  fileday3.push(item.split(''))
}

let treeFinder = (r: number, c: number): number => {
  let currentPosition = [0, 0]
  let treecnt = 0
  while (currentPosition[0] < 322) {
    currentPosition = [currentPosition[0] + r, (currentPosition[1] + c) % 31]
    if (fileday3[currentPosition[0]][currentPosition[1]] === '#') {
      treecnt++
    }
  }
  return treecnt;
}

let solvepart1 = treeFinder(1, 3)

let solvepart2 = treeFinder(1, 1) * treeFinder(1, 5) * treeFinder(1, 7) * treeFinder(2, 1) * solvepart1


export { solvepart1, solvepart2 }


//no to 52, 61
