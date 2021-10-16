(ns app.component.card
  (:require [tailwind-hiccup.core :refer [tw]]
            [app.component.svg :refer [icon-love]]
            [app.component.button :refer [button-circle]]))

(defn card [&{:keys [title thumbnail]}]
  [:div
   (tw [:w-64 :border :p-4 :m-4 :rounded-lg])
    [:img
     {:src thumbnail :class "rounded-lg"}]
   [:div 
    (tw [:font-bold :mt-2])
    title]
   [:div
    (tw [:flex :justify-end])
    [:div (tw [:flex-auto :text-sm :justify-items-start])
    [:span (tw [:text-secondary]) "Higest Bid"]
    [:span (tw [:ml-1 :text-primary :font-bold]) "1.0391 ARA"]]
    [:button
     (tw [:w-12])
     [:div
      (tw [:flex])
      [:div (tw [:flex-auto])
       [icon-love 18 18]]
      [:div (tw [:flex-auto :text-sm :text-secondary]) (str 126)]]]]])
   