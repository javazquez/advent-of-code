(ns clojure-advent-of-code-2017.day05-test
  (:require  [clojure.test :refer :all]
             [clojure.java.io :as io]
             [clojure-advent-of-code-2017.day05 :refer :all]))

(def input-ary (->>(io/resource "Day05input.txt")
                   (io/reader)
                   (line-seq)
                   (map read-string)
                   vec))

(deftest solve-a-and-b
  (testing "solving for a with input"
    (is (= 356945
           (jump-a input-ary)))
    (is (= 28372145
           (jump-b input-ary)))))
