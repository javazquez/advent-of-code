(ns clojure-advent-of-code-2017.day06)

(defn- largest-bank
  "Return [idx value] of largest bank"
  [memory]
  (let [idx-value-col (map-indexed vector memory)
        max-val (->> idx-value-col
                     (map second)
                     (apply max))]
    (->> (filter (fn [[idx value]]
                   (= value
                      max-val))
                 idx-value-col)
         first)))

(defn- handle-overflow
  [idx mbank]
  (rem idx
       (count mbank)))

(defn- distrubute-memory
  [idx counter memory]
  (if (<= counter 0)
    memory
    (recur (handle-overflow (inc idx) memory)
           (dec counter)
           (assoc memory
                  idx 
                  (inc (nth memory idx))))))

(defn balance-memory-blocks
  [memory]
  (let [[idx val] (largest-bank memory)]
    (->> (assoc memory idx 0)
         (distrubute-memory (handle-overflow (inc idx)
                                             memory)
                            val))))

(defn solve
  ([memory] (solve memory []))
  ([memory acc]
   (if (some (hash-set memory ) acc)
     {:memory memory :cycle-count (count acc)}
     (recur (balance-memory-blocks memory)
            (conj acc memory)))))
