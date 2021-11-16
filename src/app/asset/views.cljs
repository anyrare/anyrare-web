(ns app.asset.views
  (:require
   [re-frame.core :refer [subscribe dispatch]]
   [herb.core :refer [<class]]
   [tailwind-hiccup.core :refer [tw]]
   [app.asset.subs :as subs]
   [app.asset.events :as events]
   [app.config.i18n :refer [i18n]]))

(defn layout [carousel panel]
  [:div (tw [:grid :grid-cols-12 :gap-x-2 :md:gap-x-4])
   [:div (tw [:col-start-1 :col-end-13 :md:col-end-8
              :lg:col-end-8 :2xl:col-end-10 :4xl:col-end-11])
    [:div (tw [:lg:max-w-2xl :xl:max-w-4xl :mx-auto])
     carousel]]
   ;; ]]
   [:div (tw [:col-start-1 :col-end-13 :md:col-start-8 :lg:col-start-8
              :2xl:col-start-10 :4xl:col-start-11])
    panel]])

(defn image-carousel [images]
  [:div {:id "image-slider" :class "splide"}
   [:div {:class "splide__track"}
    [:ul {:class "splide__list"}
     (for [image images]
       [:li {:class "splide__slide 2xl:rounded-xl"}
        [:img {:src image}]])]]])

(defn title [text]
  [:h1 (tw [:text-2xl :font-kanit :font-medium]) text])

(defn subtitle [price]
  [:div 
   [:span (tw [:font-kanit :font-medium]) "ราคาสูงสุด"]
   [:span (tw [:font-kanit :font-medium :text-transparent :bg-clip-text 
               :bg-gradient-to-br :from-red-400 :to-purple-800 :ml-1]) 
    (str price "ARA")]
   [:span (tw [:text-secondary :text-sm :ml-1]) "~36,203.35 บาท"]]
  )

(defn description [text showFull]
  [:p (tw [:py-2]) 
   (if (true? showFull) text (subs text 0 (min (count text) 100)))])

(defn avatar [src]
  [:img
   {:class "rounded-full object-cover w-12 h-12"
    :src src}])

(defn founder-owner [founder owner]
  [:div (tw [:grid :grid-cols-2 :gap-x-2])
   [:div 
    [:div (tw [:font-kanit :font-medium :text-secondary :text-sm]) "ผู้ค้นพบสินทรัพย์"]
    [:div (tw [:flex :mt-2]) 
     (avatar "https://www.thebangkokinsight.com/wp-content/uploads/2020/02/batch_1-102.jpg")
     [:div (tw [:font-medium :ml-2 :mt-2]) "JennieJam"]]]
   [:div 
    [:div (tw [:font-kanit :font-medium :text-secondary :text-sm]) "ผู้ค้นพบสินทรัพย์"]
    [:div (tw [:flex :mt-2]) 
     (avatar "https://s.isanook.com/wo/0/rp/r/w728/ya0xa0m1w0/aHR0cHM6Ly9zLmlzYW5vb2suY29tL3dvLzAvdWQvMjcvMTM1NTY5L2wxLmpwZw==.jpg")
     [:div (tw [:font-medium :ml-2 :mt-2]) "lisaBP"]]]])

(defn panel []
  [:div (tw [:px-2 :mt-4 :md:mt-0])
   (title "พระปิดตาหลวงพ่อปานวัดเครือวัลย์ ปี พ.ศ.2515")
   (subtitle 12.334)
   (description "+บัตรพระแท้+พระปิดตาหลวงพ่อปาน วัดเครือวัลย์ พิมพ์พุทโธหลังเรียบ เนื้อผงลงรักปิดทอง จ.ชลบุรี พระปิดตาหลวงพ่อปาน วัดเครือวัลย์ พิมพ์พุทโธหลังเรียบ เนื้อผงลงรักปิดทอง จ.ชลบุรี ผสมผงเก่าอิทธิเจ หลวงพ่อแก้ว วัดเครือวัลย์" true)
   (founder-owner nil nil)
   ])


   

(defn asset []
  (layout
   (image-carousel
    ["http://g-pra.com/Auctions1/get_auc1_img.php?data=front&id=24721270&date=2021-11-14"
     "http://g-pra.com/Auctions1/get_auc1_img.php?data=back&id=24721270&date=2021-11-14"
     "http://g-pra.com/Auctions1/get_auc1_img.php?data=third&id=24721270&date=2021-11-14"
     "http://g-pra.com/Auctions3/get_auc3_img.php?id=16443819"])
   (panel)))

