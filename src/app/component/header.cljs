(ns app.component.header
  (:require [tailwind-hiccup.core :refer [tw]]
            [app.component.svg :refer [icon-moon menu-burger]]
            [app.router :refer [url-for]]
            [app.component.button :refer [button-primary button-outline button-circle button-transparent]]))

(defn header []
  [:div
   (tw [:flex :h-12 :p-4 :w-full :justify-end :bg-white :fixed :z-10])
   [:div (tw [:-ml-2 :-mt-2])
    (button-transparent (menu-burger 26 26) [:pl-2 :pt-1])]
   [:div
    (tw [:flex-auto :justify-items-start :ml-2 :-mt-1])
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
     (button-circle "EN" [:ml-2])]]])