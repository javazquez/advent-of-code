const fs = require("fs");
const path = require("path");

const INPUT_FILE = path.join(__dirname, "../resources/input_day8.txt");
const { breakIntoImageData } = require("../src/day8");

test("Day 1, Check for Corruption", done => {
  fs.readFile(INPUT_FILE, (err, data) => {
    let imageData = breakIntoImageData(data.toString(), 25, 6);
    const answ = imageData.reduce((acc, curval) => {
      if (curval.match(/0/g).length < acc.match(/0/g).length) {
        acc = curval;
      }
      return acc;
    });
    const res = answ.match(/1/g).length * answ.match(/2/g).length;
    expect(res).toBe(2904);
    done();
  });
});
