(ns app.asset.views
  (:require
   [re-frame.core :refer [subscribe dispatch]]
   [app.asset.subs :as subs]))

(defn title-panel [text]
  [:h1 {:class [:text-2xl :font-kanit :text-black]} text])

(defn subtitle-panel [auction-price]
  [:div auction-price])

(defn description-panel [text]
  [:p {:class [:text-black-700]} text])

(defn asset []
  (let [asset-title @(subscribe [::subs/asset-title])
        asset-description @(subscribe [::subs/asset-description])
        asset-auction-higest-price @(subscribe [::subs/asset-auction-higest-price])]
    [:div "Asset"]
    [:div {:class [:mx-2]}
     [title-panel asset-title]
     [subtitle-panel asset-auction-higest-price]
     [description-panel asset-description]]))