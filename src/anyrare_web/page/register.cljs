(ns anyrare-web.page.register
  (:require
   [re-frame.core :refer [subscribe dispatch]]
   [anyrare-web.events :as events]
   [anyrare-web.subs :as subs]
   [anyrare-web.ethers :as ethers]))

(defn register []
  [:div "Register"
   (.log js/console (ethers/init-wallet-signer))
   [:button {:class [:w-24 :h-12 :bg-red-300]
             :on-click #(dispatch [::ethers/create-member])}
    "Connect Wallet"]])

