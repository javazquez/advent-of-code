(ns clojure-advent-of-code.day19-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [clojure-advent-of-code.day19 :refer :all]))

(def str-to-trans "CRnSiRnCaPTiMgYCaPTiRnFArSiThFArCaSiThSiThPBCaCaSiRnSiRnTiTiMgArPBCaPMgYPTiRnFArFArCaSiRnBPMgArPRnCaPTiRnFArCaSiThCaCaFArPBCaCaPTiTiRnFArCaSiRnSiAlYSiThRnFArArCaSiRnBFArCaCaSiRnSiThCaCaCaFYCaPTiBCaSiThCaSiThPMgArSiRnCaPBFYCaCaFArCaCaCaCaSiThCaSiRnPRnFArPBSiThPRnFArSiRnMgArCaFYFArCaSiRnSiAlArTiTiTiTiTiTiTiRnPMgArPTiTiTiBSiRnSiAlArTiTiRnPMgArCaFYBPBPTiRnSiRnMgArSiThCaFArCaSiThFArPRnFArCaSiRnTiBSiThSiRnSiAlYCaFArPRnFArSiThCaFArCaCaSiThCaCaCaSiRnPRnCaFArFYPMgArCaPBCaPBSiRnFYPBCaFArCaSiAl")
(def complex-instructions-lines (line-seq (io/reader (clojure.java.io/resource "Day19.txt"))))
(def complex-machine-mapping (complex-machine complex-instructions-lines))
(def fabrication-machine-mapping (fabrication complex-instructions-lines))

(def simple-machine (list (list (re-pattern "H")  "HO")
        (list (re-pattern "H")  "OH")
        #_(list (re-pattern "H")  "OO")
        (list (re-pattern "O")  "HH")))

(deftest example-1
  (testing "first example for problem 19"
  (is (= 4  (count (small-hadron-collider
                      "HOH"
                      simple-machine)) ))))

(deftest example-2
  (testing "second example for problem 19"
  (is (= 7  (count (small-hadron-collider "HOHOHO" simple-machine)) ))))

(deftest solve-1
  (testing "solve problem 19"
  (is (= 518  (count (small-hadron-collider
                        str-to-trans
                        complex-machine-mapping))))))

(deftest solve-2
  (testing "solve problem 19 part 2"
  (is (= '("e" 200) (molecule-reducer str-to-trans
                                      fabrication-machine-mapping)))))
