(ns anyrare-web.views
  (:require [re-frame.core :refer [subscribe]]
            [anyrare-web.component.header :refer [header]]
            [anyrare-web.page.home :refer [home]]
            [anyrare-web.page.tool.views :refer [tool]]
            [anyrare-web.page.tool.mint-asset :refer [tool-mint-asset]]
            [anyrare-web.page.tool.custodian-sign :refer [tool-custodian-sign]]
            [anyrare-web.page.tool.founder-sign :refer [tool-founder-sign]]
            [anyrare-web.page.tool.collection :refer [tool-collection]]
            [anyrare-web.page.tool.open-proposal :refer [tool-open-proposal]]
            [anyrare-web.page.tool.vote-proposal :refer [tool-vote-proposal]]
            [anyrare-web.page.asset.views :refer [asset]]
            [anyrare-web.page.asset-mint :refer [asset-mint]]
            [anyrare-web.page.register.views :refer [register]]
            [anyrare-web.styles :as styles]
            [anyrare-web.subs :as subs]))

(defn pages [page-name]
  (case page-name
    :home [home]
    :tool [tool]
    :tool-mint [tool-mint-asset]
    :tool-custodian-sign [tool-custodian-sign]
    :tool-founder-sign [tool-founder-sign]
    :tool-collection [tool-collection]
    :tool-open-proposal [tool-open-proposal]
    :tool-vote-proposal [tool-vote-proposal]
    :asset [asset]
    :register [register]
    :asset-mint [asset-mint]
    [home]))

(defn main-app
  []
  (let [active-page @(subscribe [::subs/active-page])]
    [:div
     [header]
     [:div {:class [:mx-auto :max-w-screen-xl]}
      [pages active-page]]]))

