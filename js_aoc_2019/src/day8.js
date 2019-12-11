const breakIntoImageData = function(input, w, h) {
  const dataSize = w * h;
  const regex = new RegExp(`\\d{${dataSize}}`, "g");
  return input.match(regex);
};

//x and y can be 0,1,2
const combine = (x, y) => (x === "2" ? y : x);

/**
 * Combine two arrays by equiv idx's
 * @param {an array} ary1
 * @param {an array} ary2
 */
const zip = function(ary1 = new Array(), ary2 = new Array()) {
  let builder = "";
  for (let idx = 0; idx < ary1.length; idx++) {
    let combo = combine(ary1[idx], ary2[idx]);
    builder += combo;
  }
  return builder;
};

// Wish chunking/partitioning was a builtin feature,
// thanks https://www.w3resource.com/javascript-exercises/fundamental/javascript-fundamental-exercise-265.php
const chunk = (arr, size) =>
  Array.from({ length: Math.ceil(arr.length / size) }, (v, i) =>
    arr.slice(i * size, i * size + size)
  );

//data is the array of array strings
const createImage = function(layers) {
  let builder = layers.reduce(zip);
  return builder;
};

module.exports = { breakIntoImageData, zip, createImage, chunk };
