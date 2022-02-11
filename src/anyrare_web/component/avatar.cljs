(ns anyrare-web.component.avatar
  (:require
   [anyrare-web.lib.format :refer [trim-username]]))

(defn avatar [src]
  [:img
   {:class [:rounded-full :object-cover :w-12 :h-12]
    :src src}])

(defn avatar-with-username [src username address]
  [:div {:class [:flex]}
   (avatar src)
   [:div {:class [:font-kanit :font-medium :ml-2 :mt-2]} (trim-username username)]])
