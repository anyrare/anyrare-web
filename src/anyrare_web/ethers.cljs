(ns anyrare-web.ethers
  (:require
   [re-frame.core :refer [dispatch subscribe reg-event-fx]]
   [kitchen-async.promise :as p]
   ["ethers" :refer [ethers]]
   [lambdaisland.fetch :as fetch]
   [anyrare-web.subs :as subs]
   [anyrare-web.abi :refer [contract-abi contract-address]]
   ;; [anyrare-web.events :as events]
   [anyrare-web.env :as env]
   [anyrare-web.lib.utils :refer [json->clj]]
   [anyrare-web.error :refer [log error-messages]]))

(def MAX_APPROVE_SPEND_LIMIT
  (.from (.-BigNumber ethers) "1000000000000000000000000000000"))

(def provider
  (try
    (new (.-JsonRpcProvider (.-providers ethers))
         (clj->js {:url env/CHAIN_URL})
         (clj->js {:chainId env/CHAIN_ID :name env/CHAIN_NAME}))
    (catch js/Error e
      (log (get-in error-messages [:ethers :failed-to-get-provider])))))

(def provider-metamask
  (try
    (new (.-Web3Provider (.-providers ethers)) (.-ethereum js/window))
    (catch js/Error e
      (log (get-in error-messages [:ethers :metamask-not-found])))))

;; (defn init-wallet-signer []
;;   (-> (p/let [_ (.send provider-metamask "eth_requestAccounts" [])
;;               signer (.getSigner provider-metamask)
;;               address (.getAddress signer)]
;;         (dispatch [::events/set-account-id address]))
;;       (p/catch*
;;        (fn [err]
;;          (log (get-in error-messages
;;                       [:ethers :failed-to-init-wallet-signer]) err)))))


(defn signer-address [callback]
  (p/let [_ (.send provider-metamask "eth_requestAccounts" [])
          signer (.getSigner provider-metamask)
          address (.getAddress signer)]
    (callback {:address address
               :signer signer})))

(def member-contract
  (new (.-Contract ethers)
       (:member contract-address)
       (clj->js (:member contract-abi))
       provider))

;; (defn private-key->public-key
;;   [private-key]
;;   (.log js/console private-key)
;;   (new (.Wallet ethers) private-key))

(defn number->BigNumber
  [number]
  (.from (.-BigNumber ethers) number))

;; (defn create-member []
;;   (init-wallet-signer))

;; (reg-event-fx
;;  ::create-member
;;  (fn [_ _]
;;    (p/let [_ (.send provider-metamask "eth_requestAccounts" [])
;;            signer (.getSigner provider-metamask)
;;            address (.getAddress signer)]
;;      (let [contract (new (.-Contract ethers)
;;                          (:member contract-address)
;;                          (clj->js (:member contract-abi))
;;                          signer)]
;;        (.log js/console provider-metamask)
;;        (p/let [tx (.isMember contract address)]
;;          (.log js/console tx))))))

(defn get-contract
  [address abi signer]
  (.log js/console (new (.-Contract ethers) address (clj->js abi) signer))
  (new (.-Contract ethers) address (clj->js abi) signer))

;; Member


(defn set-member
  [params callback]
  (p/let [_ (.send provider-metamask "eth_requestAccounts" [])
          signer (.getSigner provider-metamask)
          address (.getAddress signer)
          tx (.setMember (get-contract (:member contract-address)
                                       (:member contract-abi)
                                       signer)
                         address
                         (:referral params))]
    (callback {:result (js->clj tx)
               :address address})))


;; NFT


(defn nft-mint
  [params callback]
  (.log js/console "nft-mint")
  (p/let [_ (.send provider-metamask "eth_requestAccounts" [])
          signer (.getSigner provider-metamask)
          address (.getAddress signer)
          tx (.mint (get-contract (:nft-factory contract-address)
                                  (:nft-factory contract-abi)
                                  signer)
                    (:founder-address params)
                    (:custodian-address params)
                    (:token-uri params)
                    (:max-weight params)
                    (:founder-weight params)
                    (:founder-redeem-weight params)
                    (:founder-general-fee params)
                    (:audit-fee params))]
    (callback {:result (js->clj tx)})))

(defn nft-custodian-sign
  [params callback]
  (p/let [_ (.send provider-metamask "eth_requestAccounts" [])
          signer (.getSigner provider-metamask)
          address (.getAddress signer)
          tx (.custodianSign (get-contract (:nft-factory contract-address)
                                           (:nft-factory contract-abi)
                                           signer)
                             (:token-id params)
                             (:custodian-weight params)
                             (:custodian-general-fee params)
                             (:custodian-redeem-weight params))]
    (callback {:result (js->clj tx)})))

