(ns anyrare-web.routes
  (:require [bidi.bidi :as bidi]
            [pushy.core :as pushy]
            [re-frame.core :refer [dispatch reg-fx]]
            [anyrare-web.events :as events]))

(def routes
  ["/" {"" :home
        "asset" :asset
        ["register/" :code] :register
        "asset/" {"mint" :asset-mint}}])

(def routes-guard [:account])

(defn routes-check [r] :not-found)

(def history
  (let [dispatch #(dispatch [::events/set-active-page
                             {:page (:handler %)
                              :route-params (:route-params %)}])
        match #(bidi/match-route routes %)]
    (pushy/pushy dispatch match)))

(defn start! [] (pushy/start! history))

(def url-for (partial bidi/path-for routes))

(defn navigate! [handler] (pushy/set-token! history (url-for handler)))

(reg-fx
 :navigate
 (fn [handler] (navigate! handler)))


