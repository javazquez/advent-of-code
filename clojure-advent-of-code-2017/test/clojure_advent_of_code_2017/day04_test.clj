(ns clojure-advent-of-code-2017.day04-test
  (:require  [clojure.test :refer :all]
             [clojure.java.io :as io]
             [clojure-advent-of-code-2017.day04 :refer :all ]))

(def input-lines (line-seq (io/reader (io/resource "Day04input.txt"))))

(deftest examples
  (testing "all of the examples"
    (is (valid-passphrase? "aa bb cc dd ee"))
    (is (not (valid-passphrase? "aa bb cc dd aa")))
    (is (valid-passphrase? "aa bb cc dd aaa"))))

(deftest problem-4a
  "testing input"
  (is (= 325
         (count (filter valid-passphrase? input-lines)))))

(deftest problem-4b
  "testing input"
  (is (= 119
         (count (filter anagram? input-lines)))))
