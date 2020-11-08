(ns clojure-advent-of-code.day9
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.pldb :as pldb]
            [clojure.java.io :as io]
            [clojure.math.combinatorics :as combo]))

(pldb/db-rel location x)
(pldb/db-rel distance p1 p2 d)

(def example-city-db
  (pldb/db [location 'London]
           [location 'Dublin]
           [location 'Belfast]))

(def connected-cities-db
  (-> example-city-db
      (pldb/db-fact distance 'London 'Dublin 464)
      (pldb/db-fact distance 'London 'Belfast 518)
      (pldb/db-fact distance 'Dublin 'Belfast 141)))

(defn find-distances
  "given two cities, search db for the distance"
  [db [city1 city2]]
  (pldb/with-db db
    (l/run* [q]
      (l/conde
       ((distance city1 city2 q))
       ((distance city2 city1 q))))))

(defn distance-between-cities
  "pass the pldb db"
  [db]
  (partial find-distances db ))

(def find-distances-example
  (distance-between-cities connected-cities-db))

(defn visit-all-places
  "return list of lists containing trips that visit all destinations"
  [destinations]
  (map #(partition 2 1 %)
       (combo/permutations destinations)))

(def santa-cities ['Tristram 'AlphaCentauri 'Snowdin 'Tambi 'Faerun 'Norrath 'Straylight 'Arbre])

(defn- create-db
  "helper function to turn vector of lists into pldb entries"
  [city]
  [location city])

(def santa-city-db
  (-> (pldb/db (map create-db santa-cities))
      (pldb/db-fact distance 'Tristram  'AlphaCentauri  34)
      (pldb/db-fact distance 'Tristram  'Snowdin  100)
      (pldb/db-fact distance 'Tristram  'Tambi  63)
      (pldb/db-fact distance 'Tristram  'Faerun  108)
      (pldb/db-fact distance 'Tristram  'Norrath  111)
      (pldb/db-fact distance 'Tristram  'Straylight  89)
      (pldb/db-fact distance 'Tristram  'Arbre  132)
      (pldb/db-fact distance 'AlphaCentauri  'Snowdin  4)
      (pldb/db-fact distance 'AlphaCentauri  'Tambi  79)
      (pldb/db-fact distance 'AlphaCentauri  'Faerun  44)
      (pldb/db-fact distance 'AlphaCentauri  'Norrath  147)
      (pldb/db-fact distance 'AlphaCentauri  'Straylight  133)
      (pldb/db-fact distance 'AlphaCentauri  'Arbre  74)
      (pldb/db-fact distance 'Snowdin  'Tambi  105)
      (pldb/db-fact distance 'Snowdin  'Faerun  95)
      (pldb/db-fact distance 'Snowdin  'Norrath  48)
      (pldb/db-fact distance 'Snowdin  'Straylight  88)
      (pldb/db-fact distance 'Snowdin  'Arbre  7)
      (pldb/db-fact distance 'Tambi  'Faerun  68)
      (pldb/db-fact distance 'Tambi  'Norrath  134)
      (pldb/db-fact distance 'Tambi  'Straylight  107)
      (pldb/db-fact distance 'Tambi  'Arbre  40)
      (pldb/db-fact distance 'Faerun  'Norrath  11)
      (pldb/db-fact distance 'Faerun  'Straylight  66)
      (pldb/db-fact distance 'Faerun  'Arbre  144)
      (pldb/db-fact distance 'Norrath  'Straylight  115)
      (pldb/db-fact distance 'Norrath  'Arbre  135)
      (pldb/db-fact distance 'Straylight  'Arbre  127)))

(def find-distances 
  (distance-between-cities santa-city-db)) ;; get all the distances for puzzle input

