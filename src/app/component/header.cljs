(ns app.component.header
  (:require [tailwind-hiccup.core :refer [tw]]
            [app.component.button :refer [button-primary button-outline button-circle]]))

(defn header []
  [:div
   (tw [:flex :h-14 :p-4 :shadow-md :w-full :justify-end])
   [:div
    (tw [:flex-auto :justify-items-start])
    [:span (tw [:font-bold :text-xl])
     "AnyRare"]]
   [:div
    (tw [:flex :right-0])
    [:div
     [:span (tw [:mx-4]) "Explorer"]
     [:span (tw [:mx-4]) "My Profile"]
     [:span (tw [:mx-4]) "Following"]
     [:span (tw [:mx-4]) "Activity"]]
    [:div
     (tw [:-mt-3])
     (button-primary "Create" [])
     (button-outline "Sign in" [:ml-2])
     (button-circle "☾" [:ml-2])
     (button-circle "EN" [:ml-2])]]
   ])