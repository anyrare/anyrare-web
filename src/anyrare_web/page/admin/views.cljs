(ns anyrare-web.page.admin.views
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [subscribe dispatch]]
            [fork.re-frame :as fork]
            [anyrare-web.page.admin.events :as events]
            [anyrare-web.subs :as app-subs]
            [anyrare-web.lib.ipfs :as ipfs]))

(defn upload-image
  [input-id]
  (let [el (.getElementById js/document input-id)
        name (.-name el)
        file (aget (.-files el) 0)
        form-data (js/FormData.)
        _ (.append form-data "file" file)]
    (dispatch [::events/upload-file form-data])))

;; (def metadata
;;   {:name "CloseEye Pra"
;;    :description "ssssss"
;;    :external_url
;;    :image
;;    :attributes [{:trait_type "width" :value 1}
;;                 {:trait_type "height"}
;;                 {:trait_type "length"}
;;                 {:trait_type "created_date"}
;;                 {:trait_type "model"}
;;                 {:trait_type "material"}
;;                 {:trait_type "origin"}
;;                 {:trait_type "condition"}
;;                 {:trait_type "weight"}
;;                 {:trait_type "volume"}]
;;    :attachments [{:type
;;                   :url}]
;;    :token_id
;;    :token_address
;;    :auditor {:address
;;              :report-uri
;;              :audit-date}
;;    :custodian {:address
;;                :contract-uri}
;;    :en {}
;;    :cn {}})

(defn mint-asset-upload-image-component
  [i18n image-id]
    (fn []
      [:div {:class [:py-1]}
       [:input {:type "file"
                :id image-id
                :accept "image/*"}]]))

(defn mint-asset-form
  [{:keys [values form-id handle-change handle-blur submitting?
           on-submit-server-message handle-submit i18n]}]
  (let [i18n @(subscribe [::app-subs/i18n])
        rows [{:type :title
               :name (:mint-asset-name i18n)}
              {:type :col
               :name "TH"
               :input-name "name-th"
               :input-type :text}
              {:type :col
               :name "EN"
               :input-name "name-en"
               :input-type :text}
              {:type :col
               :name "CN"
               :input-name "name-cn"
               :input-type :text}
              {:type :title
               :name (:mint-asset-description i18n)}
              {:type :col
               :name "TH"
               :input-name "description-th"
               :input-type :text-box}
              {:type :col
               :name "EN"
               :input-name "description-en"
               :input-type :text-box}
              {:type :col
               :name "CN"
               :input-name "description-cn"
               :input-type :text-box}
              {:type :title
               :name (:wallet-address i18n)}
              {:type :col
               :name (:founder i18n)
               :input-name "founder-address"
               :input-type :text}
              {:type :col
               :name (:auditor i18n)
               :input-name "auditor-address"
               :input-type :text}
              {:type :col
               :name (:custodian i18n)
               :input-name "custodian-address"
               :input-type :text}
              {:type :title
               :name (:royalty-fee i18n)}
              {:type :col
               :name (str (:founder-fee i18n) " %")
               :input-name "founder-fee"
               :input-type :text}
              {:type :col
               :name (str (:custodian-fee i18n) " %")
               :input-name "custodian-fee"
               :input-type :text}
              {:type :col
               :name (str (:founder-redeem-fee i18n) " %")
               :input-name "founder-redeem-fee"
               :input-type :text}
              {:type :col
               :name (str (:custodian-redeem-fee i18n) " %")
               :input-name "custodian-redeem-fee"
               :input-type :text}
              {:type :col
               :name (str (:founder-general-fee i18n) " (ARA)")
               :input-name "founder-general-fee"
               :input-type :text}
              {:type :col
               :name (str (:custodian-general-fee i18n) " (ARA)")
               :input-name "custodian-general-fee"
               :input-type :text}
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
           [:td {:class ["w-1/3"]} (when (= :col (:type row)) (:name row))]
           [:td {:class ["w-2/3"]}
            (if (= :title (:type row)) [:p {:class [:mt-4]} (:name row)]
                (cond
                  (= :text (:input-type row))
                  [:input
                   {:name (:input-name row)
                    :class [:border :border-gray-300 :rounded-lg :mt-2 :p-2 :w-full]
                    :value (values (:input-name row))
                    :type "text"
                    :on-change handle-change
                    :on-blur handle-blur}]
                  (= :text-box (:input-type row))
                  [:textarea
                   {:name (:input-name row)
                    :class [:border :border-gray-300 :rounded-lg :mt-2 :p-2 :w-full]
                    :value (values (:input-name row))
                    :type "text"
                    :rows "4"
                    :cols "10"
                    :on-change handle-change
                    :on-blur handle-blur}]))]])
        [:tr [:td (:image i18n)]
         [:td [:div {:class [:pt-4]}
               (for [i (range 5)]
                 [mint-asset-upload-image-component i18n (str "image-id-" i)])]]]
        [:tr [:td]
         [:td [:button {:class [:button :bg-primary :active:bg-primary-400 :font-kanit
                                :font-medium :w-full :mt-8 :text-white :py-2 :rounded-lg]
                        :type :submit :disabled submitting?} (:mint-asset-submit i18n)]
          [:p on-submit-server-message]]]]]]]))

(defn mint-asset
  [i18n]
  [:div {:class [:px-8]}
   [fork/form {:path [:mint-asset-form]
               :form-id "mint-asset"
               :prevent-default? true
               :clean-on-unmount? true
               :on-submit #(dispatch [::events/mint-nft %])} mint-asset-form]])

(defn admin []
  (let [image (reagent/atom nil)
        bg-image-style (fn [] (if-let [i @image]
                                {:background-image (str "url(" i ")")}
                                {:background-color "red"}))
        input-id "input-image"
        i18n @(subscribe [::app-subs/i18n])]
    (fn []
      [:div
       [:input {:type "file"
                :id input-id
                ;; :on-change #(change-read % image)
                :accept "image/*"}]
       ;; [:div {:height "300px" :width "300px" :style (bg-image-style)}]
       [:button {:class [:button :bg-red-700]
                 :on-click #(upload-image input-id)}
        "Upload Image"]
       [:button {:class [:button :bg-yellow-700]
                 :on-click #(dispatch [::events/upload-json {:key "hello123"}])}
        "Upload Json"]
       [:div [mint-asset]]])))


