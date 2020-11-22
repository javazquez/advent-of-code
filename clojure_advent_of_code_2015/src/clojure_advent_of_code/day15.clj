(ns clojure-advent-of-code.day15
  (:require [clojure.core.logic :as logic]
            [clojure.core.logic.fd :as fd]))


(def puzzle-ingredients (list {:name "Sprinkles" :capacity 5 ,:durability -1, :flavor 0, :texture 0, :calories 5}
                              {:name "PeanutButter" :capacity -1 ,:durability 3, :flavor 0, :texture 0, :calories 1}
                              {:name "Frosting" :capacity 0 ,:durability -1, :flavor 4, :texture 0, :calories 6}
                              {:name "Sugar" :capacity -1 ,:durability 0, :flavor 0, :texture 2, :calories 8}))

(defn score-combos
  ""
  [tcnts ingredient-values]
  (let [temp (map * tcnts ingredient-values)
        total (reduce + temp)]
    (if (< total 0)
      0
      total)))

;; Ugly, should clean this up :)
(defn combine-tcnt-ingredients
  [t-cnts i-list calorie-cnt]
  (let [capacities (map :capacity i-list)
        durabilities (map :durability i-list)
        flavors (map :flavor i-list)
        textures (map :texture i-list)
        calories (map :calories i-list)
        recipe-with-calories? (if (and (> calorie-cnt 0)
                                       (= (score-combos t-cnts calories)
                                          calorie-cnt))
                                true
                                false)
        calculated     (*
                        (score-combos t-cnts capacities)
                        (score-combos t-cnts durabilities)
                        (score-combos t-cnts flavors)
                        (score-combos t-cnts textures))]
    (if (or (and (> calorie-cnt 0)
                 recipe-with-calories?)
            (< calorie-cnt  1))
      calculated
      0)))

(defn ingredient-listo
  "a valid ingredient list is when all sum to 100"
  [q]
  (logic/fresh [a b c d]
               (logic/== q [a b c d])
               (fd/in a b c d (fd/interval 0 100))
               (fd/eq (= 100 (+ a b c d)))))

(def solve-part1 (->>
                  (logic/run* [q]
                              (ingredient-listo q))
                  (map #(combine-tcnt-ingredients % puzzle-ingredients 0))
                  (apply max)))

(def solve-part2 (->>
                  (logic/run* [q]
                              (ingredient-listo q))
                  (map #(combine-tcnt-ingredients % puzzle-ingredients 500))
                  (apply max)))



