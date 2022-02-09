(ns anyrare-web.subs
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

(reg-sub
 ::referral
 (fn [db _]
   (:referral db)))

(reg-sub
 ::asset
 (fn [db _]
   (:asset db)))
