(ns anyrare-web.lib.utils)

(defn json->clj [e]
  (js->clj (-> e (js/JSON.stringify) (js/JSON.parse)) :keywordize-keys true))
