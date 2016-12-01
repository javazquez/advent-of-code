(ns clojure-advent-of-code.day5 )

(defn vowel-pred [input-str]
  (>= (count
        (re-seq #"[aeiouAEIOU]" input-str) )
       3))

(defn bad-chars-pred [input-str]
   (re-find #"ab|cd|pq|xy" input-str))

(defn repeat-pred [input-str]
  (re-find #"(.)\1{1,}" input-str))

(defn nice-string? [input-str]
  (if (and (vowel-pred input-str)
           (repeat-pred input-str)
           (not (bad-chars-pred input-str)))
          true
          false))

(defn sol2-rule-1 [input-str]
  (re-find #"(.{2}).*\1" input-str))

(defn sol2-rule-2 [input-str]
  (or (repeat-pred (apply str (take-nth 2  input-str)))
       (repeat-pred (apply str (take-nth 2 (rest input-str))))))

(defn nice-string-sol2? [input-str]
  (if (and (sol2-rule-1 input-str)
           (sol2-rule-2 input-str))
      true
      false))
