(ns anyrare-web.error
  (:require
   [anyrare-web.env :as env]))

(def error-messages
  {:ethers
   {:failed-to-init-wallet-signer
    "1000: Failed to init wallet signer."
    :metamask-not-found
    "1001: Metamask not found."
    :failed-to-get-provider
    "1002: Failed to get provider."}})

(defn log [message & [err]]
  (when env/ENABLED_ERROR_LOG
    (.error js/console message)
    (when (some? err) (.error js/console err))))

