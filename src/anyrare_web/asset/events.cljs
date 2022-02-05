(ns anyrare-web.asset.events
  (:require
   [re-frame.core :refer [reg-event-db reg-event-fx]]
   ["@splidejs/splide" :default Splide]
   [day8.re-frame.tracing :refer-macros [fn-traced]]))

(reg-event-db
 ::initialize-image-slider
 (fn-traced [_ _]
            (-> (.mount (Splide. "#image-slider" (js-obj "cover" true "heightRatio" 1.0)))
                (as-> _ nil))))
