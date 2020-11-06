(ns clojure-advent-of-code.day20)

;; Part 1 Infinite elves will bring presents to every house that is multiple of its number and bring N*10 gifts
;; Part 2 Elves only deliver 50 presents
;; solve what is lowest number elf that <= puzzle-input?

(def puzzle-input 36000000)

;;find the house with
(defn elf-factors
  "this will flatten a collection of pairs, remove nil and then make them distinct to
  example-> [1,2,2,4] for house # 4  "
  [^Integer  elf-number]
  (let [inclusive-temp (Math/sqrt elf-number)
        visiting-elves (range 1
                              (Math/ceil (if (integer? inclusive-temp)
                                           inclusive-temp
                                           (inc inclusive-temp))))]
    (->> (for [elf visiting-elves]
           (if (zero? (mod elf-number elf))
             [elf (/ elf-number elf)]))
         (flatten)
         (keep identity)
         (distinct))))

(defn presents-from-elves
  "given an elf number, multiply by 10 for the give count"
  [^Integer elf-number]
  (->>
   (map #(* 10 %)
        (elf-factors elf-number))
   (reduce +)))

(defn gte-puzzle-input?
  "Predicate for if the house numbers presents are >= puzzle input"
  [^Integer elf-number ^Integer pz-input]
  (let [present-cnt (presents-from-elves elf-number)]
    (>= present-cnt
        pz-input)))

;;; PART 2 below

(defn active-elf?
  "Predicate to make sure that the elf is still able to deliver and has not met its 50 delivery quota"
  [house-number elf]
  (>= (* 50 elf) ;; if max house to deliver is gt or eq than it is good
      house-number))

(defn presents-from-elves-part2
  "given an elf number, multiply by 10 for the give count"
  [^Integer elf-number]
  (->>
   (elf-factors elf-number)
   (filter #(active-elf?  elf-number %))
   (map #(* 11 %))
   (reduce +)))

(defn gte-puzzle-input2?
  ""
  [^Integer elf-number ^Integer pz-input]
  (let [present-cnt (presents-from-elves-part2 elf-number)]
    (>= present-cnt
        pz-input)))
