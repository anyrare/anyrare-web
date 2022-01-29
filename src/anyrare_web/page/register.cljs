(ns anyrare-web.page.register
  (:require
   [re-frame.core :refer [subscribe dispatch]]
   [anyrare-web.events :as events]
   [anyrare-web.subs :as subs]))

(defn register []
  [:div "Register"])
