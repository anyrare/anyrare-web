(ns anyrare-web.component.svg)

(defn angle-down [width height]
  [:svg {:view-box "0 0 24 24" :width (str width "px") :height (str height "px")}
   [:path
    {:d "M18.71,8.21a1,1,0,0,0-1.42,0l-4.58,4.58a1,1,0,0,1-1.42,0L6.71,8.21a1,1,0,0,0-1.42,0,1,1,0,0,0,0,1.41l4.59,4.59a3,3,0,0,0,4.24,0l4.59-4.59A1,1,0,0,0,18.71,8.21Z"}]])

(defn angle-up [width height]
  [:svg {:view-box "0 0 24 24" :width (str width "px") :height (str height "px")}
   [:path
    {:d "M18,15.5a1,1,0,0,1-.71-.29l-4.58-4.59a1,1,0,0,0-1.42,0L6.71,15.21a1,1,0,0,1-1.42-1.42L9.88,9.21a3.06,3.06,0,0,1,4.24,0l4.59,4.58a1,1,0,0,1,0,1.42A1,1,0,0,1,18,15.5Z"}]])
