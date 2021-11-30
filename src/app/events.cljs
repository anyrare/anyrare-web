(ns app.events
  (:require
   [re-frame.core :refer [reg-event-db reg-event-fx]]
   [app.db :refer [app-db]]
   [day8.re-frame.tracing :refer-macros [fn-traced]]))

(reg-event-db
 ::initialize-db
 (fn-traced [_ _] app-db))

(reg-event-fx
 :set-active-page
 (fn-traced [{:keys [db]} [_ {:keys [page slug]}]]
            {:db (assoc db :active-page page)}))
