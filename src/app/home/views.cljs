(ns app.home.views
  (:require
   [kitchen-async.promise :as p]
   ["ethers" :refer [ethers]]))

(def member-abi
  [{:inputs [{:internalType "address", :name "root", :type "address"}]
    :stateMutability "nonpayable"
    :type "constructor"}
   {:inputs [{:internalType "address", :name "addr", :type "address"}]
    :name "getReferral"
    :outputs [{:internalType "address", :name "", :type "address"}]
    :stateMutability "view"
    :type "function"}
   {:inputs
    [{:internalType "address", :name "account", :type "address"}]
    :name "isMember"
    :outputs [{:internalType "bool", :name "", :type "bool"}]
    :stateMutability "view"
    :type "function"}
   {:inputs [{:internalType "address", :name "", :type "address"}]
    :name "members"
    :outputs
    [{:internalType "address", :name "referral", :type "address"}]
    :stateMutability "view"
    :type "function"}
   {:inputs
    [{:internalType "address", :name "addr", :type "address"}
     {:internalType "address", :name "referral", :type "address"}]
    :name "setMember"
    :outputs []
    :stateMutability "nonpayable"
    :type "function"}])

;; (def member-contract
;;   (.Contract js/ethers
;;              "0x1444DCDAb2C312C1C578AA435B099F79Bfc6E7f0"
;;              member-abi
;;              (.provider js/ethers)))

;; (def provider (.JsonRpcProvider (.providers js/ethers.) "https://testnet.anyrare.network"))
;; (def provider
;;   (new (.JsonRpcProvider (.-providers ethers) {:url "https://testnet.anyrare.network"})))

(def provider
  (new (.-JsonRpcProvider (.-providers ethers))
       (clj->js {:url "https://testnet.anyrare.network"})
       (clj->js {:chainId 1687 :name "anyrare"})))

(def provider-metamask
  (new (.-Web3Provider (.-providers ethers)) (.-ethereum js/window)))

(def signer (.getSigner provider-metamask))

(.log js/console provider)
(.log js/console provider-metamask)
(-> (.send provider-metamask "eth_requestAccounts" [])
    (p/then (fn [x]
              (-> signer
                  (p/then (fn [_signer]
                            (-> (.getAddress _signer)
                                (p/then (fn [x] (.log js/console x))))))
                  (p/catch* (fn [err] (js/console.error err)))))))

(def member-contract
  (new (.-Contract ethers)
       "0x1444DCDAb2C312C1C578AA435B099F79Bfc6E7f0"
       (clj->js member-abi)
       provider))

(.log js/console member-contract)

(def user0 "0x1E6C09C1D3Ac114752dbBD3E4e91b0b167ddee84")

(-> (.getReferral member-contract user0)
    (p/then (fn [x] (js/console.log x)))
    (p/catch* (fn [err] (js/console.error err))))

(-> (.isMember member-contract user0)
    (p/then (fn [x] (js/console.log x)))
    (p/catch* (fn [err] (js/console.error err))))

(defn home [] [:div "Home"])


















































































