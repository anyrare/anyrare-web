(ns anyrare-web.config
  (:require
   [re-graph.core :as re-graph]
   [re-frame.core :refer [dispatch]]
   [anyrare-web.env :as env]))

(def debug?
  ^boolean goog.DEBUG)

(defn init-gql []
  (dispatch
   [::re-graph/init
    {:ws {:url env/GRAPHQL_SERVER_WS
          :supported-operations #{:subscribe}}
     :http {:url env/GRAPHQL_SERVER_HTTP}}]))
