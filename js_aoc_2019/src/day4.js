//password gen
//at least one double
const start = 256310;
const end = 732736;
let solutionSet = new Set();
let solutionSet2 = new Set();

const findRepeatingDigits = function(pwd) {
  return pwd.match(/([0-9])\1+/g);
};

const findRepeatingDigitsPart2 = function(pwd) {
  const allMatches = pwd.match(/([0-9])\1+/g);
  if (allMatches) {
    return allMatches.find(el => el.length === 2);
  }
  return allMatches;
};

const isIncreasingDigits = function(pwd) {
  let chars = [...pwd];
  let isIncreasing = true;
  for (let index = 0; index < chars.length - 1; index++) {
    const element = chars[index];
    if (element > chars[index + 1] || !isIncreasing) {
      isIncreasing = false;
    }
  }
  return isIncreasing;
};

for (let i = start; i <= end; i++) {
  if (findRepeatingDigits(i.toString())) {
    solutionSet.add(i);
  }
  if (findRepeatingDigitsPart2(i.toString())) {
    solutionSet2.add(i);
  }
}

let counter = 0;
solutionSet.forEach(element => {
  if (isIncreasingDigits(element.toString())) {
    counter++;
  }
});

console.log("part 1", counter);
counter = 0;
solutionSet2.forEach(element => {
  if (isIncreasingDigits(element.toString())) {
    counter++;
  }
});
console.log("part 2", counter);
