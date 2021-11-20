(ns app.asset.subs
  (:require
   [re-frame.core :refer [reg-sub]]))

(reg-sub
 ::asset
 (fn [db]
   (-> db (:asset))))

(reg-sub
 ::tab-active-index
 (fn [db]
   (get-in db [:asset :tab-active-index])))