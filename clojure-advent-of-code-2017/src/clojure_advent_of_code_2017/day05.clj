(ns clojure-advent-of-code-2017.day05)

(defn trampoline-jump
  [input-ary func]
  (loop [input input-ary
         idx 0
         steps 0]
    (if (or (<  idx 0)
            (>= idx
                (count input)))
      steps
      (recur (assoc input
                    idx
                    (func (nth input idx) ))
             (+ idx  (nth input idx) )
             (inc steps)))))

(defn jump-a
  [input-ary]
  (trampoline-jump input-ary
                   inc))
(defn jump-b
  [input-ary]
  (trampoline-jump input-ary
                   (fn [operand]
                     (+ (if (>= operand 3)
                          -1
                          1 )
                        operand))))
