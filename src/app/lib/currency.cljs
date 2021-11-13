(ns app.lib.currency)

(defn convert-ara-to-thb [value] (* value 13.2313))
(defn convert-ara-to-usd [value] (* value 4.23))

(defn get-local-currency []
  (or (.getItem (.-localStorage js/window) "localCurrency") "thb"))

(defn convert-ara-to-local-currency [value]
  (-> (get-local-currency)
      (case
       "thb" (convert-ara-to-thb value)
       "usd" (convert-ara-to-usd value))
   ))