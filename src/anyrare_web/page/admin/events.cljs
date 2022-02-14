(ns anyrare-web.page.admin.events
  (:require [re-frame.core :refer [reg-event-fx reg-event-db]]
            [superstructor.re-frame.fetch-fx]
            [kitchen-async.promise :as p]
            [anyrare-web.events :as app-events]
            [anyrare-web.env :as env]
            [anyrare-web.lib.utils :as utils]
            [anyrare-web.abi :as abi]
            [anyrare-web.ethers :as ethers]))

(reg-event-db
 ::success-upload-file
 (fn [db [_ key data result]]
   (assoc db key (-> (:body result)
                     (js/JSON.parse)
                     (js->clj)
                     (as-> r {:data data
                              :ipfs-hash (get-in r ["IpfsHash"])
                              :timestamp (get-in r ["Timestamp"])})))))

(reg-event-fx
 ::upload-file
 (fn [_ [_ body image-id]]
   {:fetch {:method :post
            :url (str env/PINATA_SERVER_URL "/pinning/pinFileToIPFS")
            :headers {"pinata_api_key" env/PINATA_API_KEY
                      "pinata_secret_api_key" env/PINATA_API_SECRET}
            :request-content-type "multipart/form-data"
            :body body
            :redirect :follow
            :mode :cors
            :on-success [::success-upload-file image-id nil]
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
            :on-success [::success-upload-file :file-json data]
            :on-failure [::app-events/save-data :upload-file]}}))

(reg-event-fx
 ;; ::upload-meta-data
 ::mint-nft-flow
 (fn [{:keys [db]} [_ {:keys [values]}]]
   {:async-flow
    {:first-dispatch [::app-events/ethers ethers/nft-current-token-id
                      :current-token-id nil]
     :rules [{:when :seen? :events (fn [[e id]]
                                     (and (= ::app-events/save-data e)
                                          (= :current-token-id id)))
              :dispatch-fn
              (fn [[_ _ result]]
                (let [images (-> (for [i (range 5)]
                                   (get-in db [(keyword (str "image-id-" i))]))
                                 (as-> r (filter #(some? %) r))
                                 (as-> r (map (fn [x] {:url (str "ipfs://" (:ipfs-hash x))
                                                       :type "IMAGE"}) r))
                                 (vector))
                      token-id (str (:token-id result))
                      data {:default-lang "th"
                            :name (get-in values ["name-th"])
                            :description (get-in values ["description-th"])
                            :external_url nil
                            :image (images 0)
                            :attachments images
                            :token_address (:nft-factory abi/contract-address)
                            :token_id token-id
                            :founder_address (get-in values ["founder-address"])
                            :auditor_address (get-in db [:signer :address])
                            :custodian_address (get-in values ["custodian-address"])
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
                            {:founder_weight (get-in values ["founder-fee"])
                             :founder_redeem_weight (get-in values ["founder-redeem-fee"])
                             :founder_general_fee (get-in values ["founder-general-fee"])
                             :audit_fee (get-in values ["audit-fee"])
                             :max_weight 1000000}}]
                  [[::upload-json data]]))}
             {:when :seen? :events ::success-upload-file
              :dispatch-fn
              (fn [[_ _ result res]]
                (let [ipfs-hash
                      (-> (:body result)
                          (js/JSON.parse)
                          (js->clj)
                          (as-> r  (get-in r ["IpfsHash"])))
                      token-uri (str "ipfs://" ipfs-hash)
                      data {:founder-address (get-in result [:founder_address])
                            :custodian-address (get-in result [:custodian_address])
                            :auditor-address (get-in db [:signer :address])
                            :token-uri token-uri
                            :max-weight 1000000
                            :founder-weight (get-in result [:fee :founder_weight])
                            :founder-redeem-weight (get-in result [:fee :founder_redeem_weight])
                            :founder-general-fee (get-in result [:fee :founder_general_fee])
                            :audit-fee (get-in result [:fee :audit_fee])}]
                  [[::app-events/ethers ethers/nft-mint :ethers-tx-callback data]]))}]}}))



