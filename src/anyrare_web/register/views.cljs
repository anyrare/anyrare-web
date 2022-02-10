(ns anyrare-web.register.views
  (:require
   [re-frame.core :refer [subscribe dispatch]]
   [anyrare-web.register.events :as events]
   [anyrare-web.register.subs :as subs]
   [anyrare-web.ethers :as ethers]))

(defn register []
  (let [referral @(subscribe [::subs/referral])]
    [:div
     (when (some? referral)
           [:button {:class [:w-24 :h-12 :bg-red-300]
                     :on-click #(dispatch [::events/create-member
                                           {:referral referral
                                            :username "account12"
                                            :thumbnail "https://6.viki.io/image/a5b61f9583294ebfb6d0fe3686a8727f/dummy.jpeg?s=900x600&e=t"}])}
            "Register"])]))

