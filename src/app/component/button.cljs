(ns app.component.button
  (:require [tailwind-hiccup.core :refer [tw]]))

(defn button-primary [text class]
  [:button
   (tw [:bg-primary :active:bg-primary-400 :px-6 :py-2 :rounded-full :text-white :font-medium :font-kanit] class)
   text])

(defn button-outline [text class]
  [:button
   (tw [:bg-white :active:bg-gray-100 :border :px-6 :py-2 :rounded-full :font-medium :font-kanit] class)
   text])

(defn button-circle [text class]
  [:button
   (tw [:bg-white :border :h-10 :w-10 :rounded-full :items-center :justify-center :font-bold] class)
   text])

(defn button-transparent [text class]
  [:button
   (tw [:bg-white :active:bg-gray-100 :h-10 :w-10 :rounded-full :items-center :justify-center :font-bold] class)
   text])

