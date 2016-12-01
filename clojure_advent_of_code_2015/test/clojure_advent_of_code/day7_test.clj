(ns clojure-advent-of-code.day7-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [clojure-advent-of-code.day7 :refer :all]))

(def day7-lines-a (line-seq (io/reader (clojure.java.io/resource "Day7.txt"))))
(def day7-lines-b (line-seq (io/reader (clojure.java.io/resource "Day7B.txt"))))

(def simple-circuit '("123 -> x"
  "456 -> y"
  "x AND y -> d"
  "x OR y -> e"
  "x LSHIFT 2 -> f"
  "y RSHIFT 2 -> g"
  "NOT x -> h"
  "NOT y -> i"))

(deftest simple-output
  (testing "testing simple outputs"
  (is (= {:d 72, :e 507, :f 492, :g 114, :h 65412,
          :i 65079, :x 123, :y 456}
          (find-result simple-circuit )))))

(deftest solve-part-1
  (testing "solution-1"
  (is (= 46065 ((find-result day7-lines-a) :a)))))


(deftest solve-part-2
  (testing "solution-2"
  (is (= 14134 ((find-result day7-lines-b) :a)))))

  
