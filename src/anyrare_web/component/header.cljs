(ns anyrare-web.component.header
  (:require [spade.core :refer [defclass]]))

(defclass header-shadow []
  {:box-shadow "0 2px 4px 0 rgba(0,0,0,.2)"})

(defn header []
  [:div
   {:class [:bg-white :h-12 :text-lg :pt-2 :pl-2 :font-kanit :font-medium (header-shadow)]}
   [:div {:class [:mx-auto :max-w-screen-xl]} "AnyRare"]])
