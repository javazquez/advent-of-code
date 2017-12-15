package com.javazquez.adventofcode

import spock.lang.*

class Day12Spec extends Specification {
    @Shared
    def input
    @Shared
    Day12 day12

    def setupSpec(){
        input = this.getClass().getResource( '/Day12input.txt' ).text
            .split("\n")
            .collect{ it.split(",").collect{ it.trim() }}
        day12 = new Day12(input)
    }

    def "solve part 1"(){
        when: "I look for groups associated with 0"
          def res = day12.getDeps("0",input).size()

        then: "I get the result of 152"
          res == 152
    }

    def "solve part 2"(){
        expect:
            day12.findGroups(input) == 186
    }
}
