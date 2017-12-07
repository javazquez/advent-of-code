(ns clojure-advent-of-code-2017.day06-test
  (:require  [clojure.test :refer :all]
             [clojure-advent-of-code-2017.day06 :refer :all]))

(def input [5	1	10	0	1	7	13	14	3	12	8	10	7	12	0	6])

(deftest examples
  (testing "4 wide memory blocks"
    (is (= [2 4 1 2]
           (balance-memory-blocks [0 2 7 0])))
    (is (= [3 1 2 3]
           (balance-memory-blocks [2 4 1 2])))
    (is (= [0 2 3 4 ]
           (balance-memory-blocks [3 1 2 3])))
    (is (= [1 3 4 1]
           (balance-memory-blocks [0 2 3 4])))
    (is (= [2 4 1 2]
           (balance-memory-blocks [1 3 4 1])))))

(deftest solve-a-and-b
  (testing "The Input"
    (is (= 5042
           (->> (solve input)
                :cycle-count)))
    (is (= 1086
           (->> (solve input)
                :memory
                solve
                :cycle-count)))))
