(ns anyrare-web.page.tool.views
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [dispatch]]
            [anyrare-web.events :as app-events]))

(defn tool []
  [:div {:class [:w-full :md:mx-auto :px-4 :md:px-36]}
   [:h1 {:class [:mt-4 :text-center :text-xl :font-kanit :font-medium]}
    "เครื่องมือ"]
   [:div {:class [:grid :grid-cols-1 :gap-4 :mt-4]}
    [:button {:class [:button :border :border-gray-100 :hover:bg-gray-100 :h-16
                      :rounded-full]
              :on-click #(dispatch [::app-events/navigate :tool-mint])}
     "สร้างสินทรัพย์"]
    [:button {:class [:button :border :border-gray-100 :hover:bg-gray-100 :h-16
                      :rounded-full]
              :on-click #(dispatch [::app-events/navigate :tool-custodian-sign])}
     "สินทรัพย์รอยืนยันการจัดเก็บ"]
    [:button {:class [:button :border :border-gray-100 :hover:bg-gray-100 :h-16
                      :rounded-full]
              :on-click #(dispatch [::app-events/navigate :tool-founder-sign])}
     "สินทรัพย์รอยืนยันความเป็นเจ้าของ"]
    [:button {:class [:button :border :border-gray-100 :hover:bg-gray-100 :h-16
                      :rounded-full]
              :on-click #(dispatch [::app-events/navigate :tool-collection])}
     "สร้างชุดสะสม"]
    [:button {:class [:button :border :border-gray-100 :hover:bg-gray-100 :h-16
                      :rounded-full]
              :on-click #(dispatch [::app-events/navigate :tool-open-proposal])}
     "สร้างนโยบาย"]
    [:button {:class [:button :border :border-gray-100 :hover:bg-gray-100 :h-16
                      :rounded-full]
              :on-click #(dispatch [::app-events/navigate :tool-vote-proposal])}
     "โหวตนโยบาย"]]])

