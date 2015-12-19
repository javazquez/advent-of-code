(ns clojure-advent-of-code.day3
  (:require [clojure.string :as clstr]))

(defn move [lst1 lst2]
  (let [[ x1 y1  ] lst1
        [ x2 y2  ] lst2
        ]
      (vec (list (+ x1 x2) (+ y1 y2)))))

(def direction-map  {
    \^ [ 0 1 ]
    \> [ 1 0 ]
    \< [-1 0 ]
    \v [ 0 -1 ]
  })

(defn all-coords[ dir-str]
  (conj
      (reductions move (map direction-map dir-str))
      [0 0] ))

(defn all-houses [ dir-str]
  (count (all-coords dir-str)))

(defn at-least-1-pres [ dir-str]
  (count (set (all-coords dir-str))))

(defn robo-str [dir-str]
  (take-nth 2 (rest dir-str)))

(defn santa-str [dir-str]
    (take-nth 2 dir-str))

(defn santas-robotic-helper [dir-str]
  (count
    (set
      (concat (all-coords (santa-str dir-str))
              (all-coords (robo-str dir-str))))))
