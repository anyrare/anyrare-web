(ns anyrare-web.subs
  (:require
   [re-frame.core :refer [reg-sub]]
   [anyrare-web.ethers :as ethers]))

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
 ::signer
 (fn [db _]
   (:signer db)))

(reg-sub
 ::balance
 (fn [db _]
   (:balance db)))
