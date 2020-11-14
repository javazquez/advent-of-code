(ns clojure-advent-of-code.day13
  (:require [clojure.math.combinatorics :as combo]
            [clojure.core.logic :as l]
            [clojure.core.logic.pldb :as pldb]
            [clojure.java.io :as io]))

(def day13lines (line-seq (io/reader (clojure.java.io/resource "Day13.txt"))))

(def day13_part2_lines (line-seq (io/reader (clojure.java.io/resource "Day13_part2.txt"))))

(pldb/db-rel seating p1 p2 happiness)

(pldb/db-rel guest g)

(def all-guests
  (pldb/db [guest "alice"]
           [guest "bob"]
           [guest "carol"]
           [guest "david"]))

(def all-seating-db
  (-> all-guests
      (pldb/db-fact seating "alice" "bob" 54)
      (pldb/db-fact seating "alice" "carol" -79)
      (pldb/db-fact seating "alice" "david" -2)
      (pldb/db-fact seating "bob" "alice" 83)
      (pldb/db-fact seating "bob" "carol" -7)
      (pldb/db-fact seating "bob" "david" -63)
      (pldb/db-fact seating "carol" "alice" -62)
      (pldb/db-fact seating "carol" "bob" 60)
      (pldb/db-fact seating "carol" "david" 55)
      (pldb/db-fact seating "david" "alice" 46)
      (pldb/db-fact seating "david" "bob" -7)
      (pldb/db-fact seating "david" "carol" 41)))

(defn valid-seating
  "get the happiness scores"
  [db [p1 p2]]
  (pldb/with-db db
    (l/run* [q]
            (l/conde
             ((seating p1 p2 q))
             ((seating p2 p1 q))))))

(defn distance-between-neighbors
  "helper for valid-seating"
  [db]
  (partial valid-seating db))

(defn gen-seating
  "return list of list of seating arrangements"
  [guests]
  (map (fn [l]
         (let [[h & t] l]
           (partition 2 1 (concat l [h]))))
       (combo/permutations guests)))

(defn parse-string
  [s]
  (->>  (->  s
             (clojure.string/replace ,,,  #"\s+would gain " " +")
             (clojure.string/replace ,,,  #"\s+would lose " " -")
             (clojure.string/replace ,,, #"\s+happiness units by sitting next to " " "))
        (re-seq #"-?\w+")
        ((fn [[person happiness person2]]
           (list person person2 (read-string happiness))))))

;; Solve Part 1
(def my-data (map parse-string day13lines))
(def guests (distinct (map first my-data)))  ;;pairs of guests
(def guest-db  (apply pldb/db (map #(list guest %) guests)))

(def my-sol-db
  (reduce (fn [db [d e f]]
            (-> db
                (pldb/db-fact  seating d e f)))
          guest-db
          my-data))

;; Solve Part 2
(def my-data2 (map parse-string day13_part2_lines))
(def guests2 (distinct (map first my-data2)))
(def guest-db2  (apply pldb/db (map #(list guest %) guests2)))
(def my-sol-db2
  (reduce (fn [db [d e f]]
            (-> db
                (pldb/db-fact  seating d e f)))
          guest-db2
          my-data2))

