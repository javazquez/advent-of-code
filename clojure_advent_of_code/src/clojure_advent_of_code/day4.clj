
(ns clojure-advent-of-code.day4
  (:require [digest :refer [md5]]))

(defn find-md5-for [primer-str contraint-str]
  (let [candidates (map str (repeat primer-str) (range))]
  (->> candidates
       (map-indexed
         (fn [idx itm] [(digest/md5 itm) idx]))
       (filter #(.startsWith (first %) contraint-str))
       first
   )))
