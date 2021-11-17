(ns app.asset.views
  (:require
   [re-frame.core :refer [subscribe dispatch]]
   [herb.core :refer [<class]]
   [tailwind-hiccup.core :refer [tw]]
   [app.asset.subs :as subs]
   [app.asset.events :as events]
   [app.component.button :refer [button-primary button-outline]]
   [app.config.i18n :refer [i18n]]
   [app.component.svg :refer [menu-burger]]
   [app.lib.format :refer [format-money]]))

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

(defn image-carousel [attachments]
  [:div {:id "image-slider" :class "splide"}
   [:div {:class "splide__track"}
    [:ul {:class "splide__list"}
     (for [index (range (count attachments))]
       [:li {:class "splide__slide 2xl:rounded-xl"
             :key index}
        [:img {:src ((get attachments index) :url)}]])]]])

(defn title [text]
  [:h1 (tw [:text-2xl :font-kanit :font-medium]) text])

(defn subtitle [price]
  [:div
   [:span (tw [:font-kanit :font-medium]) "ราคาสูงสุด"]
   [:span (tw [:font-kanit :font-medium :text-transparent :bg-clip-text
               :bg-gradient-to-br :from-red-400 :to-purple-800 :ml-1])
    (str price " ARA")]
   [:span (tw [:text-secondary :text-sm :ml-1]) "~36,203.35 บาท"]])

(defn description [text showFull]
  [:p (tw [:py-2])
   (if (true? showFull) text (subs text 0 (min (count text) 100)))])

(defn avatar [src]
  [:img
   {:class "rounded-full object-cover w-12 h-12"
    :src src}])

(defn avartar-with-username [src username]
  [:div (tw [:flex])
   (avatar src)
   [:div (tw [:font-medium :ml-2 :mt-2]) username]])

(defn founder-owner [founder owner]
  [:div (tw [:grid :grid-cols-2 :gap-x-2 :mt-2])
   [:div
    [:div (tw [:font-kanit :font-medium :text-secondary :text-sm :mb-2]) "ผู้ค้นพบสินทรัพย์"]
    (avartar-with-username (founder :thumbnail) (founder :name))]
   [:div
    [:div (tw [:font-kanit :font-medium :text-secondary :text-sm :mb-2]) "เจ้าของปัจจุบัน"]
    (avartar-with-username (owner :thumbnail) (owner :name))]])

(defn tabs-menu [menus active-index]
  [:div (tw [:horizontal-scrollbar :overflow-x-hidden :relative])
   [:div (tw [:grid :grid-flow-col :auto-cols-max :my-4 :border-b])
    (for [index (range (count menus))]
      [:div {:on-click #(dispatch [:set-active-tab index])
             :class "cursor-pointer select-none"
             :key index}
       [:div (tw (into [:flex-auto :mr-6 :last:mr-0 :font-kanit :font-medium :text-center]
                       (if (= index active-index) [:border-b-4 :border-red-700 :text-black] [:text-secondary])))
        (get menus index)]])]])

(defn panel-bid [founder custodian]
  [:div (tw [:pb-32 :md:pb-32])
   [:div (tw [:flex])
    (avatar (founder :thumbnail))
    [:div (tw [:flex :flex-col :pb-4])
     [:div (tw [:ml-2])
      [:span (tw [:font-kanit :font-medium]) "12.0235 ARA"]
      [:span (tw [:text-secondary :ml-1]) "โดย"]
      [:span (tw [:font-kanit :font-medium :ml-1]) "panasun"]
      [:span (tw [:text-secondary :ml-1]) "(143)"]]
     [:div (tw [:ml-2])
      [:span (tw [:text-secondary :text-sm]) "06 พ.ย. 2564 - 23:17:51 น."]
      [:span (tw [:text-primary :text-sm :ml-1]) "ถึงราคาขั้นต่ำแล้ว"]]]]
   [:div (tw [:flex])
    (avatar (custodian :thumbnail))
    [:div (tw [:flex :flex-col :pb-4])
     [:div (tw [:ml-2])
      [:span (tw [:font-kanit :font-medium]) "12.0235 ARA"]
      [:span (tw [:text-secondary :ml-1]) "โดย"]
      [:span (tw [:font-kanit :font-medium :ml-1]) "panasun"]
      [:span (tw [:text-secondary :ml-1]) "(143)"]]
     [:div (tw [:ml-2])
      [:span (tw [:text-secondary :text-sm]) "06 พ.ย. 2564 - 23:17:51 น."]]]]])

