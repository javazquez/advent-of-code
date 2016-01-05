(ns clojure-advent-of-code.day17
      (:require [clojure.math.combinatorics :as combo]))

(def decoder-ring { \a 4, \b 17,\c 44})

(defn convert-char [x]
  (cond
    (char? x) (decoder-ring x)
    :else x))

(defn my-combo [lst goal]
  (for [x (range 1 (count lst))]
    (->> (combo/combinations lst x)
    (map #(map convert-char %))
    (filter #(= goal (apply + %))))))

(defn gen-combo
  [containers]
  (apply concat (filter not-empty (my-combo containers 150))))

(defn solve-part-1 [containers]
  (count (gen-combo containers)))

(defn solve-part-2 [lst]
   (let [sol-list (apply concat (filter not-empty (my-combo lst 150)))
         min-cnt (apply min (map count  sol-list ))]
         (count (filter #(= min-cnt (count  %)) sol-list))))
