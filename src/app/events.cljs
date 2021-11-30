(ns app.events
  (:require
   [re-frame.core :refer [reg-event-db reg-event-fx]]
   [app.db :refer [app-db]]
   [day8.re-frame.tracing :refer-macros [fn-traced]]))

(reg-event-db
 ::initialize-db
 (fn-traced [db _] app-db))

(reg-event-fx
 ::set-active-page
 (fn-traced [{:keys [db]} [_ {:keys [page]}]]
   (let [set-page (assoc db :active-page page)]
     (case page
       :home {:db set-page}
       :asset {:db set-page}))))