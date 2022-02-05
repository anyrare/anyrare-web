(ns anyrare-web.lib.localstorage)

(defn get-lang [] (or (.getItem (.-localStorage js/window) "lang") "th"))

(defn get-local-currency []
  (or (.getItem (.-localStorage js/window) "localCurrency") "thb"))
