(ns clojure-advent-of-code.day2-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [clojure-advent-of-code.day2 :refer :all]
            [clojure.string :as clstr])
            )

(def day2lines (line-seq (io/reader (clojure.java.io/resource "Day2.txt"))))

(deftest surface-area-func
  (testing "surface-area"
    (is (= (surface-area 2 3 4) 52))
    (is (= (surface-area  1 1 10) 42))))

(deftest total-paper
  (testing "total wrapping paper needed"
  (is (= (total-paper-needed "2x3x4") 58))
  (is (= (total-paper-needed "1x1x10") 43))))

(deftest solution-part1
  (testing "solution part 1"
  (is (= (reduce + (map total-paper-needed day2lines)) 1586300 ))))

(deftest ribbon-calc
  (testing "ribbon calculation"
  (is (= (calc-ribbon "2x3x4") 10))
  (is (= (calc-ribbon "1x1x10") 4))
  ))

(deftest bow-calc
  (testing "bow calculation"
  (is (= (calc-bow "2x3x4") 24))
  (is (= (calc-bow "1x1x10") 10))
  ))

(deftest total-ribbon
  (testing "the total ribbon needed"
  (is (= (total-ribbon-needed "2x3x4") 34))
  (is (= (total-ribbon-needed "1x1x10") 14))))

(deftest solution-part2
  (testing "solution part 2"
  (is (= (reduce + (map total-ribbon-needed day2lines)) 3737498 ))))
