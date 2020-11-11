(ns clojure-advent-of-code.day11-test
  (:require [clojure-advent-of-code.day11 :as day11]
            [clojure.test :refer :all]))
;; https://adventofcode.com/2015/day/11

(deftest valid-pwd
  (is (= true
         (day11/valid-password? (day11/password->numbers (reverse  "abcdffaa")))))
  (is (= false
         (day11/valid-password?  (day11/password->numbers "abcedccc"))))
  (is (= true
         (day11/inc-straight? (day11/password->numbers  (reverse "abcdffaa")))))
  (is (= true
         (day11/pairs? (day11/password->numbers  "abcdffaa"))))
  (is (= false
         (day11/valid-password? (day11/password->numbers  "abcdffed"))))
  (is (nil?
         (day11/inc-straight? (day11/password->numbers  (reverse "hxbxxwvv")))))
  (is (= '("hxbxxyzz" "hxcaabcc") ;;(part-1 , part-2)
         (->>
          (take 2 (day11/solve "hxbxwxba"))
;;        (take 1 (day11/solve "ghijklmn"))
;;        (take 1 (day11/solve "abcdefgh"))
          (map day11/numbers->password)
          (map reverse)
          (map #(apply str %))))))
