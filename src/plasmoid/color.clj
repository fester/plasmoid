(ns plasmoid.color
   (:import (java.awt Color))
   (:require [plasmoid.tools :refer :all]))

(defn color [r g b]
  (list r g b))

(defn awt-color [[r g b]]
  (Color. r g b))

(defn random-color []
  (apply color (for [_ (range 3)] (rand-int 255))))


(defn add-color [c1 c2]
  (map #(max (+ %1 %2) 255) c1 c2))

(defn average-color [c1 c2]
  (map #(int (midpoint %1 %2)) c1 c2))