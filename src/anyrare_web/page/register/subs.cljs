(ns anyrare-web.page.register.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 ::referral
 (fn [db _]
   (:referral db)))

