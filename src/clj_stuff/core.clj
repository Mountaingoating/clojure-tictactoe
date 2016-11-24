(ns clj-stuff.core
  (:gen-class))

(require '[clojure.core.match :refer [match]])

(use 'clj-stuff.logic)

(defn console-suffix
  [n]
  (cond (> n 7) nil
        (= 2 (mod n 3)) "\n-----------\n"
        :else "|"))

(defn p-to-str
  [sq n]
  (cond (= :x sq) "x"
        (= :o sq) "o"
        (= :empty sq) n))

(defn board-to-string
  [b]
  (apply str
    (map
      #(str " " (p-to-str (b %) %) " " (console-suffix %))
      (range 9))))

(defn run-game
  [p b]
  (println (board-to-string b))
  (println)
  (println "Make your move by entering a number between 0 and 8: ")
  (let [n (read-string (read-line))
        new-b (make-move n p b)
        next-p (if (= p :x)
                   :o
                   :x)]
    (cond
      (nil? new-b) (do (println "Invalid Move!")
                       (run-game p b))
      (win? p new-b) (println (apply str "Winner: " (p-to-str p)))
      (tie? new-b) (println "It's a tie!")
      :else (run-game next-p new-b))))

(defn -main
  [& args]
  (println "Welcome to tic-tac-toe. Type \"start\" if you'd like to play a game.")
  (if (= "start" (read-line))
      (run-game :x starting-board)
      (println "nay")))
