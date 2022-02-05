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


;; Router


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
                                         {:code (:code route-params)}]]}
                :asset-mint {:db set-page}))))


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


;; Ethers


(reg-event-db
 ::ethers-tx-callback
 (fn [db [_ data]]
   (.log js/console "tx-callback" (clj->js data))
   (assoc db :ethers-tx-result data)))

(reg-event-fx
 ::ethers-set-member
 (fn [_ [_ referral]]
   {:result (ethers/set-member referral #(dispatch [::ethers-tx-callback %]))}))

(reg-event-fx
 ::ethers-mint-nft
 (fn [_ [_ params]]
   {:result (ethers/mint-nft params #(dispatch [::ethers-tx-callback %]))}))

(reg-event-fx
 ::ethers-mint-nft-custodian-sign
 (fn [_ [_ params]]
   {:result (ethers/mint-nft-custodian-sign params #(dispatch [::ethers-tx-callback %]))}))

(reg-event-fx
 ::ethers-nft-current-token-id
 (fn [_ _]
   {:result (ethers/nft-current-token-id #(dispatch [::ethers-tx-callback %]))}))


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
 ::create-member
 (fn [_ [_ params]]
   {:async-flow
    {:first-dispatch [::ethers-set-member (:referral params)l]
     :rules [{:when :seen? :events ::ethers-tx-callback
              :dispatch-fn (fn [[_ result]]
                             [[::save-member (:address result) (:referral params)]])}]}}))

(reg-event-fx
 ::save-member
 (fn [_ [_ address referral]]
   {:dispatch (fetch-gql
               (get-in gql [:create-member :query])
               (get-in gql [:create-member :type])
               {:address address
                :referral referral
                :code address
                :username address
                :thumbnail "https://image.net/1.jpg"}
               ::save-gql-data
               :member
               :create_member)}))


;; Asset Mint


(reg-event-fx
 ::save-asset
 (fn [_ _]
   {:result (.log js/console "Save asset")}))

(reg-event-fx
 ::mint-nft
 (fn [_ [_ params]]
   {:async-flow
    {:first-dispatch [::ethers-mint-nft params]
     :rules [{:when :seen? :events ::ethers-tx-callback
              :dispatch-fn (fn [[_ result]] [[::save-asset result]])}]}}))

(reg-event-fx
 ::mint-nft-custodian-sign
 (fn [_ [_ params]]
   {:async-flow
    {:first-dispatch [::ethers-mint-nft-custodian-sign params]
     :rules [{:when :seen? :events ::ethers-tx-callback
              :dispatch-fn (fn [[_ result]] [[::save-asset result]])}]}}))


(reg-event-fx
 ::nft-current-token-id
 (fn [_ [_ params]]
   {:async-flow
    {:first-dispatch [::ethers-nft-current-token-id]
     :rules [{:when :seen? :events ::ethers-tx-callback
              :dispatch-fn (fn [[_ result]] [[::save-asset result]])}]}}))


