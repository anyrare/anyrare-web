(ns app.lib.ethers
  (:require
   [re-frame.core :refer [dispatch subscribe]]
   [kitchen-async.promise :as p]
   ["ethers" :refer [ethers]]
   [app.subs :as subs]
   [app.abi :refer [contract-abi contract-address]]
   [app.events :as events]
   [app.env :as env]
   [app.error :refer [error-log error-messages]]))

(def provider
  (try
    (new (.-JsonRpcProvider (.-providers ethers))
         (clj->js {:url env/CHAIN_URL})
         (clj->js {:chainId env/CHAIN_ID :name env/CHAIN_NAME}))
    (catch js/Error e
      (error-log (get-in error-messages [:ethers :failed-to-get-provider])))))

(def provider-metamask
  (try
    (new (.-Web3Provider (.-providers ethers)) (.-ethereum js/window))
    (catch js/Error e
      (error-log (get-in error-messages [:ethers :metamask-not-found])))))

(defn init-wallet-signer []
  (-> (p/let [_ (.send provider-metamask "eth_requestAccounts" [])
              signer (.getSigner provider-metamask)
              address (.getAddress signer)]
        (dispatch [::events/set-account-id address]))
      (p/catch*
       (fn [err]
         (error-log
          (get-in error-messages [:ethers :failed-to-init-wallet-signer]) err)))))

(def member-contract
  (new (.-Contract ethers) (:member contract-address) (clj->js (:member contract-abi)) provider))

(defn is-member []
  (-> (.isMember member-contract "0xb5914c8a28295E400B05EF63aD56E436Af812b64")
      (p/then (fn [x] (js/console.log x)))))
  ;; (let [account-id @(subscribe [::subs/account-id])]
  ;;   (-> (.isMember member-contract account-id)
  ;;       (p/then (fn [x] (js/console.log x)))
  ;;       (p/catch* (fn [err] (js/console.error err))))))








