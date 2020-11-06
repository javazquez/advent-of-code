(ns clojure-advent-of-code.day12-test
  (:require [clojure-advent-of-code.day12 :refer :all]
            [clojure.test :refer :all]
            [clojure.data.json :as json]
            [clojure.java.io :as io]
            [clojure.walk :as w]))

(def day12-json-str (slurp (io/resource "Day12.txt")))
(def file->json (json/read-str day12-json-str))

(deftest testing-solution
  (is (= 119433
         (->> day12-json-str
              (re-seq #"-?\d+")
              (map read-string)
              (reduce +)))))

(deftest testing-solution-2
  (is (= 68466
         (sum-of-ints (json/write-str (w/postwalk #(if (and (map? %)
                                                            (some #{"red"} (vals %)))
                                                     0
                                                     %)
                                                  file->json))))))


