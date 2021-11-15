(ns app.asset.events
  (:require
   [re-frame.core :refer [reg-event-db]]
   ["swipejs" :as Swipe]
   [app.asset.db :as db]))

(reg-event-db
 ::add-slider
 (fn []
   (-> (Swipe. (.getElementById js/document "slider"))
       (as-> x (.log js/console x)))))