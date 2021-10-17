(ns app.router
  (:require [bidi.bidi :as bidi]
            [pushy.core :as pushy]
            [re-frame.core :refer [dispatch]]))

(def routes
  ["/" {"" :home
        "asset" :asset
        "explorer" :explorer
        "profile" :profile
        "following" :following
        "activity" :activity}])

(def history
  (let [dispatch #(dispatch [:set-active-page {:page (:handler %)
                                               :slug (get-in % [:route-params :slug])}])
        match #(bidi/match-route routes %)]
  (pushy/pushy dispatch match)))

(defn start! [] (pushy/start! history))

(def url-for (partial bidi/path-for routes))

(defn set-token! [token] (pushy/set-token! history token))