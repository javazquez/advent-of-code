(ns clojure-advent-of-code.day23
  (:require [clojure.java.io :as io]))

;; NOTE change a = 1 for part 2 and run again for puzzle answer

(def registers (atom {(read-string "a") 0
                      (read-string "b") 0
                      :stack-ptr 0}))


(def puzzle-input (line-seq (io/reader (io/resource "Day23.txt"))))

(defn parse-str
  [cmd-str]
  (vec (map read-string (re-seq #"[\w-]+" cmd-str))))

(defn munge-data
  [lines]
  (->> lines
       (map parse-str)
       (into [])))

(defn execute-cmd
  [[cmd-str & args]]
  (apply (ns-resolve *ns* (symbol cmd-str)) args))

(defn process-cmds
  [cmd-stack]
  (loop [cur-cmd (get cmd-stack (:stack-ptr @registers))]
    (if  (nil? cur-cmd)
      @registers
      (do
        (execute-cmd cur-cmd)
        (recur (get cmd-stack (:stack-ptr @registers)))))))

(defn inc-stack-ptr
  ""
  []
  (swap! registers update :stack-ptr inc))

(defn incr
  ""
  [reg]
  (inc-stack-ptr)
  (swap! registers update reg inc))

(defn hlf
  "half a register"
  [reg]
  (inc-stack-ptr)
  (swap! registers
         update reg
         #(/ % 2)))

(defn tpl
  "triple a register"
  [reg]
  (inc-stack-ptr)
  (swap! registers
         update reg
         #(* % 3)))

(defn jmp
  "jump register commands by n"
  [n]
  (swap! registers
         update :stack-ptr
         #(+ % n)))

(defn jie
  "jie r, offset is like jmp, but only jumps if register r is even jump if even"
  [reg offset]
  (if (even? (get @registers reg))
    (jmp offset)
    (inc-stack-ptr)));;need to continue to execute

(defn jio
  "jio r, offset is like jmp, but only jumps if register r is 1 jump if one, not odd."
  [reg offset]
  (if (= (get @registers reg)
         1)
    (jmp offset)
    (inc-stack-ptr)))

;;Solution below, uncomment/comment out the registers code above and rerun for the part seeking an answer to
(->> (munge-data puzzle-input)
     process-cmds)

;;part 1 = 255, part 2 = 334
