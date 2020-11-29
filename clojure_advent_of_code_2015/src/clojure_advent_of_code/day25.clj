(ns clojure-advent-of-code.day25)

(defn get-index
  [row col]
  (inc (- (reduce + (range 1 (+ col row)))
          row)))

(defn code-algo
  [prev-code next]
  (rem (* prev-code 252533)
       33554393))

;;row 3010
;;col 3019

(defn gen-codes
  [row col]
  (let [solution-space (range 1 (get-index row col))]
    (reduce code-algo 20151125  solution-space)))

(gen-codes 3010 3019)
;;8997277
