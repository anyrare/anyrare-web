(ns anyrare-web.asset.views
  (:require
   [reagent.core :as reagent]
   [re-frame.core :refer [subscribe dispatch]]
   [anyrare-web.asset.events :as events]
   [anyrare-web.asset.subs :as subs]
   [anyrare-web.subs :as app-subs]
   [anyrare-web.lib.format :refer [unix-timestamp-to-local-datetime]]
   [anyrare-web.component.avatar :refer [avatar avatar-with-username]]
   [anyrare-web.component.svg :refer [angle-down angle-up]]
   [spade.core :refer [defclass]]
   [garden.stylesheet :refer (at-media)]))

(defn title-panel [text]
  [:h1 {:class [:text-2xl :font-kanit :text-black :font-medium :mt-2]} text])

(defn subtitle-panel [i18n auction-info toggle-popup-panel]
  [:div {:class [:mb-4]}
   [:div {:class [:mt-2]}
    [:p {:class [:text-secondary :text-sm :mb-2 :font-kanit]} (i18n :highest-bid-by)]
    [avatar-with-username
     (get-in auction-info [:highest-bidder :thumbnail])
     (str (get-in auction-info [:highest-bidder :name])
          " ("
          (get-in auction-info [:highest-bidder :total-bid]) ")")
     (get-in auction-info [:highest-bidder :address])]]
   [:p {:class [:my-2]}
    [:span {:class [:text-secondary :mr-1]} (str (i18n :highest-bid) ":")]
    [:span {:class [:font-kanit :font-medium :text-transparent :bg-clip-text
                    :bg-gradient-to-br :from-red-400 :to-purple-800]}
     (str (auction-info :highest-price) " " (i18n :ARA))]]
   [:p
    [:span {:class [:text-secondary :mr-1]} (str (i18n :auction-ends-in) ":")]
    [:span {:class [:font-kanit :font-medium]} "0 วัน 4 ชั่วโมง 5 นาที 10 วินาที"]]
   [:p
    [:span {:class [:text-secondary :mr-1]} (str (i18n :auction-closed) ":")]
    [:span {:class [:font-kanit :font-medium]}
     (str (unix-timestamp-to-local-datetime (auction-info :end-date)))]]
   [:p
    [:span {:class [:text-secondary :mr-1]} (str (i18n :total-bid) ":")]
    [:span {:class [:font-kanit :font-medium]} (str (auction-info :total-bid) " ครั้ง")]]
   [:div
    [:button {:class [:button :bg-primary :active:bg-primary-600 :w-full
                      :rounded-full :mt-4 :py-2 :text-white :font-kanit :font-medium]
              :on-click #(reset! toggle-popup-panel true)}
     (i18n :place-a-bid)]]])

(defn image-slider-panel [attachments]
  [:div {:id "image-slider" :class "splide"}
   [:div {:class "splide__track"}
    [:ul {:class "splide__list"}
     (for [index (range (count attachments))]
       [:li {:class "splide__slide"
             :key index}
        [:img {:src ((get attachments index) :url)}]])]]])

(defn title-section-toggle [toggle title]
  [:div {:class [:flex :active:bg-gray-100 :-px-4 :cursor-pointer :py-1]
         :on-click #(swap! toggle not)}
   [:div {:class [:font-kanit :text-lg :font-kanit :font-medium :flex-grow]} title]
   [:div {:class [:text-right :flex-none]}
    (if @toggle [angle-up 24 24] [angle-down 24 24])]])

(defn detail-panel [i18n asset-detail]
  (let [toggle (reagent/atom false)]
    (fn []
      [:div {:class [:mt-2]}
       [title-section-toggle toggle (i18n :details)]
       (when @toggle
         [:div
          [:p {:class [:text-secondary :mt-2]} (asset-detail :description)]
          [:p {:class [:mt-2]}
           [:span {:class [:text-secondary :mr-1]} (str (i18n :asset-id) ":")]
           [:span {:class [:font-kanit :font-medium]} (asset-detail :address)]]
          [:div {:class [:flex :mt-2]}
           [:div {:class ["w-1/2"]}
            [:p {:class [:text-secondary :text-sm :mb-2 :font-kanit]} (i18n :founder)]
            [avatar-with-username
             (get-in asset-detail [:founder :thumbnail])
             (get-in asset-detail [:founder :name])
             (get-in asset-detail [:founder :address])]]
           [:div {:class ["w-1/2"]}
            [:p {:class [:text-secondary :text-sm :mb-2 :font-kanit]} (i18n :owner)]
            [avatar-with-username
             (get-in asset-detail [:owner :thumbnail])
             (get-in asset-detail [:owner :name])
             (get-in asset-detail [:owner :address])]]]])])))

(defn auditor-panel [i18n asset-auditor]
  (let [toggle (reagent/atom false)]
    (fn []
      [:div {:class [:mt-2]}
       [title-section-toggle toggle (i18n :audit-title)]
       (when @toggle
         [:div
          [:div {:class [:text-secondary :text-sm :my-2 :font-kanit]} (i18n :auditor)]
          [avatar-with-username
           (get-in asset-auditor [:auditor :thumbnail])
           (get-in asset-auditor [:auditor :name])
           (get-in asset-auditor [:auditor :address])]
          [:p {:class [:text-secondary :mt-2]}
           (get-in asset-auditor [:auditor :auditor-report :th])]
          [:p {:class [:text-secondary]}
           (str (i18n :audit-date :font-kanit) ": "
                (unix-timestamp-to-local-datetime
                 (get-in asset-auditor [:auditor :audit-date])))]
          [:p {:class [:text-secondary]}
           (str (i18n :audit-certificate) ": ")
           [:span {:class [:font-kanit :font-medium]}
            (get-in asset-auditor [:auditor :audit-address])]]])])))

(defn custodian-panel [i18n asset-custodian]
  (let [toggle (reagent/atom false)]
    (fn []
      [:div {:class [:mt-2]}
       [title-section-toggle toggle (i18n :custodian-title)]
       (when @toggle
         [:div
          [:p {:class [:text-secondary :text-sm :my-2 :font-kanit]} (i18n :custodian)]
          [avatar-with-username
           (get-in asset-custodian [:custodian :thumbnail])
           (get-in asset-custodian [:custodian :name])
           (get-in asset-custodian [:custodian :address])]
          [:p {:class [:text-secondary :mt-2]}
           (get-in asset-custodian [:custodian :custodian-report :th])]
          [:p {:class [:text-secondary]}
           (str (i18n :custodian-date) ": "
                (unix-timestamp-to-local-datetime
                 (get-in asset-custodian [:custodian :contract-date])))]
          [:p {:class [:text-secondary]}
           (str (i18n :custodian-contract) ": ")
           [:span {:class [:font-kanit :font-medium]}
            (get-in asset-custodian [:custodian :contract-address])]]])])))

(defn royalty-panel [i18n asset-royalty]
  (let [toggle (reagent/atom false)]
    (fn []
      [:div {:class [:mt-2]}
       [title-section-toggle toggle (i18n :royalty-fee)]
       (when @toggle
         [:div
          [:table {:class [:table-fixed :w-full :border-collapse :border :mt-2]}
           [:tbody
            [:tr
             [:td {:class ["w-1/2" :border :pl-4 :py-1]} (i18n :founder)]
             [:td {:class ["w-1/2" :border :pl-4 :py-1]} (asset-royalty :founder-fee)]]
            [:tr
             [:td {:class ["w-1/2" :border :pl-4 :py-1]} (i18n :custodian)]
             [:td {:class ["w-1/2" :border :pl-4 :py-1]} (asset-royalty :custodian-fee)]]]]])])))

(defn bids-panel [i18n asset-acution-bids]
  (let [toggle (reagent/atom true)]
    (fn []
      [:div {:class [:mt-2]}
       [title-section-toggle toggle (i18n :bids)]
       (when @toggle
         [:ul
          (for [bid asset-acution-bids]
            [:li {:key (bid :bid-id) :class [:py-2]}
             [:div {:class [:flex]}
              [:div {:class [:flex-none :mr-2]} [avatar (bid :thumbnail)]]
              [:div {:class [:flex-grow]}
               [:div
                [:span {:class [:font-kanit :font-medium]} (str (bid :price) " " (i18n :ARA))]
                [:span {:class [:text-secondary :px-1]} (i18n :by)]
                [:span {:class [:font-kanit :font-medium]} (str (bid :name))]
                [:span {:class [:text-secondary :text-sm :px-1]} (str "(" (bid :total-bid) ")")]]
               [:div
                [:span {:class [:text-secondary :text-sm]}
                 (unix-timestamp-to-local-datetime (bid :date))]]]]])])])))

(defn recommend-auction-panel []
  (fn []
    [:div {:class [:font-kanit :py-1 :font-medium :mx-2 :text-lg]} "การประมูลแนะนำ"]))

(defn popup-panel [content toggle-popup-panel]
  (when @toggle-popup-panel
    [:div {:class [:fixed :top-0 :left-0 :w-screen :h-screen :bg-black :z-50 :bg-opacity-95]}

     [:div {:class [:flex :fixed :bottom-0 :w-screen :mx-auto :md:justify-center :md:h-screen :md:items-center]}
      [:div {:class [:flex-auto]}]
      [:div {:class [:w-full "md:w-3/5" "lg:w-3/8" "xl:w-5/12" "2xl:w-4/12" "3xl:w-3/12"
                     :bg-white :pt-4 :rounded-t-xl :md:rounded-xl :p-4]}
       [:button {:class [:h-10 :w-10 :bg-gray-800 :active:bg-gray-500 :text-white :rounded-full :m-2
                         :fixed :right-0 :top-0]
                 :on-click #(reset! toggle-popup-panel false)} "X"]
       content]
      [:div {:class [:flex-auto]}]]]))

(defn popup-auction [i18n]
  [:div {:class [:mt-2]}
   [:h2 {:class [:font-kanit :font-medium :text-xl :mb-2]} (i18n :place-a-bid)]
   [:span {:class [:text-secondary]} (i18n :you-are-about-to-place-a-bid-for)]])

(defclass image-panel-class []
  (at-media {:min-width "1024px"}
            {:width "calc(100% - 500px)"
             :padding-right "16px"})
  {:width "100%"})

(defclass side-panel-class []
  (at-media {:min-width "1024px"}
            {:width "500px"})
  {:width "100%"})

(defn layout [image-panel content-panel]
  [:div {:class [:flex :flex-wrap]}
   [:div {:class [(image-panel-class)]} image-panel]
   [:div {:class [(side-panel-class)]} content-panel]])

(defn asset []
  (let [asset-title @(subscribe [::subs/asset-title])
        asset-detail @(subscribe [::subs/asset-detail])
        asset-auditor @(subscribe [::subs/asset-auditor])
        asset-custodian @(subscribe [::subs/asset-custodian])
        asset-royalty @(subscribe [::subs/asset-royalty])
        asset-attachments @(subscribe [::subs/asset-attachments])
        asset-auction-info @(subscribe [::subs/asset-auction-info])
        asset-auction-bids @(subscribe [::subs/asset-auction-bids])
        i18n @(subscribe [::app-subs/i18n])
        toggle-popup-panel (reagent/atom false)]
    [:div
     (dispatch [::events/initialize-image-slider])
     [layout
      [image-slider-panel asset-attachments]
      [:div {:class [:mx-2 :mb-4]}
       [title-panel asset-title]
       [subtitle-panel i18n asset-auction-info toggle-popup-panel]
       [bids-panel i18n asset-auction-bids]
       [detail-panel i18n asset-detail]
       [auditor-panel i18n asset-auditor]
       [custodian-panel i18n asset-custodian]
       [royalty-panel i18n asset-royalty]]]
     [recommend-auction-panel]
     [popup-panel (popup-auction i18n) toggle-popup-panel]]))
