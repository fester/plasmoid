(ns plasmoid.core
  [:import (java.io File FileWriter BufferedWriter)
   (java.awt Color)
   (java.awt.image BufferedImage)
   (javax.imageio ImageIO)]
  [:require [plasmoid.color :refer :all]])
;; [plasmoid.tools :refer :all]])


;; (def ^:dynamic target-surface '())

;; (defn set-pixel [x y color]
;;   (.setRGB target-surface x y (int-color color)))

;; (defn plasma [width height]
;;   (binding [target-surface (BufferedImage. width height BufferedImage/TYPE_INT_ARGB)]
;;     (let [g (.createGraphics target-surface)
;;           x1 0
;;           y1 0
;;           x2 (dec width)
;;           y2 (dec height)]
;;       (.setColor g Color/BLACK)
;;       (.fillRect g 0 0 width height)

;;       (set-pixel x1 y1 (random-color))
;;       (set-pixel x2 y1 (random-color))
;;       (set-pixel x1 y2 (random-color))
;;       (set-pixel x2 y2 (random-color))
;;       (ImageIO/write target-surface "png" (File. (str width "x" height ".png"))))))

;; (defn plasma [width heigh]
  
(defn average [& values]
  (/ (reduce + values) (count values)))

(defn rand1 [limit]
  (- (* (rand) limit) (/ limit 2.0)))

(defn area [[left top] [right bottom]]
  (* (- right left)
     (- bottom top)))

(defn area-small? [point-a point-b]
  (<= (area point-a point-b) 1))

(defn plasma 
  ([width height]
  "Generate diamond square fractal plasma on a rectangle of givend width/height."
  (plasma '(0 0) [width height] (repeatedly 4 rand)))

  ([[left top :as a] [right bottom :as b] [color1 color2 color3 color4]]
  (if (area-small? a b)
     [(map int a) color1]
     (let [center-x (average left right)
           center-y (average top bottom)
           left-color (average color1 color3)
           right-color (average color2 color4)
           top-color (average color1 color2)
           bottom-color (average color3 color4)
           center-color (+ (average left-color right-color top-color bottom-color) (rand1 0.1))]
       (conj (plasma [left top] [center-x center-y] [color1 top-color left-color center-color])
             (plasma [center-x top] [right center-y] [top-color color2 center-color right-color])
             (plasma [left center-y] [center-x bottom] [left-color center-color color3 bottom-color])
             (plasma 
              [center-x center-y] 
              [right bottom] 
              [center-color right bottom-color color4])
             )))))

(defn -main
  [width height]
  (println (plasma (Integer/parseInt width) (Integer/parseInt height)))
)