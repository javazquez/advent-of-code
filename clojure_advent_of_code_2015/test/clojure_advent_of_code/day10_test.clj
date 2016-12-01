(ns clojure-advent-of-code.day10-test
  (:require [clojure.test :refer :all]
            [clojure-advent-of-code.day10 :refer :all]))

(deftest example-1
  (testing "1st example for problem 10"
  (is (= "11" (look-and-say "1" )))))

(deftest example-2
  (testing "second example for problem 10"
  (is (= "21" (look-and-say "11" )))))

(deftest example-3
  (testing "3rd example for problem 10"
    (is (= "1211" (look-and-say "21" )))))

(deftest example-4
  (testing "4th example for problem 10"
  (is (= "111221"  (look-and-say "1211")))))

(deftest solution-part1-test
  (testing "solution 1"
  (is (= 329356 solution-part1 ))))

(deftest solution-part1-test
  (testing "solution 2"
  (is (= 4666278 solution-part2 ))))
