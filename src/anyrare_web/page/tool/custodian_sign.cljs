(ns anyrare-web.page.tool.custodian-sign
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [subscribe dispatch]]
            [anyrare-web.page.tool.events :as events]
            [anyrare-web.page.tool.subs :as subs]
            [anyrare-web.subs :as app-subs]
            [anyrare-web.lib.utils :as utils]))

(defn tool-custodian-sign []
  (let [assets (subscribe [::subs/assets])
        i18n @(subscribe [::app-subs/i18n])]
    (fn []
      [:div {:class [:w-full]}
       [:h1 {:class [:mt-4 :text-center :text-xl :font-kanit :font-medium]}

        "Custodian unsign"]
       [:div {:class [:px-4 :grid :grid-cols-2 :md:px-36]}
        (for [asset @assets]
          [:div {:class [:py-4]}
           [:div {:class []}
            [:img {:src (utils/ipfs->url (get-in asset [:token-uri-data :image]))}]]
           [:div
            [:h2 {:class [:font-kanit :font-medium :text-lg]}
             (get-in asset [:token-uri-data :locales :name :th])
            ]]
           "A"])]])))

