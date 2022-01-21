(ns app.env
  (:require-macros [adzerk.env :as env]))

(env/def GQL_SERVER_HTTP "https://graphql-dev.shoplive.dev")
(env/def GQL_SERVER_WS "wss://graphql-dev.shoplive.dev/graphql")
