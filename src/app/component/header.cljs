(ns app.component.header
  (:require [tailwind-hiccup.core :refer [tw]]
            [app.component.svg :refer [icon-moon]]
            [app.router :refer [url-for]]
            [app.component.button :refer [button-primary button-outline button-circle]]))

(defn header []
  [:div
   (tw [:flex :h-14 :p-4 :w-full :justify-end :bg-white :fixed :z-10])
   [:div
    (tw [:flex-auto :justify-items-start])
    [:span (tw [:font-bold :text-xl])
     [:a {:href (url-for :home)}
      "AnyRare"]]]
   [:div
    (tw [:flex :right-0])
    ;; [:div
    ;;  (tw [:font-bold :font-kanit])
    ;;  [:span (tw [:mx-4]) [:a {:href (url-for :explorer)} "Explorer"]]
    ;;  [:span (tw [:mx-4]) [:a {:href (url-for :profile)} "My Profile"]]
    ;;  [:span (tw [:mx-4]) [:a {:href (url-for :following)} "Following"]]
    ;;  [:span (tw [:mx-4]) [:a {:href (url-for :activity)} "Activity"]]]
    [:div
     (tw [:-mt-3])
    ;;  (button-primary "Create" [])
    ;;  (button-outline "Sign in" [:ml-2])
     (button-circle "EN" [:ml-2])
     (button-circle [icon-moon 16 12] [:ml-2 :pl-3 :text-center])]]])