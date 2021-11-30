(ns app.views
  (:require
   [re-frame.core :refer [subscribe]]
   [app.component.header :refer [header]]
   [app.home.views :refer [home]]
   [app.asset.views :refer [asset]]
   [app.styles :as styles]
   [app.subs :as subs]))

(defn pages [page-name]
  (case page-name
    :home [home]
    :asset [asset]
    [home]))

(defn main-app
  []
  (let [active-page @(subscribe [::subs/active-page])]
    [:div
     [header]
     [:div active-page]
     [pages active-page]]))