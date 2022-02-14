(ns anyrare-web.lib.ipfs
  (:require [lambdaisland.fetch :as fetch]
            [kitchen-async.promise :as p]
            [anyrare-web.env :as env]
            [anyrare-web.lib.utils :as utils]))

(defn upload-file
  [form-data callback]
  (p/let
   [res (fetch/post
         (str env/PINATA_SERVER_URL "/pinning/pinFileToIPFS")
         {:headers {:pinata_api_key env/PINATA_API_KEY
                    :pinata_secret_api_key env/PINATA_API_SECRET}
          :content-type "multipart/form-data"
          :body form-data
          :redirect :follow})]
    (.log js/console (js->clj form-data))
    (prn "res" res)
    (callback (:body res))))

(defn upload-json
  [data callback]
  (p/let
   [res (fetch/post
         (str env/PINATA_SERVER_URL "/pinning/pinJSONToIPFS")
         {:headers {:pinata_api_key env/PINATA_API_KEY
                    :pinata_secret_api_key env/PINATA_API_SECRET
                    :content-type "multipart/form-data"}
          :content-type :json
          :body (utils/clj->json data)})]
    (callback (-> (:body res)
                  (as-> r {:ipfs-hash (goog.object/get r "IpfsHash")
                           :timestamp (goog.object/get r "Timestamp")})))))





