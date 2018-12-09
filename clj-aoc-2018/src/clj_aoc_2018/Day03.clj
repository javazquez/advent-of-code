(ns clj-aoc-2018.Day03
  (:require [clojure.set :as set]
            [clojure.string :refer [split]]))

(defn calculate-squares
  "given a claim provide all the coords"
  [{:keys [claim left top wide tall] :as record}]
  (let [x-vals (range (inc left) (inc (+ left wide)))
        y-vals (range (inc top) (inc (+ top tall)))]
    (assoc record
           :squares
           (for [x x-vals
                 y y-vals]
             (str x "," y)))))

(defn tokenize
  ""
  [s]
  (let [[claim _ lt wtall] (split s #"\s+")
        [left top] (split lt #",")
        [wide tall] (split wtall #"x")]
    {:claim (read-string claim)
     :left (read-string left)
     :top (read-string top)
     :wide (read-string wide)
     :tall (read-string tall)}))
