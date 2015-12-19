(ns clojure-advent-of-code.day3-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [clojure-advent-of-code.day3 :refer :all]
            [clojure.string :as clstr]))

(def day3text (clstr/trim (slurp
  (.getFile (clojure.java.io/resource "Day3.txt")))))

(deftest deliver-2-houses
  (testing "move right is 2 houses"
    (is (= (at-least-1-pres ">") 2))))

(deftest deliver-4-houses
  (testing "move right is 4 houses"
    (is (= (at-least-1-pres  ">>>") 4))))

(deftest square-delivery
  (testing "testing square delievery"
    (is (= (at-least-1-pres  "^>v<") 4))
    (is (= (at-least-1-pres  "^v^v^v^v^v")2))))

(deftest robo-santa
  (testing "robo santa deliveries"
    (is (= (santas-robotic-helper  "^v")3))
    (is (= (santas-robotic-helper  "^>v<")3))
    (is (= (santas-robotic-helper  "^v^v^v^v^v") 11))))

(deftest solution-part1
  (testing "solution 1 string directions"
    (is (= (at-least-1-pres  day3text) 2565))))

(deftest solution-part2
  (testing "solution 2 with robotic helper"
    (is (= (santas-robotic-helper  day3text) 2639))))
