(ns clojure-advent-of-code.day24
  (:require [clojure.core.logic :as logic]
            [clojure.math.combinatorics :as combo]))

(def packages [1 3 5 11 13 17 19 23 29 31 37 41 43 47 53 59 67 71 73 79 83 89 97 101 103 107 109 113])
(def max-weight (/ (apply + packages) 3))
(def max-weight2 (/ (apply + packages) 4))

(defn equal-weight
  [[g1 g2 g3]]
  (= max-weight
     (reduce + g3)))

(->> package-groups
     (map combo/subsets)
     first
     (filter #(= max-weight (reduce + %)))
     (map #(apply *' %))
     (apply min))
;;part1 -> 10439961859
(->> package-groups
     (map combo/subsets)
     first
     (filter #(= max-weight2 (reduce + %)))
     (map #(apply *' %))
     (apply min))
;;part2 -> 72050269
