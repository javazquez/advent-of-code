(ns clojure-advent-of-code.day18
  (:require [clojure.java.io :as io]))

(def day18lines (line-seq (io/reader (clojure.java.io/resource "Day18.txt"))))

(defn neighbors-on
  "return a count of neighbors that are on"
  [[r c]  board]
  (->> (list   (get  board [(dec r) (dec c)])
               (get  board [(dec r) c])
               (get  board [(dec r) (inc c)])
               (get  board [r (inc c)])
               (get  board [(inc r) (inc c)])
               (get  board [(inc r) c])
               (get  board [(inc r) (dec c)])
               (get  board [r (dec c)]))
       (filter #(= "on" %))))

(defn parse-row
  [row-value items]
  (loop [r-items items
         acc 1 ;; this is for the column, start at zero to buffer
         row-mp {}]
    (if (empty? r-items)
      row-mp
      (recur (rest r-items)
             (inc acc)
             (merge row-mp
                    (hash-map [row-value acc]
                              (if (= (first r-items) \.) "off" "on")))))))

(defn parse-all-rows
  [puzzle-rows]
  (apply merge
         (map parse-row
              (range 1 101)
              puzzle-rows)))

(defn state-for-helper
  [row-col pred]
  (if pred
    (hash-map row-col "on")
    (hash-map row-col  "off")))

(defn state-for-cur-on
  [row-col board]
  (state-for-helper row-col
                    (get #{2 3}
                         (count (neighbors-on row-col board)))))

(defn state-for-cur-off
  [row-col board]
  (state-for-helper row-col
                    (= 3
                       (count (neighbors-on row-col board)))))

(defn next-state
  " do nothing for the zero col or rows"
  [[r c :as row-col] board part2?]
  (let [cur-state (get board row-col)]
    (if (and part2?
             (contains? #{[100 1],[1 1],[1 100],[100 100]}
                        row-col))
      (hash-map row-col  cur-state)
      (if (= cur-state "on")
        (state-for-cur-on row-col board)
        (state-for-cur-off row-col board)))))

(defn update-board
  [cur-board part2?]
  (->> (map #(next-state % cur-board part2?)
            (keys cur-board))
       (apply merge)))

(def init-board (->> day18lines
                     parse-all-rows))
;;814
(def  part1 (->> (loop [acc 100
                        rec-board init-board]
                   (if (zero? acc)
                     rec-board
                     (recur (dec acc) (update-board rec-board false))))
                 vals
                 frequencies))

;;part2 update the 4 corners [0 0] [101 0] [0 101] [100 100]
(def new-board   (merge init-board {[100 1] "on", [1 1] "on", [1 100] "on", [100 100] "on"}))
;;924
(def part2 (->> (loop [acc 100
                       rec-board new-board]
                  (if (zero? acc)
                    rec-board
                    (recur (dec acc) (update-board rec-board true))))
                vals
                frequencies))

