(ns clojure-advent-of-code-2017.day02-test
  (:require  [clojure.test :refer :all]
             [clojure.java.io :as io]
             [clojure-advent-of-code-2017.day02 :refer :all ]))


(def input-lines (line-seq (io/reader (io/resource "Day02input.txt"))))
(def example-spreadsheet-input (list "5 1 9 5"
                                     "7 5 3"
                                     "2 4 6 8"))
(def example-2-list (list "5 9 2 8"
                          "9 4 7 3"
                          "3 8 6 5"))

(deftest examples
  (testing "row checksums"
    (is (= 8
           (compute-row (munger "5 1 9 5"))))
    (is (= 4
           (compute-row (munger "7 5 3"))))
    (is (= 6
           (compute-row (munger "2 4 6 8"))))))

(deftest example-spreadsheet
  (testing "the example spreadsheet"
    (is (= 18
           (reduce + (map (comp compute-row munger)
                          example-spreadsheet-input))))))
(deftest part-b
  (testing "part be examples"
    (is (= 4
           (find-divisors (munger "5 9 2 8"))))
    (is (= 3
           (find-divisors (munger "9 4 7 3"))))
    (is (= 2
           (find-divisors (munger "3 8 6 5"))))
    (is (= 9
           (->> example-2-list
                (map (comp find-divisors munger))
                (filter number?)
                (reduce +))))))

(deftest problem02
  (testing "problem 2A"
    (is (= 37923
           (reduce + (map (comp compute-row munger)
                          input-lines))))
    (is (= 263
           (->> input-lines
                (map  munger)
                (map find-divisors)
                (filter number?)
                (reduce +))))))
