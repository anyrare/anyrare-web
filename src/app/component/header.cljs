(ns app.component.header)

(defn header []
  [:div {:class [:flex :h-12 :p-4 :w-full :justify-end :bg-white :fixed :z-10]}
   [:span {:class [:font-bold :text-xl]} "AnyRare"]])