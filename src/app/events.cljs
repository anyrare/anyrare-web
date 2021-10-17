(ns app.events
  (:require
   [re-frame.core :refer [reg-event-db reg-event-fx]]
   [app.db :as db]))

(reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(reg-event-db
 ::name-change
 (fn [db [_ new-name-value]]
   (assoc db :name new-name-value)))

(reg-event-fx
 :set-active-page
 (fn [{:keys [db]} [_ {:keys [page slug]}]]
   (let [set-page (assoc db :active-page page)]
     (case page
       :home {:db set-page}
       :asset {:db set-page}
       :explorer {:db set-page}
       :profile {:db set-page}
       :following {:db set-page}
       :activity {:db set-page}))))