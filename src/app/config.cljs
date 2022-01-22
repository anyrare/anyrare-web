(ns app.config
  (:require
   [re-graph.core :as re-graph]
   [re-frame.core :refer [dispatch]]
   [app.env :as env]))

(def debug?
  ^boolean goog.DEBUG)

(defn init-gql []
  (dispatch
   [::re-graph/init
    {:ws {:url env/GQL_SERVER_WS
          :supported-operations #{:subscribe}}
     :http {:url env/GQL_SERVER_HTTP}}]))
