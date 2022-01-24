(ns app.subs
  (:require
   [re-frame.core :refer [reg-sub]]))

(reg-sub
 ::active-page
 (fn [db _]
   (:active-page db)))

(reg-sub
 ::i18n
 (fn [db _]
   (:i18n db)))

(reg-sub
 ::account-id
 (fn [db _]
   (:account-id db)))
