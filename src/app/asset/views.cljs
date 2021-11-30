(ns app.asset.views
  (:require
   [re-frame.core :refer [subscribe dispatch]]
   [app.asset.subs :as subs]))

(defn title-panel [text]
  [:h1 {:class [:text-2xl :font-kanit :text-black]} text])

(defn description-panel [text]
  [:p {:class [:text-black-700]} text])

(defn asset []
  (let [asset-title @(subscribe [::subs/asset-title])
        asset-description @(subscribe [::subs/asset-description])]
    [:div "Asset"]
    [:div {:class [:mx-2]}
     [title-panel asset-title]
     [description-panel asset-description]]))