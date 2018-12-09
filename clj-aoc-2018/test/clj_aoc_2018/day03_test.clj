(ns clj-aoc-2018.day03-test
  (:require  [clojure.test :refer :all]
             [clojure.java.io :as io]
             [clojure.set :as set]
             [clj-aoc-2018.Day03 :refer :all]))

(def input-lines (line-seq (io/reader (io/resource "Day03input.txt"))))



(deftest examples
  (is (= 4
         (->> ["1 @ 1,3 4x4"
               "2 @ 3,1 4x4"
               "3 @ 5,5 2x2"]
              (map tokenize)
              (map calculate-squares)
              (map :squares )
              flatten
              frequencies
              (filter #(> (val %) 1))
              count ))))


(def prob-helper (->> (map tokenize input-lines)
                      (map calculate-squares)
                      (map :squares )
                      flatten
                      frequencies
                      (filter #(> (val %) 1))
                      (map first)
                      (into #{})) )


(deftest prob-1
  (is (= 104126
         (count prob-helper) ))

  (is (= 695
         (let [claims (->> (map tokenize input-lines)
                           (map calculate-squares))]
           (->> (filter #(empty? (set/intersection
                                  prob-helper
                                  (set (:squares %))))
                        claims)
                first
                :claim )))))
