(ns anyrare-web.page.register
  (:require
   [re-frame.core :refer [subscribe dispatch]]
   [anyrare-web.events :as events]
   [anyrare-web.subs :as subs]
   [anyrare-web.ethers :as ethers]))

(defn register []
  [:div "Register"
   ;; (.log js/console (ethers/init-wallet-signer))
   [:button {:class [:w-24 :h-12 :bg-red-300]
             :on-click #(dispatch [::events/create-member
                                   "0x5A81399116Ad2e89E45b31c4e1A67C7F254F58f3"])}
    "Connect Wallet"]])

