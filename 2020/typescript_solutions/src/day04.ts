import * as fs from 'fs';

const fileArray: string[] = fs.readFileSync('resources/day04_input.txt', 'utf-8')
  .replace(/\n\n/g, " DIVIDE")
  .replace(/\n/g, ' ')
  .split(/DIVIDE/g)

const regex = /byr:\w+|iyr:\w+|eyr:\w+|hgt:\w+|hcl:#?\w+|ecl:#?\w+|pid:#?\w+/g
let parsePassports = (s: string) => s.match(regex);

//solve part 1
console.log(fileArray.map(s => parsePassports(s)?.length).filter(x => x === 7).length) //190

let validPassport = (s: string) => {
  let [k, v] = s.split(':')
  let isvalid = false
  if (k.match(/byr/g) !== null) {
    isvalid = parseInt(v) >= 1920 && parseInt(v) <= 2002
  } else if (k.match(/iyr/g) !== null) {
    isvalid = parseInt(v) >= 2010 && parseInt(v) <= 2020
  } else if (k.match(/eyr/g) !== null) {
    isvalid = parseInt(v) >= 2020 && parseInt(v) <= 2030
  } else if (k.match(/hgt/g) !== null) {
    if (v.match('cm') !== null) {
      isvalid = parseInt(v) >= 150 && parseInt(v) <= 193
    } else if (v.match('in') !== null) {
      isvalid = parseInt(v) >= 59 && parseInt(v) <= 76
    }
  } else if (k.match(/hcl/g) !== null) {
    isvalid = v.match(/^#[0-9a-f]{6}$/g) !== null
  } else if (k.match(/ecl/g) !== null) {
    isvalid = v.match(/amb|blu|brn|gry|grn|hzl|oth/g) !== null
  } else if (k.match(/pid/g) !== null) {
    isvalid = v.match(/^\d{9}$/g) !== null
  }
  return isvalid
}

const s2 = fileArray.map(s => parsePassports(s))
  ?.filter(s => s?.every(validPassport) && s.length === 7)
  .length

console.log(s2) // 121 

