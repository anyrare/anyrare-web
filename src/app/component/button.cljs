(ns app.component.button
  (:require [tailwind-hiccup.core :refer [tw]]))

(defn button-primary [text class]
  [:button
   (tw [:bg-primary :hover:bg-primary-200 :active:bg-primary-300 :px-6 :py-2 :rounded-full :text-white :font-medium :font-kanit] class)
   text])

(defn button-outline [text class]
  [:button
   (tw [:bg-white :hover:bg-gray-100 :active:bg-gray-200 :border :px-6 :py-2 :rounded-full :font-medium :font-kanit] class)
   text])

(defn button-circle [text class]
  [:button
   (tw [:bg-white :border :h-11 :w-11 :rounded-full :items-center :justify-center :font-bold] class)
   text])