(defn panel-details [address auditor custodian]
  [:div (tw [:pb-32 :md:pb-32])
   [:div (tw [:mb-2])
    [:span (tw [:text-secondary :font-kanit :font-medium :text-sm]) "รหัสสินทรัพย์"]
    [:span (tw [:font-kanit :font-medium :ml-1]) address]]
   [:div (tw [:grid :grid-cols-2 :mb-4])
    [:div
     [:div (tw [:font-kanit :font-medium :text-secondary :text-sm :mb-2]) "ผู้ตรวจสอบ"]
     (avartar-with-username (auditor :thumbnail) (auditor :name))]
    [:div
     [:div (tw [:font-kanit :font-medium :text-secondary :text-sm :mb-2]) "ผู้รักษาสินทรัพย์"]
     (avartar-with-username (custodian :thumbnail) (custodian :name))]]
   [:div (tw [:mb-4])
    [:div (tw [:font-kanit :font-medium :text-secondary :text-sm]) "รายละเอียดการตรวจสอบ"]
    [:p (get-in auditor [:auditor-report :th])]
    [:p "วันที่ตรวจสอบ: 12 ก.ย. 63"]
    [:p "บัตรรับรองพระ:"
     [:span (tw [:text-primary :font-medium :ml-1])
      (auditor :audit-address)]]]
   [:div (tw [:mb-4])
    [:div (tw [:font-kanit :font-medium :text-secondary :text-sm]) "การเก็บรักษาสินทรัพย์"]
    [:p (str "ผู้รักษาสินทรัพย์: " (custodian :full-name))]
    [:p "วันที่เริ่มต้นเก็บรักษา: 12 กันยายน 2563"]
    [:p "สัญญาการเก็บรักษาสินทรัพย์:"
     [:span (tw [:text-primary :font-medium :ml-1])
      (custodian :contract-address)]]]
   [:div (tw [:mb-4])
    [:div (tw [:font-kanit :font-medium :text-secondary :text-sm :mb-1]) "ค่าสิทธิ"]
    [:table (tw [:table-fixed :w-full :border-collapse :border])
     [:tr
      [:td (tw ["w-1/2" :border :pl-4 :py-1]) "ผู้ค้นพบสินทรัพย์"]
      [:td (tw ["w-1/2" :border :pl-4 :py-1]) "10%"]]
     [:tr
      [:td (tw [:border :pl-4 :py-1]) "ผู้รักษาสินทรัพย์"]
      [:td (tw [:border :pl-4 :py-1]) "2.5%"]]]]])

(defn panel-tools []
  [:div (tw [:pb-32 :md:pb-32])
   [:div (tw [:mb-2]) (button-outline "จัดประมูล" [:w-full])]
   [:div (tw [:mb-2]) (button-outline "ตั้งราคาขาย" [:w-full])]
   [:div (tw [:mb-2]) (button-outline "สร้างชุดสะสม" [:w-full])]
   [:div (tw [:mb-2]) (button-outline "ถอนสินทรัพย์" [:w-full])]
   [:div (tw [:mb-2]) (button-outline "ปิดการขาย" [:w-full])]])

(defn panel-display [active-index asset]
  (case active-index
    0 (panel-bid (asset :founder) (asset :custodian))
    1 (panel-details (asset :address) (asset :auditor) (asset :custodian))
    3 (panel-tools)
    (panel-details (asset :address) (asset :auditor) (asset :custodian))))

