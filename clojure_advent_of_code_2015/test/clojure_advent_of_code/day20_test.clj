(ns clojure-advent-of-code.day20-test
  (:require [clojure-advent-of-code.day20 :refer :all]
            [clojure.test :refer :all]))

(deftest solve-part1
  (is (= 831600
         (first (filter #(gte-puzzle-input? % puzzle-input)
                        (range 10000 puzzle-input))))))

;; After long brute force from above, made assumption that the house number was going to be
;; larger that part one, so starting range from part 1
(deftest solve-part2
  (is (= 884520
         (first (filter #(gte-puzzle-input2? % puzzle-input)
                        (range 831600 puzzle-input))))))
