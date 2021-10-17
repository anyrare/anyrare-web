(ns app.asset.views
  (:require [tailwind-hiccup.core :refer [tw]]))

(defn asset []
  [:div (tw [:flex :mt-6 :px-8])
   [:div (tw [:flex-auto :flex])
    [:div (tw [:mx-auto])
     [:img
      {:src "https://cf.lnwfile.com/2gwbkl.jpg" :class "rounded-lg object-cover"}]]
    ]
   [:div (tw [:pl-8 "w-1/4"])
    [:div [:h1 (tw [:text-3xl :font-bold])"พระนางพญาเนื้อดำ พิมพ์เข่าโค้ง"]]
    [:div
     [:span 
      (tw [:text-secondary :text-sm :font-bold]) 
      "Higest bid"]
     [:span 
      (tw [:text-primary :text-sm :font-bold :ml-2]) 
      "1.0391 ARA"]]
    [:div
     (tw [:mt-2])
     "พระนางพญา เนื้อดำ พิมพ์เข่าโค้ง กรุวัดนางพญา พิษณุโลกผ่านพิธีกรรมปลุกเสกจากเกจิอาจารย์ชื่อดัง สุดยอดพุทธคุณที่ควรหามาบูชารุ่นนี้หายากมาก เหมาะกับการสะสมในคอลเลกชั่น..."]]])