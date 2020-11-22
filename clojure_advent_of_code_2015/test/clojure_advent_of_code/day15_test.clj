(ns clojure-advent-of-code.day15-test
  (:require [clojure-advent-of-code.day15 :refer :all]
            [clojure.test :refer :all]))

(deftest solve-day15
  (is (= 13882464
         solve-part1))
  (is (= 11171160
         solve-part2)))



