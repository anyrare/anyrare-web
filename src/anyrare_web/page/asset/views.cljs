(ns anyrare-web.page.asset.views
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [subscribe dispatch]]
            [anyrare-web.page.asset.events :as events]
            [anyrare-web.page.asset.subs :as subs]
            [anyrare-web.subs :as app-subs]
            [anyrare-web.lib.format :as format]
            [anyrare-web.component.avatar :refer [avatar avatar-with-username]]
            [anyrare-web.component.svg :refer [angle-down angle-up]]
            [anyrare-web.ethers :as ethers]
            [anyrare-web.env :as env]
            [anyrare-web.lib.utils :as utils]
            [spade.core :refer [defclass]]
            [garden.stylesheet :refer (at-media)]))

(defn title-panel
  [text]
  [:h1 {:class [:text-2xl :font-kanit :text-black :font-medium :mt-2]} @text])

(defn auction-subtitle-panel
  [i18n asset asset-auction asset-auction-bid signer]
  [:div {:class [:py-2]}
   [:p
    [:span (:highest-bid i18n) ":"]
    [:span {:class [:ml-2 :font-kanit :font-medium]}
     (format/format-currency (:value @asset-auction)) " ARA"]]
   [:p
    [:span (:highest-bid-by i18n) ":"]
    [:span {:class [:ml-2 :font-kanit :font-medium]}
     (get-in asset-auction-bid [:bidder :username])]]
   [:p
    [:span (:total-bid i18n) ":"]
    [:span {:class [:ml-2 :font-kanit :font-medium]} (:total-bid @asset-auction)]]
   [:p
    [:span (:close-auction-timestamp i18n) ":"]
    [:span {:class [:ml-2 :font-kanit :font-medium]}
     (when (some? (:close-auction-timestamp @asset-auction))
       (format/unix-timestamp-to-local-datetime
        (str (:close-auction-timestamp @asset-auction))))]]
   [:button {:class [:p-2 :mt-2 :text-lg :button :bg-primary :font-kanit
                     :font-medium :text-white :rounded-full :w-full]
             :on-click #(dispatch [::events/toggle-bid-auction-panel
                                   toggle-popup-panel
                                   content-popup-panel])}
    (:bid-auction i18n)]])

(defn image-slider-panel
  [attachments]
  [:div {:id "image-slider" :class "splide"}
   [:div {:class "splide__track"}
    [:ul {:class "splide__list"}
     (if (some? attachments)
       (for [index (range (count attachments))]
         [:li {:class "splide__slide"
               :key index}
          [:img {:src (utils/ipfs->url ((get attachments index) :url))}]])
       [:div {:class [:bg-gray-100 :h-72 :w-full]}])]]])

(defn title-section-toggle
  [toggle title]
  [:div {:class [:flex :active:bg-gray-100 :-px-4 :cursor-pointer :py-1]
         :on-click #(swap! toggle not)}
   [:div {:class [:font-kanit :text-lg :font-kanit :font-medium :flex-grow]} title]
   [:div {:class [:text-right :flex-none]}
    (if @toggle [angle-up 24 24] [angle-down 24 24])]])

(defn detail-panel
  [i18n asset-detail]
  (let [toggle (reagent/atom true)]
    (fn []
      [:div {:class [:mt-2]}
       [title-section-toggle toggle (i18n :details)]
       (when @toggle
         [:div
          [:p {:class [:text-secondary :mt-2]} (:description @asset-detail)]
          [:p {:class [:mt-2]}
           [:span {:class [:text-secondary :mr-1]} (:asset-id i18n) ":"]
           [:span {:class [:font-kanit :font-medium]} (:token-id @asset-detail)]]
          [:p {:class []}
           [:span {:class [:text-secondary :mr-1]} (:audit-date i18n) ":"]
           [:span {:class [:font-kanit :font-medium]}
            (format/unix-timestamp-to-local-datetime (:audit-date @asset-detail))]]
          [:div {:class [:flex :mt-2]}
           [:div {:class ["w-1/2"]}
            [:p {:class [:text-secondary :text-sm :mb-2 :font-kanit]} (:founder i18n)]
            [avatar-with-username
             (get-in @asset-detail [:founder :thumbnail])
             (get-in @asset-detail [:founder :username])
             (get-in @asset-detail [:founder :address])]]
           [:div {:class ["w-1/2"]}
            [:p {:class [:text-secondary :text-sm :mb-2 :font-kanit]} (:owner i18n)]
            [avatar-with-username
             (get-in @asset-detail [:owner :thumbnail])
             (get-in @asset-detail [:owner :username])
             (get-in @asset-detail [:owner :address])]]]
          [:div {:class [:flex :mt-4]}
           [:div {:class ["w-1/2"]}
            [:p {:class [:text-secondary :text-sm :mb-2 :font-kanit]} (:auditor i18n)]
            [avatar-with-username
             (get-in @asset-detail [:auditor :thumbnail])
             (get-in @asset-detail [:auditor :username])
             (get-in @asset-detail [:auditor :address])]]
           [:div {:class ["w-1/2"]}
            [:p {:class [:text-secondary :text-sm :mb-2 :font-kanit]} (:custodian i18n)]
            [avatar-with-username
             (get-in @asset-detail [:custodian :thumbnail])
             (get-in @asset-detail [:custodian :username])
             (get-in @asset-detail [:custodian :address])]]]])])))

(defn royalty-panel
  [i18n asset-royalty]
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
             [:td {:class ["w-1/2" :border :pl-4 :py-1]} (@asset-royalty :founder-fee)]]
            [:tr
             [:td {:class ["w-1/2" :border :pl-4 :py-1]} (i18n :custodian)]
             [:td {:class ["w-1/2" :border :pl-4 :py-1]} (@asset-royalty :custodian-fee)]]]]])])))

