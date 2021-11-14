(ns app.asset.subs
  (:require
   [re-frame.core :refer [reg-sub]]))

(reg-sub
 ::title
 (fn [db]
   (-> db
       (:asset)
       (:title))))