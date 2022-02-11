(ns anyrare-web.asset.subs
  (:require
   [re-frame.core :refer [reg-sub]]
   [anyrare-web.lib.format :refer [format-money]]))

(reg-sub
 ::asset
 (fn [db _]
   (:asset-page db)))

(reg-sub
 ::asset-data
 (fn [db _]
   (:token-uri-data (:asset-page db))))

(reg-sub
 ::asset-title
 (fn [db _]
   (get-in db [:asset-page :token-uri-data :title :th])))

(reg-sub
 ::asset-detail
 (fn [db _]
   {:description (get-in db [:asset-page :token-uri-data :description :th])
    :token-id (str (get-in db [:asset-page :token-id]))
    :owner (get-in db [:asset-page :owner])
    :founder (get-in db [:asset-page :founder])
    :auditor (get-in db [:asset-page :auditor])
    :custodian (get-in db [:asset-page :custodian])
    :audit-date (get-in db [:asset-page :token-uri-data :auditor :timestamp])}))

(reg-sub
 ::asset-royalty
 (fn [db _]
   {:founder-fee (->
                  (/ (get-in db [:asset-page :fee :founder-weight])
                     (get-in db [:asset-page :fee :max-weight]))
                  (* 100)
                  (format-money 2)
                  (str " %"))
    :custodian-fee (->
                    (/ (get-in db [:asset-page :fee :custodian-weight])
                       (get-in db [:asset-page :fee :max-weight]))
                    (* 100)
                    (format-money 2)
                    (str " %"))}))

(reg-sub
 ::asset-attachments
 (fn [db _]
   (get-in db [:asset-page :attachments])))

(reg-sub
 ::asset-auction-highest-price
 (fn [db _]
   (let [highest-price (get-in db [:asset-page :auction :highest-price])
         highest-price-denominator (get-in db [:asset-page :auction :highest-price-denominator])]
     (format-money (/ highest-price highest-price-denominator) 4))))

(reg-sub
 ::asset-auction
 (fn [db _]
   (get-in db [:asset-page :auction])))

(reg-sub
 ::asset-auction-bids
 (fn [db _]
   (sort-by :bid-id #(compare %2 %1) (:auction-bids db))))

(reg-sub
 ::asset-auction-panel
 (fn [db _]))

