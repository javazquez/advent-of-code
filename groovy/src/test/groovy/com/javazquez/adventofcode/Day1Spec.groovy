package com.javazquez.adventofcode

import spock.lang.*

class Day1Spec extends Specification{
  @Shared
  String floorText
  Day1 day1 = new Day1()

  def setupSpec(){
    floorText = this.getClass().getResource( '/Day1.txt' ).text.trim()
  }

  def "calculateFloor example returns the expect value"(){
    when:"I pass the string to the calculateFloor method"
      int resultingFloor = day1.calculateFloor(floorText)

    then:
     resultingFloor == 280
  }

  def "findPositionOfBasement retrieves the correct position"(){
    expect:
       def res = day1.findPositionOfBasement(floorText)
       res == 1797
  }
}
