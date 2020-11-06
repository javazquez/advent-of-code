(ns clojure-advent-of-code.day8-test
  (:require [clojure-advent-of-code.day8 :refer :all]
            [clojure.test :refer :all]
            [clojure.java.io :as io]))

(def day8-lines (line-seq (io/reader (io/resource "Day8.txt"))))
(def day8-test (line-seq (io/reader (io/resource "Day8test.txt"))))

(deftest solve-example-part1
  (testing "testing part 1 using example"
    (is (= 12
           (- (count-code-chars day8-test)
              (count-in-memory-chars day8-test))))))

(deftest solve-example-part2
  (testing "testing part2 using examples"
    (is (= 19
           (- (reduce + (map #(count (encode-str %)) day8-test))
              (count-code-chars day8-test))))))

(deftest solve-part1
  (testing "testing part 1 using puzzle data"
    (is (= 1371
           (- (count-code-chars day8-lines)
              (count-in-memory-chars day8-lines))))))

(deftest solve-part2
  (testing "testing part 2 using puzzle data"
    (is (= 2117
           (- (reduce + (map #(count ( encode-str %)) day8-lines))
              (count-code-chars day8-lines))))))


