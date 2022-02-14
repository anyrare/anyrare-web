(ns anyrare-web.page.admin.events
  (:require [re-frame.core :refer [reg-event-fx reg-event-db]]
            [superstructor.re-frame.fetch-fx]
            [anyrare-web.events :as app-events]
            [anyrare-web.env :as env]
            [anyrare-web.lib.utils :as utils]
            [anyrare-web.abi :as abi]))

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

(reg-event-fx
 ::mint-nft
 (fn [_ [_ {:keys [values]}]]
   (prn (get-in values ["ingredient"]))
   (let [data {:default-lang "th"
               :name (get-in values ["name-th"])
               :description (get-in values ["description-th"])
               :external_url nil
               :image nil
               :token_address abi/nft-factory
               :token_id nil
               :founder (get-in values ["founder-address"])
               :auditor (get-in values ["auditor"])
               :custodian (get-in values ["custodian"])
               :attributes
               [{:trait_type "created_date"
                 :value (get-in values ["created-date"])}
                {:trait_type "created-by"
                 :value (get-in values ["created-by"])}
                {:trait_type "origin"
                 :value (get-in values ["origin"])}
                {:trait_type "edition"
                 :value (get-in values ["edition"])}
                {:trait_type "material"
                 :value (get-in values ["material"])}
                {:trait_type "ingredient"
                 :value (get-in values ["ingredient"])}
                {:trait_type "condition"
                 :value (get-in values ["condition"])}
                {:trait_type "width"
                 :value (get-in values ["width"])}
                {:trait_type "length"
                 :value (get-in values ["length"])}
                {:trait_type "height"
                 :value (get-in values ["height"])}
                {:trait_type "weight"
                 :value (get-in values ["weight"])}]
               :fee
               {:founder_fee (get-in values ["founder-fee"])
                :custodian_fee (get-in values ["custodian-fee"])
                :founder_redeem_fee (get-in values ["founder-redeem-fee"])
                :custodian_redeem_fee (get-in values ["custodian-redeem-fee"])
                :founder_general_fee (get-in values ["founder_general_fee"])
                :custodian_general_fee (get-in values ["custodian_general_fee"])}}]
     (prn data))))
