(ns clojure-advent-of-code.day2
  (:require [clojure.string :as clstr]))


(defn surface-area [ l w h ]
   (->> [[ 2 l w], [2 w h], [2 h l]]
        (map #(apply * %))
        (reduce +)))

(defn str-to-coll [lxwxh-str]
  (map read-string(clstr/split lxwxh-str #"x")))

(defn total-paper-needed [lxwxh-str]
  (let [[ l w h :as dims ] (str-to-coll lxwxh-str)
         smallest-side  (reduce * (take 2 (sort dims )))
        ]
    (+ (surface-area l w h) smallest-side )))

(defn calc-ribbon [lxwxh-str]
  (let [[l w h :as dims](str-to-coll lxwxh-str)]
    (->> (take 2 (sort dims))
         (map * [2 2])
         (reduce +))))

(defn calc-bow [lxwxh-str]
  (reduce * (str-to-coll lxwxh-str)))

(defn total-ribbon-needed [lxwxh-str]
  (reduce + ((juxt calc-bow calc-ribbon) lxwxh-str)))
