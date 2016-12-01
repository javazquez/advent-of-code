(ns clojure-advent-of-code.day1-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [clojure-advent-of-code.day1 :refer :all]
            [clojure.string :as clstr])
            )


(def floor-text
  (clstr/trim (slurp
    (.getFile (clojure.java.io/resource "Day1.txt")))))

(deftest basement-floor
  (testing "first basement level"
    (is (= (calculate-floor ")") -1))
    (is (= (calculate-floor "())") -1))
    (is (= (calculate-floor "))(") -1))))

(deftest basement-3rd-floor
  (testing "3rd sub-basement level"
    (is (= (calculate-floor ")))") -3))
    (is (= (calculate-floor ")())())") -3))))

(deftest first-floor
  (testing "first  level"
    (is (= (calculate-floor "(") 1))))

(deftest zero-floor
  (testing "zero level"
    (is (= (calculate-floor "()") 0))
    (is (= (calculate-floor "()()") 0))))

(deftest third-floor
  (testing "zero level"
    (is (= (calculate-floor "(((") 3))
    (is (= (calculate-floor "(((()") 3))
    (is (= (calculate-floor "))(((((") 3))
    (is (= (calculate-floor "(())(((") 3))))

(deftest solution1-final-floor
  (testing "that final floor is 280"
    (is (= (calculate-floor floor-text) 280))))


(deftest solution2-find-1st-basement
  (testing "that first basement floor occurance"
    (is (= (find-1st-basement floor-text) 1797))))
