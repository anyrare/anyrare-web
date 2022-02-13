(ns anyrare-web.page.admin.events
  (:require [re-frame.core :refer [reg-event-fx reg-event-db]]
            [superstructor.re-frame.fetch-fx]
            [anyrare-web.events :as app-events]
            [anyrare-web.env :as env]
            [anyrare-web.lib.utils :as utils]))

(reg-event-db
 ::success-upload-file
 (fn [db [_ key data]]
   (assoc db key (-> (:body data)
                     (js/JSON.parse)
                     (js->clj)
                     (as-> r {:ipfs-hash (get-in r ["IpfsHash"])
                              :timestamp (get-in r ["Timestamp"])})))))

(reg-event-fx
 ::upload-file
 (fn [_ [_ body]]
   {:fetch {:method :post
            :url (str env/PINATA_SERVER_URL "/pinning/pinFileToIPFS")
            :headers {"pinata_api_key" env/PINATA_API_KEY
                      "pinata_secret_api_key" env/PINATA_API_SECRET}
            :request-content-type "multipart/form-data"
            :body body
            :redirect :follow
            :mode :cors
            :on-success [::success-upload-file :file]
            :on-failure [::app-events/save-data :upload-file]}}))

(reg-event-fx
 ::upload-json
 (fn [_ [_ data]]
   {:fetch {:method :post
            :url (str env/PINATA_SERVER_URL "/pinning/pinJSONToIPFS")
            :headers {"pinata_api_key" env/PINATA_API_KEY
                      "pinata_secret_api_key" env/PINATA_API_SECRET}
            :request-content-type :json
            :body (clj->js data)
            :mode :cors
            :on-success [::success-upload-file :file-json]
            :on-failure [::app-events/save-data :upload-file]}}))

