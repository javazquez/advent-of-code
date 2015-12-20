(ns clojure-advent-of-code.day4-test
  (:require [clojure.test :refer :all]
            [clojure-advent-of-code.day4 :refer :all]))


(deftest abcdef-md5
  (testing "first test case given"
    (is (= (second (find-md5-for "abcdef" "00000"))609043))))

(deftest pqrstuv-md5
  (testing "first test case given"
    (is (= (second (find-md5-for "pqrstuv" "00000"))1048970))))

(deftest solution-part1-md5
  (testing "first test case given"
    (is (= (second (find-md5-for "bgvyzdsv" "00000"))254575))))

(deftest solution-part2-md5
  (testing "first test case given"
    (is (= (second (find-md5-for "bgvyzdsv" "000000"))1038736))))
