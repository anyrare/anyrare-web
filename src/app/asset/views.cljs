(ns app.asset.views
  (:require
   [reagent.dom :refer [render]]
   [re-frame.core :refer [subscribe dispatch dispatch-sync]]
   [app.asset.events :as events]
   [app.asset.subs :as subs]
   [app.subs :as app-subs]))

(defn title-panel [text]
  [:h1 {:class [:text-2xl :font-kanit :text-black :mt-2]} text])

(defn subtitle-panel [i18n auction-price]
  [:div {:class [:mb-4]}
   [:span {:class [:text-2xl :font-kanit :font-medium]} (str auction-price " " (i18n :ARA))]
   [:div {:class [:text-sm]} (i18n :current-highest-bid)]])

(defn image-slider-panel [attachments]
  [:div {:id "image-slider" :class "splide"}
   [:div {:class "splide__track"}
    [:ul {:class "splide__list"}
     (for [index (range (count attachments))]
       [:li {:class "splide__slide 2xl:rounded-xl"
             :key index}
        [:img {:src ((get attachments index) :url)}]])]]])

(defn description-panel [text]
  [:p {:class [:text-black-700]} text])

(defn asset []
  (let [asset-title @(subscribe [::subs/asset-title])
        asset-description @(subscribe [::subs/asset-description])
        asset-attachments @(subscribe [::subs/asset-attachments])
        asset-auction-higest-price @(subscribe [::subs/asset-auction-higest-price])
        i18n @(subscribe [::app-subs/i18n])]
    [:div
     (dispatch [::events/initialize-image-slider])
     [image-slider-panel asset-attachments]
     [:div {:class [:mx-2]}
      [title-panel asset-title]
      [subtitle-panel i18n asset-auction-higest-price]
      [description-panel asset-description]]]))