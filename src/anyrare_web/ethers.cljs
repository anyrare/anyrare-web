(ns anyrare-web.ethers
  (:require
   [re-frame.core :refer [dispatch subscribe reg-event-fx]]
   [kitchen-async.promise :as p]
   ["ethers" :refer [ethers]]
   [anyrare-web.subs :as subs]
   [anyrare-web.abi :refer [contract-abi contract-address]]
   [anyrare-web.events :as events]
   [anyrare-web.env :as env]
   [anyrare-web.error :refer [log error-messages]]))

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

(defn init-wallet-signer []
  (-> (p/let [_ (.send provider-metamask "eth_requestAccounts" [])
              signer (.getSigner provider-metamask)
              address (.getAddress signer)]
        (dispatch [::events/set-account-id address]))
      (p/catch*
       (fn [err]
         (log (get-in error-messages
                      [:ethers :failed-to-init-wallet-signer]) err)))))

(def member-contract
  (new (.-Contract ethers)
       (:member contract-address)
       (clj->js (:member contract-abi))
       provider))

(defn is-member []
  (-> (.isMember member-contract "0xb5914c8a28295E400B05EF63aD56E436Af812b64")
      (p/then (fn [x] (js/console.log x)))))

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

(reg-event-fx
 ::create-member
 (fn [_ _]
   (p/let [_ (.send provider-metamask "eth_requestAccounts" [])
           signer (.getSigner provider-metamask)
           address (.getAddress signer)]
     (let [contract (new (.-Contract ethers)
                         (:member contract-address)
                         (clj->js (:member contract-abi))
                         signer)]
       (.log js/console provider-metamask)
       (p/let [tx (.setMember contract address "0x5A81399116Ad2e89E45b31c4e1A67C7F254F58f3")]
         (.log js/console tx))))))

           ;; tx (.set-member contract (clj-js {:addr address :referral "0xa7Fe534827E20785FDC4Ea7F674B461b50139e56"}))]
     ;; (.log js/console (new (.-Contract ethers)
     ;;                     (:member contract-address)
     ;;                     (clj->js (:member contract-abi))
     ;;                     signer)))))





