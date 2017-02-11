(ns tictactoe.tictactoe)

(defn get-display-char [char]
  (if  (= char nil) " " char))

(defn print-board-to-screen [game-state]
  (println 
           "\n     A    B    C"
           "\n 1 " (get-display-char (nth game-state 0)) " | " 
               (get-display-char (nth game-state 1)) " | " 
               (get-display-char (nth game-state 2)) "\n"
               "  ---------------\n"
           "2 " (get-display-char (nth game-state 3)) " | " 
               (get-display-char (nth game-state 4)) " | " 
               (get-display-char (nth game-state 5)) "\n"
               "  ---------------\n"
           "3 " (get-display-char (nth game-state 6)) " | " 
               (get-display-char (nth game-state 7)) " | " 
               (get-display-char (nth game-state 8)) "\n"))

(defn check-input-valid [input-string]
  (not= (re-matches #"[A-C]{1}[1-3]{1}" input-string) nil))

(defn parse-input [input-string]
  (if (check-input-valid input-string)
    (+
      (* (- (int (get input-string 1)) 49) 3)
      (- (int (get input-string 0)) 65))))

(defn equality-excluding-nil [arg1 arg2 arg3]
  (and 
   (= arg1 arg2 arg3)
   (not (nil? arg1))))

(defn has-someone-won [board]
  (or (equality-excluding-nil (nth board 0) (nth board 1) (nth board 2))
      (equality-excluding-nil (nth board 3) (nth board 4) (nth board 5))
      (equality-excluding-nil (nth board 6) (nth board 7) (nth board 8))
      (equality-excluding-nil (nth board 0) (nth board 3) (nth board 6))
      (equality-excluding-nil (nth board 1) (nth board 4) (nth board 7))
      (equality-excluding-nil (nth board 2) (nth board 5) (nth board 8))
      (equality-excluding-nil (nth board 0) (nth board 4) (nth board 8))
      (equality-excluding-nil (nth board 2) (nth board 4) (nth board 6))))

(defn is-move-invalid [board move]
  (def move-as-number (parse-input move))
  (if (not= move-as-number nil)
    (not= (nth board move-as-number) nil)
    true))

(defn get-player-from-turn [turn] 
  (if (= (mod turn 2) 1) \x \o))

(defn main []
  (def board (vector nil nil nil nil nil nil nil nil nil))
  (println "welcome to tic tac toe clojure, crosses to start, please take your turn by entering coordinates in the format \"A1\" or \"B3\"\n")
  (loop [x 1]
    (when (not (has-someone-won board))
      (print-board-to-screen board)
      (def move (read-line))
      (if (is-move-invalid board move) 
        (println "try again")
        (def board (assoc board (parse-input move) (get-player-from-turn x))))
      (recur (inc x))))
  (print-board-to-screen board)
  (println "Congrats"))

