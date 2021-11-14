(ns app.asset.subs
  (:require
   [re-frame.core :refer [reg-sub]]))

(reg-sub
 ::asset
 (fn [db]
   (-> db (:asset))))