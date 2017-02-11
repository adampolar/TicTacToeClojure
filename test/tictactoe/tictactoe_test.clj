(ns tictactoe.tictactoe-test
  (:require [clojure.test :refer :all]
            [tictactoe.tictactoe :refer :all]))

(deftest get-display-char-if-nil-case
  (testing "testing if space is returned"
    (is (= (get-display-char nil) " "))))

(deftest get-display-char-if-number-case
  (testing "testing if char is returned"
    (is (= (get-display-char \x) \x))))

(deftest check-input-valid-valid-case
  (testing "testing if valid input passes"
    (is (check-input-valid "A1"))
    (is (check-input-valid "A2"))
    (is (check-input-valid "A3"))
    (is (check-input-valid "B1"))
    (is (check-input-valid "B2"))
    (is (check-input-valid "B3"))
    (is (check-input-valid "C1"))
    (is (check-input-valid "C2"))
    (is (check-input-valid "C3"))))

(deftest check-input-valid-invalid-case
  (testing "testing if valid input passes"
    (is (not(check-input-valid "A1A1")))
    (is (not(check-input-valid "A4")))
    (is (not(check-input-valid "D3")))
    (is (not(check-input-valid "11")))
    (is (not(check-input-valid "a1")))
    (is (not(check-input-valid "aaB3")))
    (is (not(check-input-valid " A1")))
    (is (not(check-input-valid "C2 ")))))

(deftest parse-input-returns-right-number
  (testing "the right number is returned"
    (is (=(parse-input "A1") 0))
    (is (=(parse-input "A2") 3))
    (is (=(parse-input "A3") 6))
    (is (=(parse-input "B1") 1))
    (is (=(parse-input "B2") 4))
    (is (=(parse-input "B3") 7))
    (is (=(parse-input "C1") 2))
    (is (=(parse-input "C2") 5))
    (is (=(parse-input "C3") 8))))

(deftest parse-input-returns-nil
  (testing "nil is returned"
    (is (=(parse-input "F1") nil))
    (is (=(parse-input "D2") nil))
    (is (=(parse-input "13") nil))))

(deftest is-move-invalid-positive
  (testing "is move invalid returns true if the move is invalid"
    (is (is-move-invalid (vector nil \x nil nil) "B1"))
    (is (is-move-invalid (vector nil 1  nil) "B1"))
    (is (is-move-invalid (vector \x nil nil) "A1"))
    (is (is-move-invalid (vector nil false) "B1"))
    (is (is-move-invalid (vector nil nil nil nil nil nil nil nil false) "A4"))))

(deftest is-move-invalid-returns-negative
  (testing "is move invalid returns false if the move is valid"
    (is (not (is-move-invalid (vector nil \x nil nil) "A1")))
    (is (not (is-move-invalid (vector \o nil \x) "B1")))
    (is (not (is-move-invalid (vector nil nil) "B1")))))

(deftest who-has-won-returns-true-for-win
  (testing "who has won returns true for win"
    (is (has-someone-won (vector \x \x \o \o \x \o \x \o \x)))
    (is (has-someone-won (vector \x nil \o nil \x nil \o nil \x)))
    (is (has-someone-won (vector \x nil \x \o \o \o \x nil nil)))))

(deftest who-has-won-returns-false-for-no-win
  (testing "who has won returns false for no win"
    (is (not (has-someone-won (vector \x \x \o \o nil \o \x \o \x))))
    (is (not (has-someone-won (vector \x nil \o nil nil nil \o nil \x))))
    (is (not (has-someone-won (vector \x nil \x \x nil \o \o nil nil))))))

(deftest get-player-from-turn-returns-correct-player
  (testing "correct player returned"
    (is (= (get-player-from-turn 1) \x))
    (is (= (get-player-from-turn 3) \x))
    (is (= (get-player-from-turn 4) \o))
    (is (= (get-player-from-turn 12) \o))))
