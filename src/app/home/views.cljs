(ns app.home.views
  (:require
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
(def provider
  (new (.-JsonRpcProvider (.-providers ethers)) "https://testnet.anyrare.network"))

(def member-contract
  (new (.-Contract ethers)
       "0x1444DCDAb2C312C1C578AA435B099F79Bfc6E7f0"
       (clj->js member-abi)
       provider))

(.log js/console provider)
(.log js/console member-contract)
(.log js/console
      (.getReferral member-contract "0x1E6C09C1D3Ac114752dbBD3E4e91b0b167ddee84"))


(defn home [] [:div "Home"])













