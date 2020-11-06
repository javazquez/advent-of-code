(ns clojure-advent-of-code.day12)

(defn sum-of-ints
  ""
  [in-str]
  (->> in-str
       (re-seq #"-?\d+")
       (map read-string)
       (reduce +)))
