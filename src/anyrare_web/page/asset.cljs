(ns anyrare-web.page.asset
  (:require
   [re-frame.core :refer [subscribe dispatch]]
   [anyrare-web.events :as events]
   [anyrare-web.subs :as subs]))

(defn asset []
  [:div "Asset"])
