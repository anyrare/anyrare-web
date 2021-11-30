(ns app.asset.subs
  (:require
   [re-frame.core :refer [reg-sub]]))

(reg-sub
 ::asset-title
 (fn [db _]
   (get-in db [:asset-page :title])))

(reg-sub
 ::asset-description
 (fn [db _]
   (get-in db [:asset-page :description])))