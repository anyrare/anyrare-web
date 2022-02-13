(ns anyrare-web.views
  (:require [re-frame.core :refer [subscribe]]
            [anyrare-web.component.header :refer [header]]
            [anyrare-web.page.home :refer [home]]
            [anyrare-web.page.admin.views :refer [admin]]
            [anyrare-web.page.asset.views :refer [asset]]
            [anyrare-web.page.asset-mint :refer [asset-mint]]
            [anyrare-web.page.register.views :refer [register]]
            [anyrare-web.styles :as styles]
            [anyrare-web.subs :as subs]))

(defn pages [page-name]
  (case page-name
    :home [home]
    :admin [admin]
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

