(ns clojure-advent-of-code.day11)

(def password "abcdefghijklmnopqrstuvwxyz")
(def alphabet (seq password))

(defn row-of-three?
  "Helper for inc-straight? function"
  [[a b c]]
  (and (= (inc c)
          b)
       (= (inc b)
          a)))

(defn inc-straight?
  "Passwords must include one increasing straight of at least three letters,
  like abc, bcd, cde, and so on, up to xyz. They cannot skip letters; abd
  doesn't count."
  [pwd-seq]
  (some row-of-three? (partition 3 1 pwd-seq)))

(defn password->numbers
  "convert password string into number seq "
  [pwd-str]
  (->> (seq pwd-str)
       (map #(.indexOf alphabet %))))

(defn increment-number
  "increment a number that corresponds to a letter and jump 2 when in #{ i o l }
  aka #{ 8 13 11}"
  [ number]
  (if (#{8 11 14} number)
    (inc  number)
    number))

(defn numbers->password
  [nums]
  (map  #(nth alphabet %) nums))

(defn pairs?
  "need two non overlapping pairs "
  [pwd-seq]
  (< 1 (count (re-seq #"(?:(\w)\1+)"
                      (reduce str
                              (numbers->password pwd-seq))))))

(defn bad-char?
  "passwords cannot contain l i o characters"
  [pwd-seq]
  (some? (some #{8 11 14} pwd-seq  )))

(defn valid-password?
  [pw-seq]
  (and (= 8
          (count pw-seq))
       (inc-straight? pw-seq)
       (pairs? pw-seq)
       (not (bad-char? pw-seq))))

(defn get-next-password
  "Add to number representing a letter and skip l i o characters"
  ([pw-seq] (get-next-password pw-seq []) ) 
  ([pwd-seq acc]
   (let [[h & tail] pwd-seq]
     (if (nil? h)
       acc
       (if (zero? (mod (inc h) 26))
         (recur tail (conj acc 0))
         (concat acc [(increment-number (inc h))] tail))))))

(defn solve
  [pwdstr]
  (let [n-seq (reverse (password->numbers pwdstr))
        infinite-list (iterate get-next-password n-seq)]
    (->> infinite-list
         (filter valid-password?))))


