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
   (let [higest-price (get-in db [:asset-page :auction :higest-price])
         higest-price-denominator (get-in db [:asset-page :auction :highest-price-denominator])]
     (format-money (/ higest-price higest-price-denominator) 4))))