(ns anyrare-web.page.tool.mint-asset
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [subscribe dispatch]]
            [fork.re-frame :as fork]
            [anyrare-web.page.tool.events :as events]
            [anyrare-web.page.tool.subs :as subs]
            [anyrare-web.subs :as app-subs]
            [anyrare-web.lib.utils :as utils]))

(defn upload-image
  [image-id]
  (let [el (.getElementById js/document image-id)
        name (.-name el)
        file (aget (.-files el) 0)
        form-data (js/FormData.)
        _ (.append form-data "file" file)]
    (dispatch [::events/upload-file form-data (keyword image-id)])))

(defn mint-asset-upload-image-component
  [i18n image-id handle-change handle-blur]
  (let [image (subscribe [::subs/image-by-id (keyword image-id)])]
    (fn []
      [:div {:class [:py-1 :w-48]}
       [:input {:type "file"
                :name image-id
                :id image-id
                ;; :value (get-in @image [:ipfs-hash])
                :on-change #(upload-image image-id)
                :on-blur handle-blur
                :accept "image/*"}]
       (when (some? (get-in @image [:ipfs-hash]))
         [:div
          [:img {:src (utils/ipfs-url (get-in @image [:ipfs-hash]))}]])])))

(defn mint-asset-form
  [{:keys [values form-id handle-change handle-blur submitting?
           on-submit-server-message handle-submit i18n]}]
  (let [i18n @(subscribe [::app-subs/i18n])
        rows [{:type :title
               :name (:mint-asset-name i18n)}
              {:type :col
               :name "TH"
               :input-name "name-th"
               :input-type :text
               :required true}
              {:type :col
               :name "EN"
               :input-name "name-en"
               :input-type :text
               :required true}
              {:type :col
               :name "CN"
               :input-name "name-cn"
               :input-type :text}
              {:type :title
               :name (:mint-asset-description i18n)}
              {:type :col
               :name "TH"
               :input-name "description-th"
               :input-type :text-box
               :required true}
              {:type :col
               :name "EN"
               :input-name "description-en"
               :input-type :text-box
               :required true}
              {:type :col
               :name "CN"
               :input-name "description-cn"
               :input-type :text-box}
              {:type :title
               :name (:wallet-address i18n)}
              {:type :col
               :name (:founder i18n)
               :input-name "founder-address"
               :input-type :text
               :required true}
              {:type :col
               :name (:custodian i18n)
               :input-name "custodian-address"
               :input-type :text
               :required true}
              {:type :title
               :name (:royalty-fee i18n)}
              {:type :col
               :name (str (:founder-fee i18n) " %")
               :input-name "founder-fee"
               :input-type :text
               :required true}
              {:type :col
               :name (str (:founder-redeem-fee i18n) " %")
               :input-name "founder-redeem-fee"
               :input-type :text
               :required true}
              {:type :col
               :name (str (:founder-general-fee i18n) " (ARA)")
               :input-name "founder-general-fee"
               :input-type :text
               :required true}
              {:type :col
               :name (str (:custodian-fee i18n) " %")
               :input-name "custodian-fee"
               :input-type :text
               :required true}
              {:type :col
               :name (str (:custodian-redeem-fee i18n) " %")
               :input-name "custodian-redeem-fee"
               :input-type :text
               :required true}
              {:type :col
               :name (str (:custodian-general-fee i18n) " (ARA)")
               :input-name "custodian-general-fee"
               :input-type :text
               :required true}
              {:type :col
               :name (str (:audit-fee i18n) " (ARA)")
               :input-name "audit-fee"
               :input-type :text
               :required true}
              {:type :title
               :name (:attribute i18n)}
              {:type :col
               :name (:created-date i18n)
               :input-name "created-date"
               :input-type :text}
              {:type :col
               :name (:created-by i18n)
               :input-name "created-by"
               :input-type :text}
              {:type :col
               :name (:origin i18n)
               :input-name "origin"
               :input-type :text}
              {:type :col
               :name (:edition i18n)
               :input-name "edition"
               :input-type :text}
              {:type :col
               :name (:material i18n)
               :input-name "material"
               :input-type :text}
              {:type :col
               :name (:ingredient i18n)
               :input-name "ingredient"
               :input-type :text}
              {:type :col
               :name (:condition i18n)
               :input-name "condition"
               :input-type :text}
              {:type :col
               :name (:width i18n)
               :input-name "width"
               :input-type :text}
              {:type :col
               :name (:length i18n)
               :input-name "length"
               :input-type :text}
              {:type :col
               :name (:height i18n)
               :input-name "height"
               :input-type :text}
              {:type :col
               :name (:weight i18n)
               :input-name "weight"
               :input-type :text}]]
    [:form {:id form-id :on-submit handle-submit}
     [:div
      [:table {:class [:w-full]}
       [:tbody
        (for [row rows]
          [:tr
           [:td {:class ["w-1/3"]}
            (when (= :col (:type row)) (:name row))
            (when (= true (:required row))
              [:span {:class [:ml-1 :text-red-700]} "*"])]
           [:td {:class ["w-2/3"]}
            (if (= :title (:type row))
              [:p {:class [:mt-4]}
               (:name row)]
              (cond
                (= :text (:input-type row))
                [:input
                 {:name (:input-name row)
                  :class [:border :border-gray-300 :rounded-lg :mt-2 :p-2 :w-full]
                  :value (values (:input-name row))
                  :type "text"
                  :on-change handle-change
                  :on-blur handle-blur
                  :required (= true (:required row))}]
                (= :text-box (:input-type row))
                [:textarea
                 {:name (:input-name row)
                  :class [:border :border-gray-300 :rounded-lg :mt-2 :p-2 :w-full]
                  :value (values (:input-name row))
                  :type "text"
                  :rows "4"
                  :cols "10"
                  :on-change handle-change
                  :on-blur handle-blur
                  :required (= true (:required row))}]))]])
        [:tr [:td {:class ["w-1/3"]} (:image i18n)]
         [:td {:class ["w-2/3"]}
          [:div {:class [:pt-4]}
           (for [i (range 5)]
             [mint-asset-upload-image-component
              i18n (str "image-id-" i) handle-change handle-blur])]]]
        [:tr [:td]
         [:td
          [:button
           {:class [:button :bg-primary :active:bg-primary-400 :font-kanit
                    :font-medium :w-full :mt-8 :text-white :py-2 :rounded-lg]
            :type :submit :disabled submitting?} (:mint-asset-submit i18n)]
          [:p on-submit-server-message]]]]]]]))

(defn mint []
  [:div {:class [:px-8]}
   [fork/form {:path [:mint-asset-form]
               :form-id "mint-asset"
               :initial-values {"founder-fee" 10
                                "founder-redeem-fee" 30
                                "founder-general-fee" 1
                                "custodian-fee" 10
                                "custodian-redeem-fee" 30
                                "custodian-general-fee" 1
                                "audit-fee" 1}
               :prevent-default? true
               :clean-on-unmount? true
               :on-submit #(dispatch [::events/mint-nft-flow %])} mint-asset-form]])

(defn tool-mint-asset []
  (let [image (reagent/atom nil)
        bg-image-style (fn [] (if-let [i @image]
                                {:background-image (str "url(" i ")")}
                                {:background-color "red"}))
        input-id "input-image"
        i18n @(subscribe [::app-subs/i18n])]
    (fn []
      [:div {:class [:w-full]}
       [:h1 {:class [:mt-4 :text-center :text-xl :font-kanit :font-medium]}
        (:mint-asset-page-title i18n)]
       [mint]])))