(defn auction-bids-panel
  [i18n asset-auction asset-auction-bids]
  (let [toggle (reagent/atom true)]
    (fn []
      [:div {:class [:mt-2]}
       [title-section-toggle toggle (:bids i18n)]
       (when @toggle
         [:ul
          (for [bid @asset-auction-bids]
            [:li {:key (:bid-id bid) :class [:py-2]}
             [:div {:class [:flex]}
              [:div {:class [:flex-none :mr-2]} [avatar (:thumbnail (:bidder bid))]
               (:thumbnail bid)]
              [:div {:class [:flex-grow]}
               [:div
                [:span {:class [:font-kanit :font-medium]}
                 (format/format-currency (:value bid)) " ARA"]
                [:span {:class [:mx-2]} (:by i18n)]
                [:span {:class [:font-kanit :font-medium]}
                 (format/trim-username (:username (:bidder bid)))
                 " (" (:bid-id bid) ")"]]
               [:div {:class [:text-sm :text-secondary]}
                (format/unix-timestamp-to-local-datetime (str (:timestamp bid)))
                [:span {:class [:ml-2]}
                 (when (= (:bid-id bid) (:bid-id (first @asset-auction-bids)))
                   (if (>= (:value bid) (:reserve-price @asset-auction))
                     [:span {:class [:text-green-700]} (:meet-reserve-price i18n)]
                     (:not-meet-reserve-price i18n)))]]]]])])])))

(defn actions-panel
  [i18n asset signer]
  (let [toggle (reagent/atom false)]
    (fn []
      (when (= (:address @signer) (get-in @asset [:owner :address]))
        [:div {:class [:mt-2]}
         [title-section-toggle toggle (:tools i18n)]
         (when @toggle
           [:div
            [:button {:class [:button :h-12 :bg-white :border :border-gray-100 :w-full :rounded-full]
                      :on-click #(dispatch [::events/toggle-open-auction-panel
                                            toggle-popup-panel
                                            content-popup-panel])}
             (:open-auction i18n)]])]))))

(defn recommend-auction-panel
  []
  [:div {:class [:font-kanit :py-1 :font-medium :mx-2 :text-lg]} "การประมูลแนะนำ"])

