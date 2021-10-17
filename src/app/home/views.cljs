(ns app.home.views
  (:require 
   [re-frame.core :refer [subscribe dispatch]]
   [herb.core :refer [<class]]
   [app.router :refer [url-for]]
   [app.component.card :refer [card]]
   [app.subs :as subs]
   [app.events :as events]
   [tailwind-hiccup.core :refer [tw]]))

(defn background [color] {:background color :padding "10px 10px 10px 10px" :color "#FFFFFF"})

(def color-transition [:transition-colors :ease-in-out])
(def short-duration [:duration-300])
(def hover-colors [:hover:text-white :hover:bg-red-500])

(defn home []
  (let [name (subscribe [::subs/name])
        test (subscribe [::subs/test])]
    [:div
     [:p {:style {:font-size "32px" :color "#930293" :text-decoration "underline"}}
      "Test value: " @test]
     [:p {:class (<class background "red")} "Name value: " @name]
     [:button {:on-click #(dispatch [::events/name-change "Name Changed!"])} "Change name"]
     [:a {:href (url-for :asset)} "Click Change Route"]
     [:button
      (tw [:mx-3 :my-4 :py-4 :font-bold :text-lg :text-yellow-300]
          [:shadow-md :bg-red-700 :rounded-md]
          hover-colors
          color-transition short-duration
          {:on-click #(js/alert "surprise!")})
      "Tailwind Button"]
     [card :title "พระนางพญา เนื้อดำ พิมพ์เข่าโค้ง" :thumbnail "https://cf.lnwfile.com/2gwbkl.jpg" :highest-bid 4432452.2343]
     [card :title "พระนางพญา เนื้อดำ พิมพ์เข่าโค้ง" :thumbnail "https://png.pngtree.com/thumb_back/fh260/background/20200714/pngtree-modern-double-color-futuristic-neon-background-image_351866.jpg" :highest-bid nil]
     [:span (tw [:mt-4 :font-bold "text-red-500" :text-3xl])
      "this text should be bold red if everything worked"]
     [:h1
      "Hello from " @name]]))