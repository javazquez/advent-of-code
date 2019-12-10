const breakIntoImageData = function(input, w, h) {
  const dataSize = w * h;
  const regex = new RegExp(`\\d{${dataSize}}`, "g");
  return input.match(regex);
};

module.exports = { breakIntoImageData };
