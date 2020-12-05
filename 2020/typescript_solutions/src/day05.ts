import * as fs from 'fs'

const fileArray: string[] = fs.readFileSync('resources/day05_input.txt', 'utf-8').split('\n')
const parseStr = (s: string) => s.split('')

/* F is lower half, B is upper half */
const binarySplit = (letter: string, range: [number, number]): [number, number] => {
  let diff = Math.floor((range[1] - range[0]) / 2)
  let newRange: [number, number] = [0, 0]
  if (letter === 'F' || letter === 'L') {
    newRange = [range[0], range[0] + diff]
  }
  else if (letter === 'B' || letter === 'R') {
    newRange = [range[0] + diff + 1, range[1]]
  }
  return newRange
}

/*
  The first 7 characters will either be F or B; these specify exactly one of the 128 rows on the plane (numbered 0 through 127)
*/
const findSeat = (inString: string) => {
  if (inString.length === 0) return [0, 0]
  let [a, b, c, d, e, f, g, x, y, z] = parseStr(inString)
  let i = binarySplit(a, [0, 127])
  let ii = binarySplit(b, i)
  let iii = binarySplit(c, ii)
  let iv = binarySplit(d, iii)
  let v = binarySplit(e, iv)
  let vi = binarySplit(f, v)
  let vii = binarySplit(g, vi)
  let ci = binarySplit(x, [0, 7])
  let cii = binarySplit(y, ci)
  let ciii = binarySplit(z, cii)

  return [vii[0], ciii[0]]
}

let calcSeatID = (s: string): number => {
  let [r, c] = findSeat(s)
  return r * 8 + c
}

const solvePart1 = Math.max(...fileArray.map(calcSeatID))
console.log("part 1 ->", solvePart1)

// part 2
let sortedIDs = fileArray.map(calcSeatID).sort()
for (let i = 0; i < sortedIDs.length - 1; i++) {
  if (Math.abs(sortedIDs[i] - sortedIDs[i + 1]) === 2) {
    console.log("part 2 ->", sortedIDs[i] + 1)
    break
  }
}

