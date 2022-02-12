(ns anyrare-web.asset.events
  (:require
   [re-frame.core :refer [reg-event-db reg-event-fx]]
   [anyrare-web.ethers :as ethers]
   [anyrare-web.events :as app-events]
   [kitchen-async.promise :as p]
   ["@splidejs/splide" :default Splide]))

(reg-event-db
 ::initialize-image-slider
 (fn [_ _]
   (-> (.mount (Splide. "#image-slider"
                        (js-obj "cover" true "heightRatio" 1.0)))
       (as-> _ nil))))

(reg-event-fx
 ::open-auction
 (fn [_ [_ params]]
   {:dispatch-fn [(ethers/nft-open-auction
                   params
                   #(dispatch [::app-events/save-data :ethers-tx-callback %]))]}))

(reg-event-fx
 ::bid-auction
 (fn [_ [_ params]]
   {:dispatch-fn [(ethers/nft-bid-auction
                   params
                   #(dispatch [::app-events/save-data :ethers-tx-callback %]))]}))

(reg-event-fx
 ::open-auction-bid-panel
 (fn [_ [_ toggle-popup-panel content-popup-panel]]
   {:async-flow
    {:first-dispatch [::app-events/ethers ethers/signer-address :signer nil]
     :rules [{:when :seen? :events ::app-events/save-data
              :dispatch-fn
              (fn [[_ _ params]]
                [[::app-events/ethers ethers/check-ara-balance :balance params]
                 [::app-events/result (reset! content-popup-panel :bid-auction)]
                 [::app-events/result (reset! toggle-popup-panel true)]])}]}}))

