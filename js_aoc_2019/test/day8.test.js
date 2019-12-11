const fs = require("fs");
const path = require("path");

const INPUT_FILE = path.join(__dirname, "../resources/input_day8.txt");
const { breakIntoImageData, createImage, chunk } = require("../src/day8");

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

test("Day 2 , secret message", done => {
  fs.readFile(INPUT_FILE, (err, data) => {
    let layers = chunk(data.toString(), 25 * 6);
    let img = createImage(layers);
    const part2 = chunk(img, 25).map(x => {
      return x
        .toString()
        .replace(/0/g, " ")
        .replace(/1/g, "|");
    });
    const ans = [
      "|  |  ||  |||   ||  |||| ",
      "|  | |  | |  | |  | |    ",
      "|||| |    |||  |    |||  ",
      "|  | | || |  | |    |    ",
      "|  | |  | |  | |  | |    ",
      "|  |  ||| |||   ||  |    "
    ];
    expect(part2).toStrictEqual(ans);
    done();
  });
});
