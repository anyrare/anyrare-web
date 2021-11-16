(ns app.asset.events
  (:require
   [re-frame.core :refer [reg-event-db]]
   ["@splidejs/splide" :default Splide]
   ["perfect-scrollbar" :as PerfectScrollbar]
   [app.asset.db :as db]))

(reg-event-db
 :initialze-view
 (fn []
   (-> (.mount (Splide. "#image-slider" (js-obj "cover" true "heightRatio" 1.0)))
       (as-> _ (PerfectScrollbar. ".horizontal-scrollbar"))
       (as-> _ nil))))
