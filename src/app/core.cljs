(ns app.core
  (:require
   [reagent.dom :refer [render unmount-component-at-node]]
   [re-frame.core :refer [dispatch-sync clear-subscription-cache!]]
   [app.events :as events]
   [app.routes :as routes]
   [app.views :refer [main-app]]
   [app.config :as config]))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (unmount-component-at-node root-el)
    (render [main-app] root-el)))

(defn init []
  (routes/start!)
  (dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
