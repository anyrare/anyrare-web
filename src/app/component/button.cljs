(ns app.component.button
  (:require [tailwind-hiccup.core :refer [tw]]))

(defn button-primary [text class]
  [:button
   (tw [:bg-primary :px-6 :py-2 :rounded-full :text-white :font-bold] class)
   text
   ])

(defn button-outline [text class]
  [:button
   (tw [:bg-white :border :px-6 :py-2 :rounded-full :font-bold] class)
   text
   ])

(defn button-circle [text class]
  [:button
   (tw [:bg-white :border :h-11 :w-11 :rounded-full :items-center :justify-center :font-bold] class)
   text
   ])