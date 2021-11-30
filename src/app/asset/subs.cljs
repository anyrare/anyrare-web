(ns app.asset.subs
  (:require
   [re-frame.core :refer [reg-sub]]
   [app.lib.format :refer [format-money]]))

(reg-sub
 ::asset-title
 (fn [db _]
   (get-in db [:asset-page :title])))

(reg-sub
 ::asset-description
 (fn [db _]
   (get-in db [:asset-page :description])))

(reg-sub
 ::asset-auction-higest-price
 (fn [db _]
   (let [highest-price (get-in db [:asset-page :auction :highest-price])
         highest-price-denominator (get-in db [:asset-page :auction :highest-price-denominator])]
     (format-money (/ highest-price highest-price-denominator) 4))))