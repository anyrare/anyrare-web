(ns anyrare-web.asset.events
  (:require
   [re-frame.core :refer [reg-event-db reg-event-fx]]
   [anyrare-web.ethers :as ethers]
   [anyrare-web.events :as app-events]
   ["@splidejs/splide" :default Splide]
   [day8.re-frame.tracing :refer-macros [fn-traced]]))

(reg-event-db
 ::initialize-image-slider
 (fn-traced [_ _]
            (-> (.mount (Splide. "#image-slider" (js-obj "cover" true "heightRatio" 1.0)))
                (as-> _ nil))))

(reg-event-fx
 ::open-auction
 (fn [_ [_ params]]
   {:dispatch-fn [(ethers/nft-open-auction params #(dispatch [::app-events/save-data ::ethers-tx-callback %]))]}))

