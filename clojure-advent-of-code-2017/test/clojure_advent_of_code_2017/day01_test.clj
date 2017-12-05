(ns clojure-advent-of-code-2017.day01-test
  (:require  [clojure.test :refer :all]
             [clojure.java.io :as io]
             [clojure-advent-of-code-2017.day01 :refer :all]))

(def input-lines (line-seq (io/reader (io/resource "Day01input.txt"))))

(deftest day1-examples
  (testing " all the xamples" 
    (is (= 3
           (solve-captcha "1122")))
    (is (= 4
           (solve-captcha "1111")))
    (is (= 0
           (solve-captcha "1234")))
    (is (= 9
           (solve-captcha "91212129")))))

(deftest day1
  (testing "Part a and b"
    (is (= 1251
           (solve-captcha (first input-lines))))
    (is (= 1244
           (part-b (first input-lines))))))
