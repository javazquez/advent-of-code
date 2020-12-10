import * as fs from 'fs'

const fileArray: any = fs.readFileSync('resources/day07_input.txt', 'utf-8')
    .replace(/\s(bags contain)\s*|(bag)(s)?,?\s*|,\s/g, '')
    .split(/.\n/)
    .map(x => x.split(/(?=\d)/g))

//['container bag # first bag coloer', second bag string, etc]
let globaltree = new Map()
let containsShinyGoldBags: string[] = []

const createTreeAndReturncontainsShinyGoldBags = ([k, ...rest]) => {
    let maps: any = []
    for (const bag of rest) {
        let amount = bag[0]
        const bagType = bag.slice(1).trim()
        if (bagType == 'shiny gold') {
            containsShinyGoldBags.push(k)
        }
        maps.push(new Map([[bagType, amount]]))
    }
    if (maps.length !== 0) {
        maps = maps.reduce((acc, cur) => new Map([...acc, ...cur]))
        globaltree.set(k, maps)
    }
}

fileArray.map(createTreeAndReturncontainsShinyGoldBags)

let acc = new Set(containsShinyGoldBags)

let containerBags = (bag: string): string[] => {
    let outerBags = []
    for (const containerBag of globaltree.keys()) {
        if (globaltree.get(containerBag).get(bag) !== undefined) {
            outerBags = outerBags.concat(containerBag)
        }
    }
    return outerBags
}

while (containsShinyGoldBags.length > 0) {
    let tempBags: any = []
    for (const bag of containsShinyGoldBags) {
        let outerBags = containerBags(bag)
        if (outerBags.length > 0) {
            tempBags = tempBags.concat(outerBags)
            outerBags.map(item => acc.add(item));
        }
    }
    containsShinyGoldBags = tempBags
}

//Solve Part 1
console.log(acc.size); // 185

// Solve Part 2
let part2tree = new Map()

for (const [parentColor, ...rest] of fileArray) {
    part2tree = new Map([[parentColor, rest], ...part2tree])
}

const p2 = (clr: string, mult = 1): number => {
    let total = 0
    const node = part2tree.get(clr)
    if (node !== undefined) {
        for (const vals of node) {
            let [n, ...bagColor] = vals
            total += mult * parseInt(n) + p2(bagColor.join('').trim(), n * mult)
        }
    }
    return total
}

console.log(p2('shiny gold')); // 89084
