(ns app.views
  (:require
   [re-frame.core :refer [subscribe]]
   [tailwind-hiccup.core :refer [tw]]
   [app.component.header :refer [header]]
   [app.home.views :refer [home]]
   [app.asset.views :refer [asset]]
   [app.explorer.views :refer [explorer]]
   [app.profile.views :refer [profile]]
   [app.following.views :refer [following]]
   [app.activity.views :refer [activity]]))

(defn pages [page-name]
  (case page-name
    :home [home]
    :asset [asset]
    :explorer [explorer]
    :profile [profile]
    :following [following]
    :activity [activity]
    [home]))

(defn main-app
  []
  (let [active-page @(subscribe [:active-page])]
    [:div
     [header]
     [:div (tw [:pt-12])]
     [pages active-page]]))