(ns clojure-advent-of-code.day10
    (:require [clojure.string :as clstr]))

(defn look-and-say [input-str]
  (clstr/join
    (map
        #(str (count (first %)) (ffirst %))
        (re-seq #"(\d)\1+|\d" input-str))))

(def sol-list (iterate look-and-say "3113322113"))

(def solution-part1 (count  (nth  (take 41 sol-list) 40)))

(def solution-part2 (count  (nth  (take 51 sol-list) 50)))
