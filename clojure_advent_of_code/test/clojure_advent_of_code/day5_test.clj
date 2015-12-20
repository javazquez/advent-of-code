(ns clojure-advent-of-code.day5-test
  (:require [clojure.test :refer :all]
            [clojure-advent-of-code.day5 :refer :all]
            [clojure.java.io :as io]))

(def day5lines (line-seq (io/reader (clojure.java.io/resource "Day5.txt"))))

(deftest example-1
  (testing "first test case given"
    (is (= (nice-string? "ugknbfddgicrmopn")) true)))

(deftest example-2
  (testing "second test case given"
    (is (= (nice-string? "aaa")) true)))

(deftest example-3
  (testing "third test case given"
    (is (= (nice-string? "jchzalrnumimnmhp")) false)))

(deftest example-4
  (testing "fourth test case given"
    (is (= (nice-string? "haegwjzuvuyypxyu")) false)))


(deftest example-5
  (testing "fourth test case given"
    (is (= (nice-string? "dvszwmarrgswjxmb")) false)))


(deftest solution-part1
  (testing "solution 1 "
    (is (= (count (filter nice-string? day5lines)) 255))))

(deftest solution-part2
  (testing "solution 2"
    (is (= (count (filter nice-string-sol2? day5lines)) 55))))
