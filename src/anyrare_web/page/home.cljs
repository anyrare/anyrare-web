(ns anyrare-web.page.home
  (:require
   [reagent.core :as reagent]
   [re-frame.core :refer [subscribe dispatch]]
   [spade.core :refer [defclass]]
   [garden.stylesheet :refer (at-media)]))

(defn home []
  [:div "Home"])
