(ns anyrare-web.views
  (:require
   [re-frame.core :as re-frame]
   [garden.core :refer [css]]
   [anyrare-web.subs :as subs]
   [anyrare-web.events :as events]))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        test (re-frame/subscribe [::subs/test])]
    [:div
     [:p {:style {:font-size "32px" :color "#930293" :text-decoration "underline"}} 
      "Test value: " @test]
     [:p "Name value: " @name]
     [:button {:on-click #(re-frame/dispatch [::events/name-change "Name Changed!"])} "Change name"]
     [:h1
      "Hello from " @name]]))
