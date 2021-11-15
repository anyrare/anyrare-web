(ns app.asset.events
  (:require
   [re-frame.core :refer [reg-event-db]]
   ["@splidejs/splide" :default Splide]
   [app.asset.db :as db]))

(reg-event-db
 ::add-slider
 (fn []
   (-> (.mount (Splide. ".splide"))
       (as-> _ nil))))