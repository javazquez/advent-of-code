(ns clojure-advent-of-code.day9-test
  (:require [clojure-advent-of-code.day9 :refer :all]
            [clojure.test :refer :all]
            [clojure.java.io :as io]))

(deftest example
  (testing "Solve for example"
    (is (= 605
           (->> (visit-all-places ['London 'Belfast 'Dublin])
                (map #(mapcat (fn [city-pair]
                                (find-distances-example city-pair)) %))
                (map (partial reduce +))
                (apply min))))))

(deftest solve-part-1
  (is (= 251
         (->> (visit-all-places santa-cities)
              (map #(mapcat (fn [city-pair]
                              (find-puzzle-distances city-pair)) %))
              (map (partial reduce +))
              (apply min)))))

(deftest solve-part-2
  (is (= 898
         (->> (visit-all-places santa-cities)
              (map #(mapcat (fn [city-pair]
                              (find-puzzle-distances city-pair)) %))
              (map (partial reduce +))
              (apply max)))))

