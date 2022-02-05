(ns anyrare-web.views
  (:require
   [re-frame.core :refer [subscribe]]
   [anyrare-web.component.header :refer [header]]
   [anyrare-web.page.home :refer [home]]
   [anyrare-web.page.asset :refer [asset]]
   [anyrare-web.page.register :refer [register]]
   [anyrare-web.styles :as styles]
   [anyrare-web.subs :as subs]))

(defn pages [page-name]
  (case page-name
    :home [home]
    :asset [asset]
    :register [register]
    [home]))

(defn main-app
  []
  (let [active-page @(subscribe [::subs/active-page])]
    [:div
     [header]
     [:div {:class [:mx-auto :max-w-screen-xl]}
      [pages active-page]]]))