(defn popup-panel
  [toggle-popup-panel content]
  (when @toggle-popup-panel
    [:div {:class [:fixed :top-0 :left-0 :w-screen :h-screen
                   :bg-black :z-50 :bg-opacity-95]}

     [:div {:class [:flex :fixed :bottom-0 :w-screen :mx-auto
                    :md:justify-center :md:h-screen :md:items-center]}
      [:div {:class [:flex-auto]}]
      [:div {:class [:w-full "md:w-3/6" "lg:w-3/9" "xl:w-5/12" "2xl:w-4/12" "3xl:w-3/12"
                     :bg-white :rounded-t-xl :md:rounded-xl :p-4]}
       [:button {:class [:h-10 :w-10 :bg-gray-800 :active:bg-gray-500
                         :text-white :rounded-full :m-2
                         :fixed :right-0 :top-0]
                 :on-click #(reset! toggle-popup-panel false)} "X"]
       content]
      [:div {:class [:flex-auto]}]]]))

(defn popup-login
  [i18n]
  [:div
   [:h2 {:class [:font-kanit :font-medium :text-xl :mb-2]}
    (:connect-to-wallet i18n) " "]
   [:span {:class [:text-secondary]} (:you-are-not-connect-to-wallet i18n)]
   [:button {:class [:button :mt-4 :h-12 :bg-white :border :border-gray-100 :w-full :rounded-full
                     :font-kanit :font-medium]
             :on-click #(%)} "Metamask"]])

(defn popup-bid-auction
  [i18n balance asset asset-title asset-auction]
  (when (some? @asset-auction)
    (let [minimum-bid (-> (format/num->big-number (:value @asset-auction))
                          (.times (format/num->big-number
                                   (:next-bid-weight @asset-auction)))
                          (.dividedBy (format/num->big-number
                                       (:max-weight @asset-auction)))
                          (.plus (format/num->big-number
                                  (:value @asset-auction)))
                          (.dividedBy format/erc20-digit-scaling-factor)
                          (.toFixed 4))
          bid-value (reagent/atom minimum-bid)
          max-bid-value (reagent/atom minimum-bid)]
      (fn []
        [:div {:class [:w-full]}
         [:h2 {:class [:font-kanit :font-medium :text-xl :mb-2]}
          (:place-a-bid i18n)]
         [:span {:class [:text-secondary]}
          (:you-are-about-to-place-a-bid-for i18n)
          [:span {:class [:font-bold :ml-2]} @asset-title]]
         [:div {:class [:flex :flex-auto :space-x-2]}
          [:div {:class ["w-1/2" :font-kanit :font-medium :mt-4]}
           [:p (:your-bid i18n)]
           [:input {:class [:p-2 :mt-2 :w-full :border-2 :border-gray-300
                            :hover:border-gray-500 :rounded-md]
                    :type "number"
                    :value @bid-value
                    :on-change #(reset! bid-value (-> % .-target .-value))}]]
          [:div {:class ["w-1/2" :font-kanit :font-medium :mt-4]}
           [:p (:max-bid i18n)]
           [:input {:class [:p-2 :mt-2 :w-full :border-2 :border-gray-300
                            :hover:border-gray-500 :rounded-md]
                    :type "number"
                    :value @max-bid-value
                    :on-change #(reset! max-bid-value (-> % .-target .-value))}]]]
         [:p {:class [:mt-2 :text-sm]}
          (:minimum-bid i18n) " " minimum-bid " ARA"]
         [:table {:class [:mt-4 :w-full]}
          [:tbody
           [:tr
            [:td (:wallet-address i18n)]
            [:td {:class [:text-right :font-kanit :font-medium]}
             (format/trim-username (:address @balance) 18)]]
           [:tr
            [:td (:your-balance i18n)]
            [:td {:class [:text-right :font-kanit :font-medium]}
             " " (format/format-currency (:value @balance)) " ARA"]]
           [:tr
            [:td (:you-will-pay i18n)]
            [:td {:class [:text-right :font-kanit :font-medium]}
             " " (if (< @max-bid-value @bid-value)
                   @bid-value @max-bid-value)
             " ARA"]]]]
         [:button {:class [:p-2 :mt-4 :text-lg :button :bg-primary :font-kanit
                           :font-medium :text-white :rounded-full :w-full]
                   :on-click
                   #(dispatch
                     [::events/bid-auction
                      {:token-id (:token-id @asset)
                       :bid-value (format/format-currency->number @bid-value)
                       :max-bid (-> (if (< @max-bid-value @bid-value)
                                      @bid-value @max-bid-value)
                                    (format/format-currency->number))}])}
          (:bid-auction i18n)]]))))

(defn popup-open-auction
  [i18n balance asset asset-title]
  (let [starting-price (reagent/atom 0)
        reserve-price (reagent/atom 0)
        close-auction-duration (reagent/atom 86400)]
    (fn []
      [:div {:class [:w-full]}
       [:h2 {:class [:font-kanit :font-medium :text-xl :mb-2]}
        (:open-auction i18n)]
       [:span {:class [:text-secondary]}
        (:you-are-about-to-place-a-bid-for i18n)
        [:span {:class [:font-bold :ml-2]} @asset-title]]
       [:div {:class [:flex :flex-auto :space-x-2]}
        [:div {:class ["w-1/2" :font-kanit :font-medium :mt-4]}
         [:p (:starting-price i18n)]
         [:input {:class [:p-2 :mt-2 :w-full :border-2 :border-gray-300
                          :hover:border-gray-500 :rounded-md]
                  :type "number"
                  :value @starting-price
                  :on-change #(reset! starting-price (-> % .-target .-value))}]]
        [:div {:class ["w-1/2" :font-kanit :font-medium :mt-4]}
         [:p (:reserve-price i18n)]
         [:input {:class [:p-2 :mt-2 :w-full :border-2 :border-gray-300
                          :hover:border-gray-500 :rounded-md]
                  :type "number"
                  :value @reserve-price
                  :on-change #(reset! reserve-price (-> % .-target .-value))}]]]
       [:div {:class [:flex :flex-auto :space-x-2]}
        [:div {:class ["w-1/2" :font-kanit :font-medium :mt-4]}
         [:p (:auction-duration i18n)]
         [:select {:field :list
                   :class [:p-2 :mt-2 :w-full :border-2 :border-gray-300
                           :bg-white :rounded-md]
                   :on-change #(reset! close-auction-duration (-> % .-target .-value))}
          [:option {:value 86400} (:day-1 i18n)]
          [:option {:value (* 3 86400)} (:day-3 i18n)]
          [:option {:value (* 5 86400)} (:day-5 i18n)]]]]
       [:table {:class [:mt-4 :w-full]}
        [:tbody
         [:tr
          [:td (:wallet-address i18n)]
          [:td {:class [:text-right :font-kanit :font-medium]}
           (format/trim-username (:address @balance) 18)]]
         [:tr
          [:td (:your-balance i18n)]
          [:td {:class [:text-right :font-kanit :font-medium]}
           " " (format/format-currency (str (:value @balance))) " ARA"]]
         [:tr
          [:td (:fee i18n)]
          [:td {:class [:text-right :font-kanit :font-medium]} "343321 ARA"]]]]
       [:button {:class [:p-2 :mt-4 :text-lg :button :bg-primary :font-kanit
                         :font-medium :text-white :rounded-full :w-full]
                 :on-click #(dispatch
                             [::events/open-auction
                              {:token-id (:token-id @asset)
                               :close-auction-duration @close-auction-duration
                               :starting-price @starting-price
                               :reserve-price @reserve-price
                               :max-weight env/AUCTION_MAX_WEIGHT
                               :next-bid-weight env/AUCTION_NEXT_BID_WEIGHT}])}
        (:open-auction-cta i18n)]])))

(defclass image-panel-class []
  (at-media {:min-width "1024px"}
            {:width "calc(100% - 500px)"
             :padding-right "16px"})
  {:width "100%"})

(defclass side-panel-class []
  (at-media {:min-width "1024px"}
            {:width "500px"})
  {:width "100%"})

(defn layout
  [image-panel content-panel]
  [:div {:class [:flex :flex-wrap]}
   [:div {:class [(image-panel-class)]} image-panel]
   [:div {:class [(side-panel-class)]} content-panel]])

(def toggle-popup-panel (reagent/atom false))
(def content-popup-panel (reagent/atom :bid-auction))

(defn asset []
  (let [asset (subscribe [::subs/asset])
        asset-title (subscribe [::subs/asset-title])
        asset-data (subscribe [::subs/asset-data])
        asset-detail (subscribe [::subs/asset-detail])
        asset-royalty (subscribe [::subs/asset-royalty])
        asset-auction (subscribe [::subs/asset-auction])
        asset-auction-bids (subscribe [::subs/asset-auction-bids])
        i18n @(subscribe [::app-subs/i18n])
        signer (subscribe [::app-subs/signer])
        balance (subscribe [::app-subs/balance])]
    [:div
     (dispatch [::events/initialize-image-slider])
     [layout
      [image-slider-panel (:assets @asset-data)]
      [:div {:class [:mx-2 :mb-4]}
       [title-panel asset-title]
       (when (some? @asset-auction)
         [auction-subtitle-panel i18n asset asset-auction
          (first @asset-auction-bids) signer])
       [detail-panel i18n asset-detail]
       [royalty-panel i18n asset-royalty]
       (when (not (:auction (:status @asset)))
         [actions-panel i18n asset signer])
       (when (and (some? @asset-auction) (> (:total-bid @asset-auction) 0))
         [auction-bids-panel i18n asset-auction asset-auction-bids])]]
     [popup-panel toggle-popup-panel
      (cond
        (nil? @signer) [popup-login i18n]
        (= @content-popup-panel :bid-auction)
        [popup-bid-auction i18n balance asset asset-title asset-auction]
        (= @content-popup-panel :open-auction)
        [popup-open-auction i18n balance asset asset-title asset-auction]
        (= @content-popup-panel :login) [popup-login i18n])]]))


