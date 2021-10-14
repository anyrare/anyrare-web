(ns anyrare-web.views
  (:require
   [re-frame.core :as re-frame]
   [herb.core :refer [<class]]
   [tailwind-hiccup.core :refer [tw]]
   [anyrare-web.subs :as subs]
   [anyrare-web.events :as events]))

(defn background [color] {:background color :padding "10px 10px 10px 10px" :color "#FFFFFF"} )

(def color-transition ["transition-colors" "ease-in-out"])
(def short-duration ["duration-300"])
(def hover-colors ["hover:text-white" "hover:bg-red-500"])

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        test (re-frame/subscribe [::subs/test])]
    [:div
     [:p {:style {:font-size "32px" :color "#930293" :text-decoration "underline"}} 
      "Test value: " @test]
     [:p {:class (<class background "red")} "Name value: " @name]
     [:button {:on-click #(re-frame/dispatch [::events/name-change "Name Changed!"])} "Change name"]
     [:button
      (tw ["mx-3" "my-4" "py-4" "font-bold" "text-lg" "text-yellow-300"]
          ["shadow-md" "bg-red-700" "rounded-md"]
          hover-colors
          color-transition short-duration
          {:on-click #(js/alert "surprise!")}) 
      "Tailwind Button"]
     [:span (tw [:mt-4 :font-bold "text-red-500" :text-3xl])
      "this text should be bold red if everything worked"]
     [:h1
      "Hello from " @name]]))
