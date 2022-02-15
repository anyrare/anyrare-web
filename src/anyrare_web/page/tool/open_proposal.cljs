(ns anyrare-web.page.tool.open-proposal
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [subscribe dispatch]]
            [anyrare-web.page.tool.events :as events]
            [anyrare-web.page.tool.subs :as subs]
            [anyrare-web.subs :as app-subs]
            [anyrare-web.lib.utils :as utils]))


(defn tool-open-proposal []
  (let [image (reagent/atom nil)
        i18n @(subscribe [::app-subs/i18n])]
    (fn []
      [:div {:class [:w-full]}
       [:h1 {:class [:mt-4 :text-center :text-xl :font-kanit :font-medium]}
        "Open proposal"]])))

