(ns clojure-advent-of-code.day17-test
  (:require [clojure.test :refer :all]
            [clojure-advent-of-code.day17 :refer :all]))

(def containers (list 3 4 \a 6 9 10 17 \b 21 27 31 34 36 38 41 43 44 \c 46 47))

(deftest solution-1
  (testing "solution for 1"
  (is (= 1638 (solve-part-1 containers)))))

(deftest solution-2
  (testing "solution for 2 "
  (is (= 17 (solve-part-2 containers)))))
