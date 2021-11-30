(ns app.asset.views
  (:require
   [reagent.dom :refer [render]]
   [re-frame.core :refer [subscribe dispatch dispatch-sync]]
   [app.asset.events :as events]
   [app.asset.subs :as subs]
   [app.subs :as app-subs]))

(defn title-panel [text]
  [:h1 {:class [:text-2xl :font-kanit :text-black :font-medium :mt-2]} text])

(defn subtitle-panel [i18n auction-price]
  [:div {:class [:mb-4]}
   [:div {:class [:flex :mt-4]}
    [:div {:class ["w-1/2"]}
     [:div {:class [:text-black-700 :text-sm]} (i18n :highest-bid)]
     [:div {:class [:font-kanit :font-medium]} (str auction-price " " (i18n :ARA))]]
    [:div {:class ["w-1/2"]}
     [:div {:class [:text-black-700 :text-sm]} (i18n :highest-bid-by)]
     [:div {:class [:font-kanit :font-medium]} "panasun (143)"]]]
   [:div {:class [:mt-2]}
    [:span {:class [:text-black-700 :text-sm :mr-1]} (str (i18n :auction-ends-in) ":")]
    [:span {:class [:text-sm :font-kanit :font-medium]} "0 วัน 4 ชั่วโมง 5 นาที 10 วินาที"]]
   [:div
    [:span {:class [:text-black-700 :text-sm :mr-1]} (str (i18n :auction-closed) ":")]
    [:span {:class [:text-sm :font-kanit :font-medium]} "3 ธ.ค. 2564 18:09"]]
   [:div
    [:span {:class [:text-black-700 :text-sm :mr-1]} (str (i18n :total-bid) ":")]
    [:span {:class [:text-sm :font-kanit :font-medium]} "43 ครั้ง"]]
   [:div
    [:button {:class [:button :bg-primary :w-full :rounded-full :mt-4 :py-2 :text-white]} "เสนอราคา"]]])

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
        asset-auction-highest-price @(subscribe [::subs/asset-auction-highest-price])
        i18n @(subscribe [::app-subs/i18n])]
    [:div
     (dispatch [::events/initialize-image-slider])
     [image-slider-panel asset-attachments]
     [:div {:class [:mx-2]}
      [title-panel asset-title]
      [subtitle-panel i18n asset-auction-highest-price]
      [description-panel asset-description]]]))