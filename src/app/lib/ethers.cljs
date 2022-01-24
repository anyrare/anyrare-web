(ns app.lib.ethers
  (:require
   [re-frame.core :refer [dispatch]]
   [kitchen-async.promise :as p]
   ["ethers" :refer [ethers]]
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


