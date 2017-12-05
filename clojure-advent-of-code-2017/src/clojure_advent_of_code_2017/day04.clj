(ns clojure-advent-of-code-2017.day04)

(defn valid-passphrase?
  [pass-str]
  (->> (clojure.string/split pass-str #"\s+")
       (apply distinct? ))) 

(defn anagram?
  [pass-str]
  (->> (clojure.string/split pass-str #"\s+")
       (map sort)
       (apply distinct? )))

