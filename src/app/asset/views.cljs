(ns app.asset.views
  (:require
   [re-frame.core :refer [subscribe dispatch]]
   [app.asset.subs :as subs]
   [app.subs :as app-subs]))

(defn title-panel [text]
  [:h1 {:class [:text-2xl :font-kanit :text-black]} text])

(defn subtitle-panel [i18n auction-price]
  [:div {:class [:mb-4]}
   [:span {:class [:text-2xl :font-kanit :font-medium]} (str auction-price " " (i18n :ARA))]
   [:div {:class [:text-sm]} (i18n :current-highest-bid)]])

(defn description-panel [text]
  [:p {:class [:text-black-700]} text])

(defn asset []
  (let [asset-title @(subscribe [::subs/asset-title])
        asset-description @(subscribe [::subs/asset-description])
        asset-auction-higest-price @(subscribe [::subs/asset-auction-higest-price])
        i18n @(subscribe [::app-subs/i18n])]
    [:div "Asset"]
    [:div {:class [:mx-2]}
     [title-panel asset-title]
     [subtitle-panel i18n asset-auction-higest-price]
     [description-panel asset-description]]))