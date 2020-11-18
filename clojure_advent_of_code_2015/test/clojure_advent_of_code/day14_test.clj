(ns clojure-advent-of-code.day14-test
  (:require [clojure-advent-of-code.day14 :refer :all]
            [clojure.test :refer :all]))

(deftest part1
  (is (= 2660
         (->> reindeer
              (map (fn [deer]
                     (distance deer 2503)))
              (apply max))))
  )

(deftest part2
  (is (= 1256
         (->> reindeer
              (map #(new-flight-plan % 2503));; list of deers
              deer-race
              flatten
              frequencies
              vals
              (apply max)
              )))
  )




