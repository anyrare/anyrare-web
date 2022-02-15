(ns anyrare-web.page.tool.views
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [subscribe dispatch]]
            [fork.re-frame :as fork]
            [anyrare-web.page.tool.events :as events]
            [anyrare-web.page.tool.subs :as subs]
            [anyrare-web.subs :as app-subs]
            [anyrare-web.lib.utils :as utils]))


(defn tool []
  [:div {:class [:w-full :md:mx-auto :px-4 :md:px-36]}
   [:h1 {:class [:mt-4 :text-center :text-xl :font-kanit :font-medium]}
    "เครื่องมือ"]
   [:div {:class [:grid :grid-cols-1 :gap-4 :mt-4]}
    [:button {:class [:button :border :border-gray-100 :hover:bg-gray-100 :h-16
                      :rounded-full]} "สร้างสินทรัพย์"]
    [:button {:class [:button :border :border-gray-100 :hover:bg-gray-100 :h-16
                      :rounded-full]} "สินทรัพย์รอยืนยันการจัดเก็บ"]
    [:button {:class [:button :border :border-gray-100 :hover:bg-gray-100 :h-16
                      :rounded-full]} "สินทรัพย์รอยืนยันความเป็นเจ้าของ"]
    [:button {:class [:button :border :border-gray-100 :hover:bg-gray-100 :h-16
                      :rounded-full]} "สร้างชุดสะสม"]
    [:button {:class [:button :border :border-gray-100 :hover:bg-gray-100 :h-16
                      :rounded-full]} "สร้างนโยบาย"]
    [:button {:class [:button :border :border-gray-100 :hover:bg-gray-100 :h-16
                      :rounded-full]} "โหวตนโยบาย"]]])
