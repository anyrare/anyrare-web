(ns anyrare-web.page.register
  (:require
   [re-frame.core :refer [subscribe dispatch]]
   [anyrare-web.events :as events]
   [anyrare-web.subs :as subs]
   [anyrare-web.ethers :as ethers]))

(defn register []
  (let [referral @(subscribe [::subs/referral])]
    [:div "Register"
     ;; (.log js/console (ethers/init-wallet-signer))
     (when some? (:address referral)
           [:button {:class [:w-24 :h-12 :bg-red-300]
                     :on-click #(dispatch [::events/create-member
                                           (:address referral)])}
            "Connect Wallet"])]))



