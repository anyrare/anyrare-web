(ns anyrare-web.page.home
  (:require
   [reagent.core :as reagent]
   [re-frame.core :refer [subscribe dispatch]]
   [anyrare-web.asset.events :as events]
   [anyrare-web.asset.subs :as subs]
   [anyrare-web.subs :as app-subs]
   [anyrare-web.lib.format :refer [unix-timestamp-to-local-datetime]]
   [anyrare-web.component.avatar :refer [avatar avatar-with-username]]
   [anyrare-web.component.svg :refer [angle-down angle-up]]
   [spade.core :refer [defclass]]
   [garden.stylesheet :refer (at-media)]))

(defn home []
  [:div "Home"])
