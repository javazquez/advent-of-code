(ns clojure-advent-of-code.day1)

(defn find-1st-basement [floor-text]
  (->> (map {\) -1 , \( 1} floor-text)
       (reductions + 0 )
       (take-while #(not= -1 %))
       count))

(defn calculate-floor [floor-text]
  (reduce + (map {\) -1 , \( 1} floor-text)))
