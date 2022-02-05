(ns anyrare-web.core
  (:require
   [reagent.dom :refer [render unmount-component-at-node]]
   [re-frame.core :refer [dispatch-sync clear-subscription-cache!]]
   [anyrare-web.routes :as routes]
   [anyrare-web.events :as events]
   [anyrare-web.views :refer [main-app]]
   [anyrare-web.config :as config]))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (unmount-component-at-node root-el)
    (render [main-app] root-el)))

(defn ^:export init []
  (routes/start!)
  (dispatch-sync [::events/initialize-db])
  (config/init-gql)
  (dispatch-sync [::events/set-i18n :th])
  (dev-setup)
  (mount-root))
