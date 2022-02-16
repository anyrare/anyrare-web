(ns anyrare-web.page.tool.founder-sign
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [subscribe dispatch]]
            [anyrare-web.page.tool.events :as events]
            [anyrare-web.page.tool.subs :as subs]
            [anyrare-web.component.avatar :refer [avatar avatar-with-username]]
            [anyrare-web.subs :as app-subs]
            [anyrare-web.lib.format :as format]
            [anyrare-web.lib.utils :as utils]))

(defn tool-founder-sign []
  (let [assets (subscribe [::subs/assets])
        i18n @(subscribe [::app-subs/i18n])]
    (fn []
      [:div {:class [:w-full]}
       [:h1 {:class [:mt-4 :text-center :text-xl :font-kanit :font-medium]}
        "สินทรัพย์รอยืนยันความเป็นเจ้าของ"]
       [:div {:class [:px-4 :grid :md:grid-cols-2 :gap-4 :md:gap-8 :md:px-36]}
        (for [asset @assets]
          [:div {:class [:py-4]}
           [:div {:class []}
            [:img {:src (utils/ipfs->url (get-in asset [:token-uri-data :image]))}]]
           [:div
            [:h2 {:class [:font-kanit :font-medium :text-lg :mt-2]}
             (get-in asset [:token-uri-data :locales :name :th])]
            [:p (get-in asset [:token-uri-data :locales :description :th])]

            [:div {:class [:flex :mt-2]}
             [:div {:class ["w-1/2"]}
              [:p {:class [:text-secondary :text-sm :mb-2 :font-kanit]} (:custodian i18n)]
              [avatar-with-username
               (get-in asset [:custodian :thumbnail])
               (get-in asset [:custodian :username])
               (get-in asset [:custodian :address])]]
             [:div {:class ["w-1/2"]}
              [:p {:class [:text-secondary :text-sm :mb-2 :font-kanit]} (:auditor i18n)]
              [avatar-with-username
               (get-in asset [:auditor :thumbnail])
               (get-in asset [:auditor :username])
               (get-in asset [:auditor :address])]]]

            [:div {:class [:flex :mt-4]}
             [:div {:class ["w-1/2"]}
              [:p {:class [:text-secondary :text-sm :mb-2 :font-kanit]} "TokenId"]
              [:span {:class [:text-sm]} (str (:token-id asset))]]
             [:div {:class ["w-1/2"]}
              [:p {:class [:text-secondary :text-sm :mb-2 :font-kanit]} (:audit-date i18n)]
              [:span {:class [:text-sm]}
               (format/unix-timestamp-to-local-datetime
                (get-in asset [:token-uri-data :timestamp]))]]]

            [:div {:class [:mt-4]}
             [:p {:class [:text-secondary :text-sm :mb-2 :font-kanit]} (:fee i18n)]]
            [:table {:class [:table-fixed :w-full :border-collapse :border :mt-2 :text-sm]}
             [:tbody
              [:tr
               [:td {:class [:border :pl-4 :py-1]}
                (str (:founder-fee i18n) " %")]
               [:td {:class [:w-24 :border :pl-4 :py-1]}
                (format/format-percentage (get-in asset [:fee :founder-weight])
                                          (get-in asset [:fee :max-weight]))]]
              [:tr
               [:td {:class [:border :pl-4 :py-1]}
                (str (:founder-redeem-fee i18n) " %")]
               [:td {:class [:border :pl-4 :py-1]}
                (format/format-percentage (get-in asset [:fee :founder-redeem-weight])
                                          (get-in asset [:fee :max-weight]))]]
              [:tr
               [:td {:class [:border :pl-4 :py-1]}
                (str (:founder-fee i18n) " ARA")]
               [:td {:class [:border :pl-4 :py-1]}
                (format/format-currency (get-in asset [:fee :founder-general-fee]))]]

              [:tr
               [:td {:class [:border :pl-4 :py-1]}
                (str (:custodian-fee i18n) " %")]
               [:td {:class [:border :pl-4 :py-1]}
                (format/format-percentage (get-in asset [:fee :custodian-weight])
                                          (get-in asset [:fee :max-weight]))]]
              [:tr
               [:td {:class [:border :pl-4 :py-1]}
                (str (:custodian-redeem-fee i18n) " %")]
               [:td {:class [:border :pl-4 :py-1]}
                (format/format-percentage (get-in asset [:fee :custodian-redeem-weight])
                                          (get-in asset [:fee :max-weight]))]]
              [:tr
               [:td {:class [:border :pl-4 :py-1]}
                (str (:custodian-general-fee i18n) " ARA")]
               [:td {:class [:border :pl-4 :py-1]}
                (format/format-currency (get-in asset [:fee :custodian-general-fee]))]]

              [:tr
               [:td {:class [:border :pl-4 :py-1]}
                (str (:founder-fee i18n) " %")]
               [:td {:class [:border :pl-4 :py-1]}
                (format/format-currency (get-in asset [:fee :audit-fee]))]]]]

            [:button {:class [:button :bg-primary :rounded-full
                              :w-full :text-white :py-2 :mt-4]
                      :on-click #(dispatch [::events/founder-sign
                                            {:token-id (:token-id asset)}])}
             "ยืนยันความเป็นเจ้าของ"]]])]])))


