(ns plasmoid.core
  [:import (java.io File FileWriter BufferedWriter)
   (java.awt Color)
   (java.awt.image BufferedImage)
   (javax.imageio ImageIO)]
  [:require [plasmoid.color :refer :all] [plasmoid.tools :refer :all]])

(defn plasma [width height]
  (let [bi (BufferedImage. width height BufferedImage/TYPE_INT_ARGB)
        g (.createGraphics bi)
        color-a (random-color)
        color-b (random-color)
        surface-color (average-color color-a color-b)]
    (.setColor g (awt-color surface-color))
    (.fillRect g 0 0 width height)
    (ImageIO/write bi "png" (File. (str width "x" height ".png")))))

(defn -main
  "I don't do a whole lot."
  [width height]
  (let [integer-width (. Integer parseInt width)
        integer-height (. Integer parseInt height)]
    (plasma integer-width integer-height)
    ))