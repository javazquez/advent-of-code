const { solveDay1, solveDay2, getData } = require("../src/day1.js");

test("Day1 and Day2 Answers", done => {
  getData()
    .then(data => {
      expect(solveDay1(data.toString().split(/\n/))).toBe(3520097);
      expect(solveDay2(data.toString().split(/\n/))).toBe(5277255);
      done();
    })
    .catch(e => {
      console.error(e);
    });
});
