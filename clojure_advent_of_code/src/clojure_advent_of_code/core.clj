(ns clojure-advent-of-code.core)

(defn parens-to-up-down [string-paren]
  (if (= "(" (str string-paren)) 1 -1))

(defn calculate-floor [floorText]
  (->> (map parens-to-up-down floorText)
       (reduce +)))

(defn find-1st-basement [floor-text]
  (->> (map parens-to-up-down floor-text)
       (reductions + 0 )
       (take-while #(not= -1 %))
       count))
