(ns clojure-advent-of-code.day18-test
  (:require [clojure-advent-of-code.day18 :refer :all]
            [clojure.test :refer :all]))

(deftest day18puzzle
  (is (= 814 (get part1 "on")))
  (is (= 924 (get part2 "on"))))