;; (defn asset []
;;   (let [asset (subscribe [::subs/asset])]
;;     [:div (tw [:flex :px-2 :flex-wrap])
;;      [:div (tw [:flex :flex-auto])
;;       [:div (tw [:mx-auto :lg:max-w-lg :xl:max-full])
;;        [:div {:id "slider" :class "swipe w-full"}
;;         [:div {:class ["swipe-wrap w-full"]}
;;          [:div {:class "swiper-slide w-64 bg-red-700"} "Slide 1"]
;;          [:div {:class "swiper-slide w-64 bg-yellow-700"} "Slide 2"]
;;          [:div {:class "swiper-slide w-64 bg-green-700"} "Slide 3"]]]
;;        [:button {:class [:bg-primary] :on-click #(dispatch [::events/add-slider])} "Add slider"]]]
;;      [:div (tw [:mt-4 :w-full :lg:max-w-md :xl:max-w-lg])
;;       [:div [:h1 (tw [:text-3xl :font-kanit :font-medium]) (get-in @asset [:title :th])]]
;;       [:div
;;        [:span
;;         (tw [:text-secondary :text-sm :font-kanit :font-medium])
;;         (i18n :highest-bid)]
;;        [:span
;;         (tw [:text-sm :font-bold :ml-1 :text-transparent :bg-clip-text :bg-gradient-to-br :from-red-400 :to-purple-800])
;;         "1.0391 ARA"]]
;;       [:div
;;        (tw [:mt-2])
;;        (description "พระนางพญา เนื้อดำ พิมพ์เข่าโค้ง กรุวัดนางพญา พิษณุโลกผ่านพิธีกรรมปลุกเสกจากเกจิอาจารย์ชื่อดัง สุดยอดพุทธคุณที่ควรหามาบูชารุ่นนี้หายากมาก เหมาะกับการสะสมในคอลเลกชั่น..." false)]
;;       [:div
;;        (tw [:mt-8 :flex])
;;        [:div (tw ["w-1/2"])
;;         [:div (tw [:text-sm :font-bold])
;;          [:span (tw [:font-bold]) "Founder "]
;;          [:span (tw [:text-secondary]) " 10% royalties"]]
;;         [:div
;;          (tw [:flex :mt-2])
;;          [:img {:src "https://cdn.iconscout.com/icon/free/png-256/avatar-373-456325.png" :class "w-12"}]
;;          [:span (tw [:font-bold :ml-3 :mt-2]) "BinBangkruai"]]]
;;        [:div (tw ["w-1/2"])
;;         [:div (tw [:text-sm :font-bold])
;;          [:span "Founder"]]
;;         [:div
;;          (tw [:flex :mt-2])
;;          [:img {:src "https://cdn.iconscout.com/icon/free/png-256/avatar-373-456325.png" :class "w-12"}]
;;          [:span (tw [:font-bold :ml-3 :mt-2]) "JKBin"]]]]
;;       [:div
;;        (tw [:mt-8 :font-bold])
;;        [:span (tw [:pr-6]) "Details"]
;;        [:span (tw [:pr-6 :text-secondary]) "Bids"]
;;        [:span (tw [:pr-6 :text-secondary]) "History"]
;;        [:hr (tw [:mt-2])]]
;;       [:div (tw [:mt-4])
;;        [:div (tw [:text-sm :font-bold :text-secondary]) "Custodian"]
;;        [:div
;;         (tw [:flex :mt-2])
;;         [:img {:src "https://cdn.iconscout.com/icon/free/png-256/avatar-373-456325.png" :class "w-12 h-12"}]
;;         [:div
;;          (tw [:flex :flex-col])
;;          [:div (tw [:font-bold :ml-3 :flex-0]) "GPraCustodian"]
;;          [:div (tw [:font-bold :ml-3 :text-sm :text-secondary :flex]) "Bangkok, TH"]]]]
;;       [:div (tw [:mt-4 :text-sm])
;;        [:div (tw [:font-bold :text-secondary]) "Custodian Fees"]
;;        [:div
;;         (tw [:mt-2])
;;         [:p "Storage Fee: 0.102 ARA / Year"]
;;         [:p "Due Date: 10/9/2022 2:25 PM"]]
;;        [:div
;;         (tw [:mt-2])
;;         [:p "Insurance Coverage: 1.200 ARA"]
;;         [:p "Expired Date: 10/9/2022 2:25 PM"]]]
;;       [:div
;;        [:div (tw [:mt-4 :text-sm :text-secondary :font-bold]) "Auditor"]
;;        [:div (tw [:flex])
;;         [:div (tw ["w-1/2"])
;;          [:div
;;           (tw [:flex :mt-2])
;;           [:img {:src "https://cdn.iconscout.com/icon/free/png-256/avatar-373-456325.png" :class "w-12"}]
;;           [:span (tw [:font-bold :ml-3 :mt-2]) "JKBin"]]]
;;         [:div (tw ["w-1/2" :text-sm])
;;          "Audited Date: 10/3/2020 1:37PM"]]]
;;       [:div
;;        [:div (tw [:mt-4 :text-sm :text-secondary :font-bold]) "Auditor Report"]
;;        [:div (tw [:mt-2]) "พระนางพญา เนื้อดำ พิมพ์เข่าโค้ง มีรอยบิ่นขอบบนซ้าย มีจุดตัด ปีพ.ศ.2490 รุ่นเมตตามหานิยม จัดสร้างที่วัดนางพญา จังหวัดพิษณุโลก จำนวน 20,000องค์ ด้านหลังพิมพ์อักขระพิเศษ มีรอยนูนด้านซ้ายล่าง"]]
;;       [:div
;;        (tw [:mt-4])
;;        [:hr]]]]))