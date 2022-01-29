(ns anyrare-web.routes
  (:require [bidi.bidi :as bidi]
            [pushy.core :as pushy]
            [re-frame.core :refer [dispatch]]
            [anyrare-web.events :as events]))

(def routes
  ["/" {"" :home
        "asset" :asset
        "register" :register}])

(def history
  (let [dispatch #(dispatch [::events/set-active-page {:page (:handler %)}])
        match #(bidi/match-route routes %)]
  (pushy/pushy dispatch match)))

(defn start! [] (pushy/start! history))

(def url-for (partial bidi/path-for routes))

(defn set-token! [token] (pushy/set-token! history token))
