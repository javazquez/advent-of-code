package com.javazquez.adventofcode

class Day12 {

    def table =[:]
    public Day12(input){
        input.each{
            table[it[0]] = it[1..-1]}//create mapping
    }

    def getDeps(seed,input){
        def acc = [].toSet()
        table[seed].each{ acc += it}
        input.size().times{
            acc.each{ table[it].each{acc += it}
            }
        }
        acc
    }

    def findGroups(input){
        def cands = table.keySet()
        int counter = 0
        def alreadySeen = [].toSet()
        for(int it =0; it< 2000; it++){
            if(!alreadySeen.contains(it.toString())){
                def deps =  getDeps(it.toString(), input)
                alreadySeen +=  deps
                counter +=1
            }
        }
        counter
    }
}

