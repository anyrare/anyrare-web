(ns app.subs
  (:require
   [re-frame.core :refer [reg-sub]]))

(reg-sub
 :active-page
 (fn [db _]
   (:active-page db)))

(reg-sub
 ::name
 (fn [db]
   (:name db)))

(reg-sub
 ::test
 (fn [db]
   (:test db)))