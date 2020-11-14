(ns clojure-advent-of-code.day13-test
  (:require [clojure-advent-of-code.day13 :refer :all]
            [clojure.test :refer :all]))

(deftest exmple
  (is (= 330
           (->> (gen-seating ["alice" "bob" "carol" "david"])
                (map #(mapcat (fn [city-pair]
                                ((distance-between-neighbors all-seating-db) city-pair)) %))
                (map (partial reduce +))
                (apply max))))

  (is (= 709
         (->> (gen-seating guests)
              (map #(mapcat (fn [city-pair]
                              ((distance-between-neighbors my-sol-db) city-pair)) %))
              (map (partial reduce +))
              (apply max))))
  ;; takes a bit to run 
  (is (= 668
         (->> (gen-seating guests2)
              (map #(mapcat (fn [city-pair]
                              ((distance-between-neighbors my-sol-db2) city-pair)) %))
              (map (partial reduce +))
              (apply max)))))
