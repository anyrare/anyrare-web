(ns anyrare-web.events
  (:require
   [re-graph.core :as re-graph]
   [re-frame.core :refer [reg-event-db reg-event-fx reg-fx dispatch subscribe]]
   [kitchen-async.promise :as p]
   [anyrare-web.db :refer [app-db]]
   [anyrare-web.gql :refer [gql]]
   [anyrare-web.ethers :as ethers]
   [anyrare-web.config.i18n :refer [get-dicts-by-lang]]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   [day8.re-frame.async-flow-fx :as async-flow-fx]))

(reg-event-db
 ::initialize-db
 (fn-traced [db _] app-db))

(reg-event-fx
 ::navigate
 (fn [_ [_ handler]]
   {:navigate handler}))

(reg-event-fx
 ::set-active-page
 (fn-traced [{:keys [db]} [_ {:keys [page route-params]}]]
            (let [set-page (-> db
                               (assoc :active-page page)
                               (assoc :route-params route-params))]
              (case page
                :home {:db set-page}
                :asset {:db set-page}
                :register {:db set-page
                           :dispatch-n [[::fetch-member-by-code
                                         {:code (:code route-params)}]]}))))

;; GraphQL Flow

(defn fetch-gql [query type params callback save-var nested-var]
  (case type
    :query [::re-graph/query query params [callback save-var nested-var]]
    :mutation [::re-graph/mutate query params [callback save-var nested-var]]))

(reg-event-fx
 ::save-gql-data
 (fn [{:keys [db]} [_ save-var nested-var {:keys [data errors]}]]
   {:db (-> db
            (assoc save-var (get-in data [nested-var]))
            (assoc :errors errors))}))

(reg-event-db
 ::set-i18n
 (fn-traced [db [_ lang]]
            (assoc db :i18n (get-dicts-by-lang lang))))

;; lib/ethers
(reg-event-db
 ::set-account-id
 (fn-traced [db [_ account-id]]
            (assoc db :account-id account-id)))

;; Register
(reg-event-fx
 ::fetch-member-by-code
 (fn [_ [_ {:keys [code]}]]
   {:dispatch (fetch-gql
               (get-in gql [:member-by-code :query])
               (get-in gql [:member-by-code :type])
               {:code code}
               ::save-gql-data
               :referral
               :member_by_code)}))

(reg-event-fx
 ::ethers-set-member
 (fn [_ _]
   (ethers/set-member "0x5A81399116Ad2e89E45b31c4e1A67C7F254F58f3" #(dispatch [::save-member]))))

(reg-event-fx
 ::create-member
 (fn [_ _]
   {:async-flow
    {:first-dispatch [::ethers-set-member]}}))

(reg-event-fx
 ::save-member
 (fn [_ _]
   (.log js/console "save-member")))