(defn offer-bar-auction [auction]
  [:div (tw [:fixed :bg-white :bottom-0 :w-full "md:w-5/12" "2xl:w-3/12" "4xl:w-2/12" :-mx-2 :md:-ml-2 :md:pr-4 :p-2
             :border-t-2 :mt-36 :offer-bar])
   [:div (tw [:grid :grid-cols-2 :text-center])
    [:div (tw [:border-r])
     [:div (tw [:font-kanit :font-medium :text-xs])
      [:span (tw [:text-secondary]) "ผู้ให้ราคาสูงสุด"]
      [:span (tw [:ml-1]) (str (get-in auction [:highest-bid :name]) " (" (get-in auction [:highest-bid :total-bid]) ")")]]
     [:div (tw [:font-kanit])
      [:span (tw [:font-medium :text-lg :text-transparent :bg-clip-text
                  :bg-gradient-to-br :from-red-400 :to-purple-800]) 
       (str (format-money 
                (/ (auction :highest-price) 
                   (auction :highest-price-denominator)))) " ARA"]]]
    [:div
     [:div (tw [:font-kanit :font-medium :text-xs])
      [:span (tw [:text-secondary]) "เหลือเวลาประมูล"]]
     [:div (tw [:font-kanit :font-medium :text-sm :mt-1]) "0 วัน 4 ชั่วโมง 5 นาที 10 วินาที"]]]
   [:div (tw [:mt-2])
    (button-primary "เสนอราคา" [:w-full :shadow-md])]])

(defn popup []
  [:div (tw [:fixed :top-0 :left-0 :w-screen :h-screen :bg-black :z-50 :bg-opacity-95])
   [:div (tw [:fixed :right-0])
    [:button (tw [:h-10 :w-10 :bg-gray-800 :text-white :rounded-full :m-2]) "X"]]
   [:div (tw [:flex :fixed :bottom-0 :w-screen :mx-auto :md:justify-center :md:h-screen :md:items-center])
    [:div (tw [:flex-auto])]
    [:div (tw [:w-full "md:w-3/5" "lg:w-3/8" "xl:w-5/12" "2xl:w-4/12" "3xl:w-3/12"
               :bg-white :pt-4 :rounded-t-xl :md:rounded-xl :p-4])
     [:h2 (tw [:font-kanit :font-medium :text-xl]) "เสนอราคา"]
     [:div (tw [:mt-2])
      [:span (tw [:text-secondary]) "คุณกำลังเสนอราคารายการประมูล"]
      [:span (tw [:font-bold :ml-1]) "พระปิดตาหลวงพ่อปานวัดเครือวัลย์"]]
     [:div (tw [:font-kanit :text-sm :text-secondary :font-medium :mt-4]) "ราคาที่ต้องการเสนอ"]
     [:input {:type "number" :class "border-b w-full mt-2 font-kanit font-medium p-1"}]
     [:div (tw [:text-xs :text-secondary :mt-2]) "ราคาขั้นต่ำ 12.9584 ARA"]
     [:table (tw [:table-fixed :w-full :text-sm :mt-2])
      [:tr
       [:td (tw ["w-2/3" :text-secondary]) "ยอดเงินในกระเป๋าสตางค์ของคุณ"]
       [:td (tw [:text-right :font-kanit :font-medium]) "459.236 ARA"]]
      [:tr
       [:td (tw ["w-2/3" :text-secondary]) "ค่าธรรมเนียม"]
       [:td (tw [:text-right :font-kanit :font-medium]) "0.329456 ARA"]]
      [:tr
       [:td (tw ["w-2/3" :text-secondary]) "ยอดชำระ"]
       [:td (tw [:text-right :font-kanit :font-medium]) "13.329456 ARA"]]
      [:tr
       [:td (tw ["w-2/3" :text-secondary]) ""]
       [:td (tw [:text-right :font-kanit :text-secondary :text-xs]) "~36,203.35 บาท"]]]
     [:div (tw [:mt-2])
      (button-primary "ยืนยันการเสนอราคา" [:w-full])]]
    [:div (tw [:flex-auto])]]])

(defn panel []
  (let [asset @(subscribe [::subs/asset])
        active-index @(subscribe [::subs/tab-active-index])]
    [:div (tw [:px-2 :mt-4 :md:mt-0])
     (title (asset :title))
     (subtitle (format-money 
                (/ (get-in asset [:auction :highest-price]) 
                   (get-in asset [:auction :highest-price-denominator]))))
     (description (asset :description) true)
     (founder-owner (asset :founder) (asset :owner))
     (tabs-menu ["เสนอราคา" "รายละเอียด" "ประวัติ" "เครื่องมือ"] active-index)
     (panel-display active-index asset)
     (offer-bar-auction (asset :auction))
    ;;  (popup)
     ]))

(defn asset []
  (let [asset @(subscribe [::subs/asset])]
  (layout
   (image-carousel (asset :attachments))
   (panel))))

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