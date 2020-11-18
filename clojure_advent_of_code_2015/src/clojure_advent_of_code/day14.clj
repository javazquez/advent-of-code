(ns clojure-advent-of-code.day14)

(def fly (repeat 1))
(def fly2 (iterate inc 1))
(def break (repeat 0))
(def vixen-flight {:name "vixen", :t-distance 0, :d 19 ,:flight (repeat (concat (take 7 fly) (take 124 break)))})
(def rudolph-flight {:name "rudolph" :t-distance 0 :d 3 :flight (repeat (concat (take 15 fly) (take 28 break)))})
(def donner-flight {:name "donner" :t-distance 0 :d 19 :flight (repeat (concat (take 9 fly) (take 164 break)))})
(def blitzen-flight {:name "blitzen" :t-distance 0 :d 19 :flight (repeat (concat (take 9 fly) (take 158 break)))})
(def comet-flight {:name "comet" :t-distance 0 :d 13 :flight (repeat (concat (take 7 fly) (take 82 break)))})
(def cupid-flight {:name "cupid" :t-distance 0 :d 25 :flight (repeat (concat (take 6 fly) (take 145 break)))})
(def dasher-flight {:name "dasher" :t-distance 0 :d 14 :flight (repeat (concat (take 3 fly) (take 38 break)))})
(def dancer-flight {:name "dancer" :t-distance 0 :d 3 :flight (repeat (concat (take 16 fly) (take 37 break)))})
(def prancer-flight {:name "prancer" :t-distance 0 :d 25 :flight (repeat (concat (take 6 fly) (take 143 break)))})

(def reindeer (list vixen-flight rudolph-flight donner-flight blitzen-flight
                    comet-flight cupid-flight dasher-flight dancer-flight prancer-flight))

(defn distance
  [{:keys [d flight] :as deer} flight-time]
  (->> flight
       flatten
       (take flight-time)
       (reduce +)
       (* d)))

(defn calc-distance
  [flightplan]
  (loop [[h & t] flightplan
         new-plan (list)
         acc 1]
    (if (nil? h)
      new-plan
      (recur  t
              (conj new-plan
                    (if (zero? h) ;;use prev value
                      (first new-plan)
                      (* h acc)))
              (if (zero? h)
                acc
                (inc acc))))))

(defn new-flight-plan
  "update the given deer with the new flight "
  [{:keys [d flight name] :as deer} time]
  (->> flight
       flatten
       (take time)
       (map #(* d %))
       calc-distance
       (map #(hash-map :name  name :total-distance %))
       reverse))

(defn chicken-dinner
  "winner winner, chicken dinner. return all deer in the lead "
  [& sled]
  (let [lead-distance (apply max (map :total-distance sled))]
    (map :name
         (filter (fn [deer]
                   (and (not (zero? lead-distance));;
                        (= lead-distance (:total-distance deer))))
                 sled))))

(defn deer-race
  "each letter represents a reindeers seq,  {:name n , :total-distance}"
  [[a b c d e f g h i]]
  (map chicken-dinner a b c d e f g h i));; how to pass the deer name and distance
