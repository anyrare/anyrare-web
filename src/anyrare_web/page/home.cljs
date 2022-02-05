(ns anyrare-web.page.home
  (:require
   [re-frame.core :refer [subscribe dispatch]]
   [anyrare-web.events :as events]
   [anyrare-web.subs :as subs]))

(defn home []
  [:div "Home"])
