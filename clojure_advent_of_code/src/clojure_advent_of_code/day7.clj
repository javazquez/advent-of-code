(ns clojure-advent-of-code.day7
  (:require [instaparse.core :as insta]))

(def symbol-table (atom {}))

(def circuit-instructions
  (insta/parser
    "<p> = statement | output ;
     <statement> = and | or | lshift | rshift | not
     and = numvar <wspace> <'AND'> <wspace> numvar <wspace> <'->'> <wspace> var
     or = numvar <wspace> <'OR'> <wspace> numvar <wspace> <'->'> <wspace> var
     lshift = numvar <wspace> <'LSHIFT'> <wspace> num <wspace> <'->'> <wspace> var
     rshift = numvar <wspace> <'RSHIFT'> <wspace> num <wspace> <'->'> <wspace> var
     not = <'NOT'> <wspace> var <wspace> <'->'> <wspace> var
     output = numvar <wspace> <<'->'>> <wspace> var
     <num> = #'\\d+'
     wspace = #'\\s+'
     <var> = #'[a-z]+'
     <numvar>= num|var
     "))

(defn resolve-vars [ string-input ]
  (let [conversion (read-string string-input)]
    (if (number? conversion )
      conversion
      (@symbol-table (keyword conversion)))))

(defn handle-output [ num output ]
  (let [resolved-arg (resolve-vars num) ]
    (if (nil? resolved-arg)
      {}
      {(keyword output) resolved-arg})))

(defn handle-bit-not [ var1 output ]
  (let [resolved-arg (resolve-vars var1) ]
    (if (nil? resolved-arg)
      {}
      {(keyword output) (bit-and 0xffff
                                 (bit-not (resolve-vars var1)))})))

(defn handle-ops [ f arg1 arg2 output ]
  (let [resolved-arg1 (resolve-vars arg1)
        resolved-arg2 (resolve-vars arg2)]
    (if (or (nil? resolved-arg1) (nil? resolved-arg2 ))
      {}
      {(keyword output) (f resolved-arg1 resolved-arg2)})))

(def transform-ops
  { :output (fn [x z](handle-output x z))
    :not (fn [x z] (handle-bit-not x z ))
    :and (fn [x y z](handle-ops bit-and x y z ))
    :or (fn [x y z](handle-ops bit-or x y z ))
    :lshift (fn [x y z](handle-ops bit-shift-left x y z ))
    :rshift (fn [x y z](handle-ops bit-shift-right x y z ))
  })

(defn eval-line [ instruction ]
  (insta/transform transform-ops (circuit-instructions instruction)))

(defn process-instructions [ list-of-instructions ]
  (loop [ [h & my-stack] list-of-instructions]
        (if (nil? h)
             @symbol-table
             (do (swap! symbol-table merge (first (eval-line h )))
               (recur my-stack)))))

(defn find-result [string-instructions]
  (reset! symbol-table {})
  (loop [ cur-count (count @symbol-table) ]
    (process-instructions string-instructions)
    (if (= cur-count (count @symbol-table))
     @symbol-table
     (recur (count @symbol-table))
    )))
