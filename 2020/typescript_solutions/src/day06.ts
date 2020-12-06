import * as fs from 'fs'

const fileArrray: any = fs.readFileSync('resources/day06_input.txt', 'utf-8')

const part1: any = fileArrray.replace(/\n\n/g, "DIVIDER")
  .replace(/\n/g, "")
  .split('DIVIDER')
  .map((ary: string) => new Set(ary))

const sumCounts = part1.reduce((acc: number, cur: any) => acc + cur.size, 0)

const part2: any = fileArrray.replace(/\n\n/g, "DIVIDER")
  .replace(/\n/g, " ")
  .split('DIVIDER')
  .map((ary: string) => ary.split(/\s+/g))

//part 1 => 6735
console.log(sumCounts)

let solve = part2.map((sarry: any): string[] => {
  return sarry.reduce((acc: string, cur: string): string[] => {
    return [...acc].filter(v => [...cur].includes(v))
  })
})

//part 2 => 3221
console.log(solve.reduce((acc: number, cur: any) => acc + cur.length, 0))

