(ns clojure-advent-of-code.day8)

(defn count-code-chars
  "count the number of chars in code string"
  [in-str-seq]
  (count (clojure.string/join in-str-seq)))

(defn count-in-memory-chars
  "count the number of in memory chars"
  [in-str-lst]
  (-> in-str-lst
      (clojure.string/join)
      (clojure.string/replace #"\\\\" "|")
      (clojure.string/replace #"\\x\w\w" "'")
      (clojure.string/replace #"\"" "")
      count))

(defn wrap-str
  "bookend string with double quotes"
  [in-str]
  (str "\"" in-str "\""))

(defn encode-str
  "encode a string using the rules provided by the puzzle"
  [in-str]
  (-> in-str
      (clojure.string/replace  #"\"" "_quote_")
      (clojure.string/replace  #"\\" "_slash_")
      (clojure.string/replace #"_quote_" "\\\\\"")
      (clojure.string/replace #"_slash_" "\\\\\\\\")
      wrap-str))

