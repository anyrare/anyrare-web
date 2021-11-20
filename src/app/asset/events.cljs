(ns app.asset.events
  (:require
   [re-frame.core :refer [reg-event-db]]
   [re-graph.core :refer [query]]
   ["@splidejs/splide" :default Splide]
   ["perfect-scrollbar" :as PerfectScrollbar]
   [app.asset.db :as db]))

(defn console-log [r]
  (.log js/console r))

(defn gql-query []
  (query "{ creatorProfiles { id } }"
         nil
         console-log)
  )

(reg-event-db
 :initialze-view
 (fn []
   (-> (.mount (Splide. "#image-slider" (js-obj "cover" true "heightRatio" 1.0)))
       (as-> _ (PerfectScrollbar. ".horizontal-scrollbar"))
       (as-> _ (gql-query))
       (as-> _ nil))))

(reg-event-db
 :set-active-tab
 (fn [db [_ active-tab]]
   (assoc-in db [:asset :tab-active-index] active-tab)))

