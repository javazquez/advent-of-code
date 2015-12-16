package com.javazquez.adventofcode

class Day1 {
  Map ops = ['(': 1, ')': -1]

  int calculateFloor(String floorText){
      floorText.inject(0){result, item -> result + ops[item]}
  }

  int findPositionOfBasement(String floorText){
      int counter = 0
      floorText.collect{ops[it]}.takeWhile { (counter += it) != -1}.size() + 1
  }
}
