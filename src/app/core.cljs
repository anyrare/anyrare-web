(ns app.core
  (:require
   [reagent.dom :refer [render unmount-component-at-node]]
   [re-frame.core :refer [dispatch-sync clear-subscription-cache!]]
   [re-graph.core :as re-graph]
   [app.router :as router]
   [app.events :as events]
   [app.views :as views]
   [app.config :as config]
   ))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (unmount-component-at-node root-el)
    (render [views/main-app] root-el)))

(defn init-gql []
  (dispatch-sync [::re-graph/init  
    {:ws {:url "ws://localhost:8080/graphql"
          :supported-operations #{:subscribe}}
     :http {:url "http://localhost:8080"}}                             
  ]))

(defn ^:export init []
  (router/start!)
  (dispatch-sync [::events/initialize-db])
  (init-gql)
  (dev-setup)
  (mount-root))
