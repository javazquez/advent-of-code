(ns clojure-advent-of-code-2017.day02)

(def max-min (juxt max min))

(defn munger [input-str]
  (->> (clojure.string/split input-str #"\s+")
       (map read-string)
       sort))

(defn compute-row [input-str]
  (->> (apply max-min input-str)
       (#(- (nth % 0) (nth % 1)))))

(defn find-divisors [lst]
  (loop [[h & tail] lst
         acc 0]
    (if (and acc
             (> acc 0))
      acc
      (recur tail
             (->> tail
                  (map #(/ % h))
                  (filter #(integer? %) )
                  first)))))

