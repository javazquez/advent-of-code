(ns clojure-advent-of-code.day16
  (:require [clojure.java.io :as io]
            [clojure.string :as cstr]))

(def day16lines (line-seq (io/reader (clojure.java.io/resource "Day16.txt"))))

(defn str-helper
  [in-str]
  (cstr/split in-str #"\s*:\s*"))

(defn create-map
  [[h & t]]
  (let [[s p1 v1] (str-helper h)
        xs (map str-helper t)]
    (reduce merge
            {(read-string p1) [(read-string v1) s]}
            (map (fn [[prop v]]
                   (hash-map prop [v s]))
                 (map #(map read-string %)
                      xs)))))

(def prop-map {(read-string "children") []
               (read-string "cats") []
               (read-string "samoyeds") []
               (read-string "pomeranians") []
               (read-string "akitas") []
               (read-string "vizslas") []
               (read-string "goldfish") []
               (read-string "trees") []
               (read-string "cars") []
               (read-string "perfumes") []})

(def solution-space
  (->> day16lines
       (map #(cstr/split  %  #",\s+"))
       (map create-map)
       (reduce #(merge-with conj %1 %2) prop-map)))

;;build a map where the properties own the aunts ex -> goldfish [[6 sue 1] [x sue 4]]

(defn true-for-prop?
  [prop-name comp-fn]
  (->> (get solution-space prop-name)
       (filter #(comp-fn (first %)))))

(def children (true-for-prop? (read-string "children") #(= % 3)))
(def cats (true-for-prop? (read-string "cats") #(= % 7)))
(def sams (true-for-prop? (read-string "samoyeds") #(= % 2)))
(def poms (true-for-prop? (read-string "pomeranians") #(= % 3)))
(def akitas (true-for-prop? (read-string "akitas") #(zero? %)))
(def vizslas (true-for-prop? (read-string "vizslas") #(zero? %)))
(def fish (true-for-prop? (read-string "goldfish") #(= % 5)))
(def trees (true-for-prop? (read-string "trees") #(= % 3)))
(def cars (true-for-prop? (read-string "cars") #(= % 2)))
(def perfumes (true-for-prop? (read-string "perfumes") #(= % 1)))

;;solve 1-> sue 103
(->>
 (concat children cats sams poms akitas vizslas fish trees cars perfumes)
 (map second)
 frequencies
 (sort-by val)
 reverse
 first)

;; update criteria
(def cats2 (true-for-prop? (read-string "cats") #(> % 7)))
(def trees2 (true-for-prop? (read-string "trees") #(> % 3)))
(def poms2 (true-for-prop? (read-string "pomeranians") #(< % 3)))
(def fish2 (true-for-prop? (read-string "goldfish") #(< % 5)))

;;solve 2 -> sue 405
(->>
 (concat children cats2 sams poms2 akitas vizslas fish2 trees2 cars perfumes)
 (map second)
 frequencies
 (sort-by val)
 reverse
 first)




