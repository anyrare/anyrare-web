(ns app.asset.views
  (:require [tailwind-hiccup.core :refer [tw]]))

(defn asset []
  [:div (tw [:flex :mt-6 :px-8])
   [:div (tw [:flex-auto :flex])
    [:div (tw [:mx-auto])
     [:img
      {:src "https://cf.lnwfile.com/2gwbkl.jpg" :class "rounded-lg object-cover"}]]]
   [:div (tw [:pl-8 "w-1/4" "lg:w-1/2" "xl:1/5" "2xl:1/6"])
    [:div [:h1 (tw [:text-3xl :font-bold]) "พระนางพญาเนื้อดำ พิมพ์เข่าโค้ง"]]
    [:div
     [:span
      (tw [:text-secondary :text-sm :font-bold])
      "Higest bid"]
     [:span
      (tw [:text-primary :text-sm :font-bold :ml-2])
      "1.0391 ARA"]]
    [:div
     (tw [:mt-2])
     "พระนางพญา เนื้อดำ พิมพ์เข่าโค้ง กรุวัดนางพญา พิษณุโลกผ่านพิธีกรรมปลุกเสกจากเกจิอาจารย์ชื่อดัง สุดยอดพุทธคุณที่ควรหามาบูชารุ่นนี้หายากมาก เหมาะกับการสะสมในคอลเลกชั่น..."]
    [:div
     (tw [:mt-8 :flex])
     [:div (tw ["w-1/2"])
      [:div (tw [:text-sm :font-bold])
       [:span (tw [:font-bold]) "Founder "]
       [:span (tw [:text-secondary]) " 10% royalties"]]
      [:div
       (tw [:flex :mt-2])
       [:img {:src "https://cdn.iconscout.com/icon/free/png-256/avatar-373-456325.png" :class "w-12"}]
       [:span (tw [:font-bold :ml-3 :mt-2]) "BinBangkruai"]]]
     [:div (tw ["w-1/2"])
      [:div (tw [:text-sm :font-bold])
       [:span "Founder"]]
      [:div
       (tw [:flex :mt-2])
       [:img {:src "https://cdn.iconscout.com/icon/free/png-256/avatar-373-456325.png" :class "w-12"}]
       [:span (tw [:font-bold :ml-3 :mt-2]) "JKBin"]]]]
    [:div
     (tw [:mt-8 :font-bold])
     [:span (tw [:pr-6]) "Details"]
     [:span (tw [:pr-6 :text-secondary]) "Bids"]
     [:span (tw [:pr-6 :text-secondary]) "History"]
     [:hr (tw [:mt-2])]]
    [:div (tw [:mt-4])
      [:div (tw [:text-sm :font-bold :text-secondary]) "Custodian"]
      [:div
       (tw [:flex :mt-2])
       [:img {:src "https://cdn.iconscout.com/icon/free/png-256/avatar-373-456325.png" :class "w-12 h-12"}]
       [:div
        (tw [:flex :flex-col])
        [:div (tw [:font-bold :ml-3 :flex-0]) "GPraCustodian"]
        [:div (tw [:font-bold :ml-3 :text-sm :text-secondary :flex]) "Bangkok, TH"]]]]
    [:div (tw [:mt-4 :text-sm])
     [:div (tw [:font-bold :text-secondary]) "Custodian Fees"]
     [:div
      (tw [:mt-2])
      [:p "Storage Fee: 0.102 ARA / Year"]
      [:p "Due Date: 10/9/2022 2:25 PM"]]
     [:div
      (tw [:mt-2])
      [:p "Insurance Coverage: 1.200 ARA"]
      [:p "Expired Date: 10/9/2022 2:25 PM"]]]
    [:div
     [:div (tw [:mt-4 :text-sm :text-secondary :font-bold]) "Auditor"]
     [:div (tw [:flex])
      [:div (tw ["w-1/2"])
       [:div
       (tw [:flex :mt-2])
       [:img {:src "https://cdn.iconscout.com/icon/free/png-256/avatar-373-456325.png" :class "w-12"}]
       [:span (tw [:font-bold :ml-3 :mt-2]) "JKBin"]]
       ]
      [:div (tw ["w-1/2" :text-sm])
       "Audited Date: 10/3/2020 1:37PM"]]]
    [:div
     [:div (tw [:mt-4 :text-sm :text-secondary :font-bold]) "Auditor Report"]
     [:div (tw [:mt-2]) "พระนางพญา เนื้อดำ พิมพ์เข่าโค้ง มีรอยบิ่นขอบบนซ้าย มีจุดตัด ปีพ.ศ.2490 รุ่นเมตตามหานิยม จัดสร้างที่วัดนางพญา จังหวัดพิษณุโลก จำนวน 20,000 องค์ ด้านหลังพิมพ์อักขระพิเศษ มีรอยนูนด้านซ้ายล่าง"]]]])