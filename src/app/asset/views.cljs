(ns app.asset.views
  (:require
   [re-frame.core :refer [subscribe dispatch]]
   [herb.core :refer [<class]]
   [tailwind-hiccup.core :refer [tw]]
   [app.asset.subs :as subs]
   [app.asset.events :as events]
   ["@splidejs/splide" :as Splide]
   [app.config.i18n :refer [i18n]]))


(defn image-carousel [images]
  (for [image images]
    [:div 
     [:img {:src image}]]))

;; (def swipe (Swipe. (.getElementById js/document "slider")))



;; (set! (.. js/window -mySwipe) swipe)

(defn description [text showFull]
  [:div (if (true? showFull) text (subs text 0 (min (count text) 100)))])

(defn asset []
  [:div
   [:div {:id "image-slider" :class "splide"}
    [:div {:class "splide__track"}
     [:ul {:class "splide__list"}
      [:li {:class "splide__slide "}
       [:img {:src "https://image.shutterstock.com/image-photo/closeup-three-square-instant-photo-260nw-364181222.jpg"}]]
      [:li {:class "splide__slide"}
       [:img {:src "https://www.vuescript.com/wp-content/uploads/2019/09/Vue.js-Card-Carousel-For-Web.png"}]]
      [:li {:class "splide__slide"}
       [:img {:src "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcREMxb-miDguY0jOpy1kYUPDlTR6sjWFVRWF-ucdBnyBpKPrSRS3C67nFnI5A-TOtBS2pY&usqp=CAU"}]]
      [:li {:class "splide__slide"}
       [:img {:src "https://mediatemple.net/blog/wp-content/uploads/2016/10/chris-coyier-css-tricks-carousel_1665x705.png"}]]]]]])

;; (defn asset []
;;   [Carousel
;;    [:img {:src "https://via.placeholder.com/400/ffffff/c0392b/&text=slide1"}]
;;    [:img {:src "https://via.placeholder.com/400/ffffff/c0392b/&text=slide2"}]
;;    [:img {:src "https://via.placeholder.com/400/ffffff/c0392b/&text=slide3"}]])

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