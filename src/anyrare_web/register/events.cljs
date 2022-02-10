(ns anyrare-web.register.events
  (:require
   [re-frame.core :refer [reg-event-fx dispatch]]
   [anyrare-web.events :as app-events]
   [anyrare-web.ethers :as ethers]))


(reg-event-fx
 ::create-member
 (fn [_ [_ params]]
   {:dispatch-fn [(ethers/set-member params #(dispatch [::app-events/save-data :ethers-tx-callback %]))]}))

