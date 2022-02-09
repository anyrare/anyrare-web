(ns anyrare-web.lib.utils)

(defn parse-json [e]
  (js->clj (js/JSON.parse e) :keywordize-keys true))
