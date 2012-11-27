(ns plasmoid.core
  [:import (java.io File FileWriter BufferedWriter)
   (java.awt Color)
   (java.awt.image BufferedImage)
   (javax.imageio ImageIO)]
  [:require [plasmoid.color :refer :all] [plasmoid.tools :refer :all]])

(defn set-pixel [image x y color]
  (.setRGB image x y (int-color color)))
  

(defn plasma [width height]
  (let [bi (BufferedImage. width height BufferedImage/TYPE_INT_ARGB)
        g (.createGraphics bi)
        x1 0
        y1 0
        x2 (dec width)
        y2 (dec height)]
    (.setColor g Color/BLACK)
    (.fillRect g 0  0 width height)

    (set-pixel bi x1 y1 (random-color))
    (set-pixel bi x2 y1 (random-color))
    (set-pixel bi x1 y2 (random-color))
    (set-pixel bi x2 y2 (random-color))
    (ImageIO/write bi "png" (File. (str width "x" height ".png")))))

(defn -main
  "I don't do a whole lot."
  [width height]
  (let [integer-width (. Integer parseInt width)
        integer-height (. Integer parseInt height)]
    (plasma integer-width integer-height)
    ))