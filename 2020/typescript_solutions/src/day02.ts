import * as fs from "fs";

let validPwds1 = 0
let validPwds2 = 0

fs.readFile("resources/day02_input.txt", (err, data) => {
    if (err) { throw err }
    let puzzleArray = data.toString().split("\n")
    for (const i in puzzleArray) {
        let [minMax, letter, pwd] = puzzleArray[i].split(/\s/)
        let [mn, mx]: any = minMax.split("-")
        let l = letter?.replace(":", "")
        const regex = new RegExp(l, "g")
        let count = pwd?.match(regex)?.length
        let mnchar = pwd[mn - 1]
        let mxchar = pwd[mx - 1]
        if (count !== undefined && mn <= count && count <= mx) {
            validPwds1 += 1
        }
        if (mnchar !== mxchar && (mnchar === l || mxchar === l)) {
            validPwds2 += 1
        }
    }
    //output answers part1 = 474 745
    console.log("part1 -> " + validPwds1, ", part2 -> " + validPwds2);

})