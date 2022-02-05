(ns anyrare-web.home.views
  (:require
   [kitchen-async.promise :as p]
   ["ethers" :refer [ethers]]
   [anyrare-web.lib.ethers :refer [init-wallet-signer is-member]]))

;; (def member-contract
;;   (.Contract js/ethers
;;              "0x1444DCDAb2C312C1C578AA435B099F79Bfc6E7f0"
;;              member-abi
;;              (.provider js/ethers)))

;; (def provider (.JsonRpcProvider (.providers js/ethers.) "https://testnet.anyrare.network"))
;; (def provider
;;   (new (.JsonRpcProvider (.-providers ethers) {:url "https://testnet.anyrare.network"})))

;; (def provider
;;   (new (.-JsonRpcProvider (.-providers ethers))
;;        (clj->js {:url "https://testnet.anyrare.network"})
;;        (clj->js {:chainId 1687 :name "anyrare"})))

;; (def provider-metamask
;;   (new (.-Web3Provider (.-providers ethers)) (.-ethereum js/window)))

;; (def signer (.getSigner provider-metamask))

;; (.log js/console provider)
;; (.log js/console provider-metamask)
;; (-> (.send provider-metamask "eth_requestAccounts" []z)
;;     (p/then (fn [x]
;;               (-> signer
;;                   (p/then (fn [_signer]
;;                             (-> (.getAddress _signer)
;;                                 (p/then (fn [x] (.log js/console x))))))
;;                   (p/catch* (fn [err] (js/console.error err)))))))

;; (def member-contract
;;   (new (.-Contract ethers)
;;        "0x1444DCDAb2C312C1C578AA435B099F79Bfc6E7f0"
;;        (clj->js member-abi)
;;        provider))

;; (.log js/console member-contract)

;; (def user0 "0x1E6C09C1D3Ac114752dbBD3E4e91b0b167ddee84")

;; (-> (.getReferral member-contract user0)
;;     (p/then (fn [x] (js/console.log x)))
;;     (p/catch* (fn [err] (js/console.error err))))

;; (-> (.isMember member-contract user0)
;;     (p/then (fn [x] (js/console.log x)))
;;     (p/catch* (fn [err] (js/console.error err))))

(defn home [] [:div "Home"])

(init-wallet-signer)
(is-member)
