(ns app.asset.subs
  (:require
   [re-frame.core :refer [reg-sub]]
   [app.lib.format :refer [format-money]]))

(reg-sub
 ::asset-title
 (fn [db _]
   (get-in db [:asset-page :title])))

(reg-sub
 ::asset-detail
 (fn [db _]
   {:description (get-in db [:asset-page :description])
    :address (get-in db [:asset-page :address])
    :owner (get-in db [:asset-page :owner])
    :founder (get-in db [:asset-page :founder])}))

(reg-sub
 ::asset-auditor
 (fn [db _]
   {:auditor (get-in db [:asset-page :auditor])}))

(reg-sub
 ::asset-custodian
 (fn [db _]
   {:custodian (get-in db [:asset-page :custodian])}))

(reg-sub
 ::asset-royalty
 (fn [db _]
   {:founder-fee (format-money
                  (/ (get-in db [:asset-page :founder :fee])
                     (get-in db [:asset-page :founder :fee-denominator])) 4)
    :custodian-fee (format-money
                    (/ (get-in db [:asset-page :custodian :fee])
                       (get-in db [:asset-page :custodian :fee-denominator])) 4)}))

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