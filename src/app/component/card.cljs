(ns app.component.card
  (:require [tailwind-hiccup.core :refer [tw]]
            [app.component.svg :refer [icon-love]]))

(defn card [&{:keys [title thumbnail highest-bid]}]
  [:div
   (tw [:w-64 :border :p-4 :m-4 :rounded-lg])
   [:div
    [:img
     {:src thumbnail :class "rounded-lg object-cover h-64 w-64"}]
    ]
   [:div 
    (tw [:font-bold :mt-2])
    title]
   [:div
    (tw [:flex :justify-end])
    (if-not (nil? highest-bid) [:div (tw [:flex-auto :text-sm :justify-items-start])
     [:span (tw [:text-xs :font-bold :text-secondary]) "Higest Bid"]
     [:span (tw [:ml-1 :text-primary :font-bold]) (str highest-bid " ARA")]])
    [:button
     (tw [:w-12])
     [:div
      (tw [:flex])
      [:div (tw [:flex-auto])
       [icon-love 18 18]]
      [:div (tw [:flex-auto :text-sm :text-secondary]) (str 126)]]]]])
   