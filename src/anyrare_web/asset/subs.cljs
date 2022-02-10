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
   {:description (get-in db [:asset-page :tokenURIData :description :th])
    :address (get-in db [:asset-page :address])
    :owner (get-in db [:asset-page :ownerAddress])
    :founder (get-in db [:asset-page :founder])}))

(reg-sub
 ::asset-auditor
 (fn [db _]
   {:address (get-in db [:asset-page :auditorAddress])
    :thumbnail (get-in db [:asset-page :auditor :thumbnail])
    :username (get-in db [:asset-page :auditor :username])
    :report (get-in db [:asset-page :tokenURIData :auditor :report])
    :assets (get-in db [:asset-page :tokenURIData :auditor :assets])
    :timestamp (get-in db [:asset-page :tokenURIData :auditor :timestamp])}))

(reg-sub
 ::asset-custodian
 (fn [db _]
   {:address (get-in db [:asset-page :custodianAddress])
    :thumbnail (get-in db [:asset-page :custodian :thumbnail])
    :username (get-in db [:asset-page :custodian :username])}))

(reg-sub
 ::asset-royalty
 (fn [db _]
   {:founder-fee (format-money
                  (/ (get-in db [:asset-page :feeFounderWeight])
                     (get-in db [:asset-page :feeMaxWeight])) 4)
    :custodian-fee (format-money
                    (/ (get-in db [:asset-page :feeCustodianWeight])
                       (get-in db [:asset-page :feeMaxWeight])) 4)}))

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
 ::asset-auction-info
 (fn [db _]
   (let [auction (get-in db [:asset-page :auction])
         highest-price (auction :highest-price)
         highest-price-denominator (auction :highest-price-denominator)]
     {:highest-price (format-money (/ highest-price highest-price-denominator) 4)
      :total-bid (auction :total-bid)
      :start-date (auction :start-date)
      :end-date (auction :end-date)
      :highest-bidder (auction :highest-bidder)})))

(reg-sub
 ::asset-auction-bids
 (fn [db _]
   (let [bids (get-in db [:asset-page :auction :bids])]
     (map (fn [r] {:bid-id (r :bid-id)
                   :price (format-money (/ (r :price) (r :price-denominator)) 4)
                   :name (r :name)
                   :thumbnail (r :thumbnail)
                   :address (r :address)
                   :total-bid (r :total-bid)
                   :date (r :date)}) bids))))

(reg-sub
 ::asset-auction-panel
 (fn [db _]))


