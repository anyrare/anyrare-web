(ns anyrare-web.lib.currency
  (:require [anyrare-web.lib.localstorage :refer [get-local-currency]]))

(defn convert-ara-to-thb [value] (* value 13.2313))
(defn convert-ara-to-usd [value] (* value 4.23))


(defn convert-ara-to-local-currency [value]
  (-> (get-local-currency)
      (case
       "thb" (convert-ara-to-thb value)
       "usd" (convert-ara-to-usd value))
   ))
