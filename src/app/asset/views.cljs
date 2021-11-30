(ns app.asset.views
  (:require
   [reagent.dom :refer [render]]
   [re-frame.core :refer [subscribe dispatch dispatch-sync]]
   [app.asset.events :as events]
   [app.asset.subs :as subs]
   [app.subs :as app-subs]
   [app.lib.format :refer [unix-timestamp-to-local-datetime]]
   [app.component.avatar :refer [avatar-with-username]]))

(defn title-panel [text]
  [:h1 {:class [:text-2xl :font-kanit :text-black :font-medium :mt-2]} text])

(defn subtitle-panel [i18n auction-info]
  [:div {:class [:mb-4]}
   [:div {:class [:mt-2]}
    [:p {:class [:text-base :text-sm :mb-2 :font-kanit]} (i18n :highest-bid-by)]
    [avatar-with-username
     (get-in auction-info [:highest-bidder :thumbnail])
     (str (get-in auction-info [:highest-bidder :name])
          " ("
          (get-in auction-info [:highest-bidder :total-bid]) ")")
     (get-in auction-info [:highest-bidder :address])]]
   [:p {:class [:my-2]}
    [:span {:class [:text-sm :text-base :mr-1]} (str (i18n :highest-bid) ":")]
    [:span {:class [:font-kanit :font-medium :text-transparent :bg-clip-text
                    :bg-gradient-to-br :from-red-400 :to-purple-800]} 
     (str (auction-info :highest-price) " " (i18n :ARA))]]
   [:p
    [:span {:class [:text-base :text-sm :mr-1]} (str (i18n :auction-ends-in) ":")]
    [:span {:class [:text-sm :font-kanit :font-medium]} "0 วัน 4 ชั่วโมง 5 นาที 10 วินาที"]]
   [:p
    [:span {:class [:text-base :text-sm :mr-1]} (str (i18n :auction-closed) ":")]
    [:span {:class [:text-sm :font-kanit :font-medium]} (str (unix-timestamp-to-local-datetime (auction-info :end-date)))]]
   [:p
    [:span {:class [:text-base :text-sm :mr-1]} (str (i18n :total-bid) ":")]
    [:span {:class [:text-sm :font-kanit :font-medium]} (str (auction-info :total-bid) " ครั้ง")]]
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

(defn detail-panel [i18n asset-detail]
  [:div
   [:p {:class [:font-kanit :text-lg :mt-8 :mb-2 :font-kanit :font-medium]} (i18n :details)]
   [:p {:class [:text-base :mt-2]} (asset-detail :description)]
   [:p {:class [:py-2]}
    [:span {:class [:text-base :mr-1]} (str (i18n :asset-id) ":")]
    [:span {:class [:font-kanit :font-medium]} (asset-detail :address)]]
   [:div {:class [:flex :mt-2]}
    [:div {:class ["w-1/2"]}
     [:p {:class [:text-base :text-sm :mb-2 :font-kanit]} (i18n :founder)]
     [avatar-with-username 
      (get-in asset-detail [:founder :thumbnail])
      (get-in asset-detail [:founder :name])
      (get-in asset-detail [:founder :address])]]
    [:div {:class ["w-1/2"]}
     [:p {:class [:text-base :text-sm :mb-2 :font-kanit]} (i18n :owner)]
     [avatar-with-username 
      (get-in asset-detail [:owner :thumbnail])
      (get-in asset-detail [:owner :name])
      (get-in asset-detail [:owner :address])]]]])

(defn auditor-panel [i18n asset-auditor]
  [:div {:class [:mt-8]}
   [:p {:class [:font-kanit :text-lg :mb-2 :font-kanit :font-medium]} (i18n :audit-title)]
   [:div
    [:div {:class [:text-base :text-sm :my-2 :font-kanit]} (i18n :auditor)]
    [avatar-with-username
     (get-in asset-auditor [:auditor :thumbnail])
     (get-in asset-auditor [:auditor :name])
     (get-in asset-auditor [:auditor :address])]]
   [:p {:class [:text-base :mt-2]}
    (get-in asset-auditor [:auditor :auditor-report :th])]
   [:p {:class [:text-base]}
    (str (i18n :audit-date :font-kanit) ": "
         (unix-timestamp-to-local-datetime (get-in asset-auditor [:auditor :audit-date])))]
   [:p {:class [:text-base]}
    (str (i18n :audit-certificate) ": ")
    [:span {:class [:font-kanit :font-medium]} (get-in asset-auditor [:auditor :audit-address])]]])

(defn custodian-panel [i18n asset-custodian]
  [:div {:class [:mt-8]}
   [:p {:class [:font-kanit :text-lg :mb-2 :font-kanit :font-medium]} (i18n :custodian-title)]
   [:div
    [:p {:class [:text-base :text-sm :my-2 :font-kanit]} (i18n :custodian)]
    [avatar-with-username
     (get-in asset-custodian [:custodian :thumbnail])
     (get-in asset-custodian [:custodian :name])
     (get-in asset-custodian [:custodian :address])]]
   [:p {:class [:text-base :mt-2]}
    (get-in asset-custodian [:custodian :custodian-report :th])]
   [:p {:class [:text-base]}
    (str (i18n :custodian-date) ": "
         (unix-timestamp-to-local-datetime (get-in asset-custodian [:custodian :contract-date])))]
   [:p {:class [:text-base]}
    (str (i18n :custodian-contract) ": ")
    [:span {:class [:font-kanit :font-medium]} (get-in asset-custodian [:custodian :contract-address])]]])

(defn royalty-panel [i18n asset-royalty]
  [:div {:class [:mt-8]}
   [:p {:class [:font-kanit :text-lg :mb-2 :font-kanit :font-medium]} (i18n :royalty-fee)]
   [:table {:class [:table-fixed :w-full :border-collapse :border]}
    [:tbody
     [:tr
      [:td {:class ["w-1/2" :border :pl-4 :py-1]} (i18n :founder)]
      [:td {:class ["w-1/2" :border :pl-4 :py-1]} (asset-royalty :founder-fee)]]
     [:tr
      [:td {:class ["w-1/2" :border :pl-4 :py-1]} (i18n :custodian)]
      [:td {:class ["w-1/2" :border :pl-4 :py-1]} (asset-royalty :custodian-fee)]]]]])


(defn asset []
  (let [asset-title @(subscribe [::subs/asset-title])
        asset-detail @(subscribe [::subs/asset-detail])
        asset-auditor @(subscribe [::subs/asset-auditor])
        asset-custodian @(subscribe [::subs/asset-custodian])
        asset-royalty @(subscribe [::subs/asset-royalty])
        asset-attachments @(subscribe [::subs/asset-attachments])
        asset-auction-info @(subscribe [::subs/asset-auction-info])
        i18n @(subscribe [::app-subs/i18n])]
    [:div
     (dispatch [::events/initialize-image-slider])
     [image-slider-panel asset-attachments]
     [:div {:class [:mx-2]}
      [title-panel asset-title]
      [subtitle-panel i18n asset-auction-info]
      [detail-panel i18n asset-detail]
      [auditor-panel i18n asset-auditor]
      [custodian-panel i18n asset-custodian]
      [royalty-panel i18n asset-royalty]
      [:div {:class [:mt-8]}]]]))