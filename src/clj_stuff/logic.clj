(ns clj-stuff.logic)

; A tic tac toe board is like so
;  [:empty     :x      :o
;   :x         :empty  :x
;   :empty     :empty  :empty]
;
; Each space can also be considered to be numbered, like so:
;   [0   1   2
;    3   4   5
;    6   7   8]
;
; Just a vector.
(def starting-board
  (vec (repeat 9 :empty)))

(defn move?
  [n b]
  (= :empty (b n)))

(defn make-move
  [n p b]
  (if (move? n b)
      (assoc b n p)
      nil))

(defn tie?
  [b]
  (not (some #(= :empty %) b)))

(defn triples
  [b]
  (concat
    ; rows
    (partition-all 3 b)
    (list
      ; columns
      (take-nth 3 b)
      (take-nth 3 (drop 1 b))
      (take-nth 3 (drop 2 b))

      ; diagonals
      (take-nth 4 b)
      (take-nth 4 (reverse b)))))

(defn win?
  [p b]
  (some #(= (repeat 3 p) %) (triples b)))
