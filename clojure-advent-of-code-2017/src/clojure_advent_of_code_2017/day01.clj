(ns clojure-advent-of-code-2017.day01)

(defn part-a-helper
  [working-input]
  (->>  (partition 2 1 working-input)
        (filter #(= (first %)
                    (second %)))
        (map #(Character/getNumericValue (first %)))
        (reduce +)))

(defn solve-captcha
  [input]
  (let [input-size (count input)
        working-input (apply str (take (inc input-size)
                                       (cycle input)))]
    (part-a-helper working-input)))


(defn part-b
  [input]
  (let [divisor (/ (count input) 2) ]
    (->> (partition (inc divisor) 1 input )
         (filter #(= (first %)
                     (nth % divisor)))
         (map #(* 2
                  (Character/getNumericValue (first %))))
         (reduce +))))
