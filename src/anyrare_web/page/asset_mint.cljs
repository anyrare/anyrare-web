(ns anyrare-web.page.asset-mint
  (:require
   [re-frame.core :refer [subscribe dispatch]]
   [anyrare-web.events :as events]
   [anyrare-web.subs :as subs]
   [anyrare-web.ethers :as ethers]))

(defn asset-mint []
  [:div "Mint Asset"
   [:div
    [:button {:class [:w-24 :h-12 :bg-red-300]
              :on-click #(dispatch
                          [::events/mint-nft
                           {:founder-address "0xb30Ec3b60A5d5ca4822E950942B75eb03D8BBF15"
                            :custodian-address "0x16e498ff4Bfb297b79a9E6C06142b593Ddb3f4F5"
                            :token-uri "https://examle/metadata.json"
                            :max-weight 1000000
                            :founder-weight 100000
                            :founder-redeem-weight 300000
                            :founder-general-fee 3500
                            :audit-fee 1000}])}
    ;; (.log js/console (ethers/number->BigNumber 10000))
     "Mint"]]
   [:div [:button {:class [:w-24 :h-12 :bg-yellow-300]
                   :on-click #(dispatch
                               [::events/mint-nft-custodian-sign
                                {:token-id 15
                                 :custodian-weight 2000
                                 :custodian-general-fee 2000
                                 :custodian-redeem-weight 2000}])}
          "custodian sign"]]
   [:div [:button {:class [:w-24 :h-12 :bg-green-300]
                   :on-click #(dispatch
                               [::events/nft-current-token-id])}
          "currentTokenId"]]])



