(ns anyrare-web.page.admin.views
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [subscribe dispatch]]
            [anyrare-web.page.admin.events :as events]
            [anyrare-web.lib.ipfs :as ipfs]))

(defn upload-image
  [input-id]
  (let [el (.getElementById js/document input-id)
        name (.-name el)
        file (aget (.-files el) 0)
        form-data (js/FormData.)
        _ (.append form-data "file" file)]
    (dispatch [::events/upload-file form-data])))


(defn admin []
  (let [image (reagent/atom nil)
        bg-image-style (fn [] (if-let [i @image]
                               {:background-image (str "url(" i ")")}
                               {:background-color "red"}))
        input-id "input-image"]
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
       ])))
       
