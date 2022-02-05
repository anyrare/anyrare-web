(ns anyrare-web.ethers
  (:require
   [re-frame.core :refer [dispatch subscribe reg-event-fx]]
   [kitchen-async.promise :as p]
   ["ethers" :refer [ethers]]
   [anyrare-web.subs :as subs]
   [anyrare-web.abi :refer [contract-abi contract-address]]
   ;; [anyrare-web.events :as events]
   [anyrare-web.env :as env]
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
  (.log js/console (:token-id params))
  (p/let [_ (.send provider-metamask "eth_requestAccounts" [])
          signer (.getSigner provider-metamask)
          address (.getAddress signer)
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