(defn nft-pay-fee-and-claim-token
  [params callback]
  (p/let [_ (.send provider-metamask "eth_requestAccounts" [])
          signer (.getSigner provider-metamask)
          address (.getAddress signer)
          ;; Check spend limit
          approve-spend (.approve (get-contract (:ara-token contract-address)
                                                (:ara-token contract-abi)
                                                signer)
                                  (:nft-factory contract-address)
                                  MAX_APPROVE_SPEND_LIMIT)
          tx (.payFeeAndClaimToken (get-contract (:nft-factory contract-address)
                                                 (:nft-factory contract-abi)
                                                 signer)
                                   (:token-id params))]
    (callback {:result (js->clj tx)})))

(defn nft-current-token-id
  [callback]
  (p/let [_ (.send provider-metamask "eth_requestAccounts" [])
          signer (.getSigner provider-metamask)
          address (.getAddress signer)
          tx (.getCurrentTokenId (get-contract (:nft-factory contract-address)
                                               (:nft-factory contract-abi)
                                               signer))]
    (callback {:result (js->clj tx)})))

(def lisa-image "https://upload.wikimedia.org/wikipedia/commons/e/e9/Blackpink_Lisa_Vogue_2021_%281%29.jpg")

(defn nft-by-id
  [params callback]
  (p/let [_ (.send provider-metamask "eth_requestAccounts" [])
          signer (.getSigner provider-metamask)
          address (.getAddress signer)
          tx (.nfts (get-contract (:nft-factory contract-address)
                                  (:nft-factory contract-abi)
                                  signer)
                    (:token-id params))
          tx-token-uri (.tokenURI (get-contract (:nft-factory contract-address)
                                                (:nft-factory contract-abi)
                                                signer)
                                  (:token-id params))
          tx-token-uri-data (fetch/get tx-token-uri)]
    (callback (js->clj
               {:token-id (.-tokenId tx)
                :token-uri tx-token-uri
                :token-uri-data (json->clj (:body tx-token-uri-data))
                :founder {:address (.. tx -addr -founder)
                          :username (.. tx -addr -founder)
                          :thumbnail lisa-image}
                :auditor {:address (.. tx -addr -auditor)
                          :username (.. tx -addr -auditor)
                          :thumbnail lisa-image}
                :custodian {:address (.. tx -addr -custodian)
                            :username (.. tx -addr -custodian)
                            :thumbnail lisa-image}
                :owner {:address (.. tx -addr -owner)
                        :username (.. tx -addr -owner)
                        :thumbnail lisa-image}
                :status {:auction (.. tx -status -auction)
                         :buy-it-now (.. tx -status -buyItNow)
                         :claim (.. tx -status -claim)
                         :custodian-sign (.. tx -status -custodian-sign)
                         :freeze (.. tx -status -freeze)
                         :lock-in-collection (.. tx -status -lockInCollection)
                         :offer (.. tx -status -offer)
                         :redeem (.. tx -status -redeem)}
                :fee {:max-weight (.. tx -fee -maxWeight)
                      :audit-fee (.. tx -fee -auditFee)
                      :custodian-general-fee (.. tx -fee -custodianGeneralFee)
                      :custodian-redeem-weight (.. tx -fee -custodianRedeemWeight)
                      :custodian-weight (.. tx -fee -custodianWeight)
                      :founder-general-fee (.. tx -fee -founderGeneralFee)
                      :founder-redeem-weight (.. tx -fee -founderRedeemWeight)
                      :founder-weight (.. tx -fee -founderWeight)
                      :mint-fee (.. tx -fee -mintFee)}
                :total-auction (.. tx -totalAuction)
                :buy-it-now {:owner (.. tx -buyItNow -owner)
                             :value (.. tx -buyItNow -value)}
                :bid-id (.. tx -bidId)
                :offer {:bidder (.. tx -offer -bidder)
                        :close-offer-timestamp (.. tx -offer -closeOfferTimestamp)
                        :open-offer-timestamp (.. tx -offer -openOfferTimestamp)
                        :owner (.. tx -offer -owner)
                        :value (.. tx -offer -value)}
                :redeem-timestamp (.. tx -offer -redeemTimestamp)}))))

(defn nft-token-uri
  [params callback]
  (p/let [_ (.send provider-metamask "eth_requestAccounts" [])
          signer (.getSigner provider-metamask)
          address (.getAddress signer)
          tx (.tokenURI (get-contract (:nft-factory contract-address)
                                      (:nft-factory contract-abi)
                                      signer)
                        (:token-id params))]
    (callback {:result (js->clj tx)})))



;; (reg-event-fx
;;  ::create-member
;;  (fn [_ _]
;;    (p/let [_ (.send provider-metamask "eth_requestAccounts" [])
;;            signer (.getSigner provider-metamask)
;;            address (.getAddress signer)
;;            tx (.setMember (get-contract (:member contract-address)
;;                                         (:member contract-abi)
;;                                         signer)
;;                           address "0x5A81399116Ad2e89E45b31c4e1A67C7F254F58f3")]
;;      tx)))

           ;; tx (.set-member contract (clj-js {:addr address :referral "0xa7Fe534827E20785FDC4Ea7F674B461b50139e56"}))]
     ;; (.log js/console (new (.-Contract ethers)
     ;;                     (:member contract-address)
     ;;                     (clj->js (:member contract-abi))
     ;;                     signer)))))









