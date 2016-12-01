(ns clojure-advent-of-code.day19
  (:require [clojure.string :as clstr] ))

; returns list of (list (re-pattern %) replacement-text)
; ex -> ((#"Al" "ThF") (#"Al" "ThRnFAr") (#"B" "BCa") ...)
(defn complex-machine [lines]
  (->> (map #(clstr/split % #"\s+=>\s+")
            lines)
       (map #(list (re-pattern (first %)) (second %) ))))

; returns list of (list (re-pattern %) replacement-text)
; where it is sorted by the largest pattern first
 (defn fabrication [lines]
   (->> (map #(clstr/split % #"\s+=>\s+")
             lines)
        (sort #(> (count (second %1)) (count (second %2))))
        (map #(list (re-pattern (second %)) (first %) ))))


; tokenize str-input and apply the transformations on each token while appending
; the rest of the str-input to the new tranformation. shove into a set and remove
; the original str-input
(defn small-hadron-collider [str-input machine]
  (loop [[h & tail] (re-seq #"[A-Z][a-z]|[A-Z]|\d" str-input)
         acc '()
         hacc ""]
  (if (nil? h)
    (disj (into #{} acc) str-input)
    (recur
      tail
      (concat (map
                  (fn [[x y]]
                            (str hacc
                                (clstr/replace h x y)
                                (reduce str tail)))
                  machine)
               acc)
      (str hacc h)))))

; Using the biggest matching pattern in the machine, count how many
; replacements(via re-seq) can be found and return that as the accumulated
; sum along with the string transformation
(defn helper [str-input machine]
  (loop [[[h1 h2] & t] machine
         res-string str-input
         acc 0 ]
    (if (or (> acc 0 ) (nil? h1))
        (list res-string acc)
      (recur
        t
        (clstr/replace res-string h1 h2)
        (+ (count (re-seq h1 res-string))acc)))))

(defn molecule-reducer [str-input machine]
  (loop [ str-res str-input
          acc 0 ]
    (let [[tmp-res tmpacc] (helper str-res machine)]
      (if (= str-res "e")
        (list str-res acc)
        (recur tmp-res (+ acc tmpacc))))))
