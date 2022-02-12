(ns anyrare-web.ethers
  (:require
   [re-frame.core :refer [dispatch subscribe reg-event-fx]]
   [kitchen-async.promise :as p]
   ["ethers" :refer [ethers]]
   [lambdaisland.fetch :as fetch]
   [anyrare-web.abi :refer [contract-abi contract-address]]
   [anyrare-web.env :as env]
   [anyrare-web.lib.utils :refer [json->clj]]
   [anyrare-web.error :refer [log error-messages]]))

(def MAX_APPROVE_SPEND_LIMIT
  (.from (.-BigNumber ethers) "1000000000000000000000000000000"))

(def provider
  (try
    (new (.-JsonRpcProvider (.-providers ethers))
         (clj->js {:url env/CHAIN_URL})
         (clj->js {:chainId env/CHAIN_ID :name env/CHAIN_NAME}))
    (catch js/Error e
      (log (get-in error-messages [:ethers :failed-to-get-provider])))))

(def provider-metamask
  (try
    (new (.-Web3Provider (.-providers ethers)) (.-ethereum js/window))
    (catch js/Error e
      (log (get-in error-messages [:ethers :metamask-not-found])))))

;; (defn init-wallet-signer []
;;   (-> (p/let [_ (.send provider-metamask "eth_requestAccounts" [])
;;               signer (.getSigner provider-metamask)
;;               address (.getAddress signer)]
;;         (dispatch [::events/set-account-id address]))
;;       (p/catch*
;;        (fn [err]
;;          (log (get-in error-messages
;;                       [:ethers :failed-to-init-wallet-signer]) err)))))


(defn signer-address
  [_ callback]
  (p/let [_ (.send provider-metamask "eth_requestAccounts" [])
          signer (.getSigner provider-metamask)
          address (.getAddress signer)]
    (callback {:address address
               :signer signer})))

(def member-contract
  (new (.-Contract ethers)
       (:member contract-address)
       (clj->js (:member contract-abi))
       provider))

;; (defn private-key->public-key
;;   [private-key]
;;   (.log js/console private-key)
;;   (new (.Wallet ethers) private-key))

(defn number->BigNumber
  [number]
  (.from (.-BigNumber ethers) number))

(defn get-contract
  [address abi signer]
  (new (.-Contract ethers) address (clj->js abi) signer))


;; ARA

(defn check-ara-balance
  [params callback]
  (p/let [tx (.balanceOf (get-contract (:ara-token contract-address)
                                     (:ara-token contract-abi)
                                     provider)
                         (:address params))]
    (callback (js->clj
               {:address (:address params)
                :symbol "ARA"
                :token (:ara-token contract-address)
                :value tx}))))
  


;; Member


(defn set-member
  [params callback]
  (p/let [_ (.send provider-metamask "eth_requestAccounts" [])
          signer (.getSigner provider-metamask)
          address (.getAddress signer)
          tx (.setMember (get-contract (:member contract-address)
                                       (:member contract-abi)
                                       signer)
                         address
                         (:referral params)
                         (:username params)
                         (:thumbnail params))]
    (callback {:result (js->clj tx)
               :address address})))

(defn member-by-address
  [params callback]
  (p/let [tx (.members (get-contract (:member contract-address)
                                     (:member contract-abi)
                                     provider)
                       (:address params))]
    (callback (js->clj
               {:address (:address params)
                :referral (.. tx -referral)
                :username (.. tx -username)
                :thumbnail (.. tx -thumbnail)}))))

(defn member-by-username
  [params callback]
  (p/let [tx (.getAddressByUsername (get-contract (:member contract-address)
                                                  (:member contract-abi)
                                                  provider)
                                    (:username params))]
    (callback (js->clj tx))))


;; NFT


(defn nft-mint
  [params callback]
  (p/let [_ (.send provider-metamask "eth_requestAccounts" [])
          signer (.getSigner provider-metamask)
          address (.getAddress signer)
          tx (.mint (get-contract (:nft-factory contract-address)
                                  (:nft-factory contract-abi)
                                  signer)
                    (:founder-address params)
                    (:custodian-address params)
                    (:token-uri params)
                    (:max-weight params)
                    (:founder-weight params)
                    (:founder-redeem-weight params)
                    (:founder-general-fee params)
                    (:audit-fee params))]
    (callback {:result (js->clj tx)})))

(defn nft-custodian-sign
  [params callback]
  (p/let [_ (.send provider-metamask "eth_requestAccounts" [])
          signer (.getSigner provider-metamask)
          address (.getAddress signer)
          tx (.custodianSign (get-contract (:nft-factory contract-address)
                                           (:nft-factory contract-abi)
                                           signer)
                             (:token-id params)
                             (:custodian-weight params)
                             (:custodian-general-fee params)
                             (:custodian-redeem-weight params))]
    (callback {:result (js->clj tx)})))

(defn nft-pay-fee-and-claim-token
  [params callback]
  (p/let [_ (.send provider-metamask "eth_requestAccounts" [])
          signer (.getSigner provider-metamask)
          address (.getAddress signer)
          ;; Check spend limit
          approve-spend (.approve (get-contract (:ara-token contract-address)
                                                (:ara-token contract-abi)
                                                signer)
                                  (:nft-factory contract-address)
                                  MAX_APPROVE_SPEND_LIMIT)
          tx (.payFeeAndClaimToken (get-contract (:nft-factory contract-address)
                                                 (:nft-factory contract-abi)
                                                 signer)
                                   (:token-id params))]
    (callback (js->clj tx))))

(defn nft-current-token-id
  [callback]
  (p/let [tx (.getCurrentTokenId (get-contract (:nft-factory contract-address)
                                               (:nft-factory contract-abi)
                                               provider))]
    (callback {:result (js->clj tx)})))

(defn nft-by-id
  [params callback]
  (-> [(.nfts (get-contract (:nft-factory contract-address)
                            (:nft-factory contract-abi)
                            provider)
              (:token-id params))
       (.tokenURI (get-contract (:nft-factory contract-address)
                                (:nft-factory contract-abi)
                                provider)
                  (:token-id params))]
      (p/all)
      (p/then
       (fn [[tx token-uri]]
         (->
          [(fetch/get token-uri)
           (member-by-address {:address (.. tx -addr -founder)} (fn [x] x))
           (member-by-address {:address (.. tx -addr -auditor)} (fn [x] x))
           (member-by-address {:address (.. tx -addr -custodian)} (fn [x] x))
           (member-by-address {:address (.. tx -addr -owner)} (fn [x] x))
           (member-by-address {:address (.. tx -addr -owner)} (fn [x] x))
           (member-by-address {:address (.. tx -offer -bidder)} (fn [x] x))
           (if (= (.. tx -totalAuction) 0) nil
               (.getAuctionByAuctionId (get-contract (:nft-factory contract-address)
                                                     (:nft-factory contract-abi)
                                                     provider)
                                       (:token-id params)
                                       (- (.. tx -totalAuction) 1)))]
          (p/all)
          (p/then
           (fn
             [[token-uri-data founder auditor
               custodian auction-bidder owner offer-bidder auction]]
             (callback
              (js->clj
               {:token-id (.-tokenId tx)
                :token-uri token-uri
                :token-uri-data (json->clj (:body token-uri-data))
                :auction (if (nil? auction) nil
                             {:bidder-address (.. auction -bidder)
                              :owner owner
                              :open-auction-timestamp (.. auction -openAuctionTimestamp)
                              :close-auction-timestamp (.. auction -closeAuctionTimestamp)
                              :max-bid (.. auction -maxBid)
                              :max-weight (.. auction -maxWeight)
                              :meet-reserve-price (.. auction -meetReservePrice)
                              :next-bid-weight (.. auction -nextBidWeight)
                              :reserve-price (.. auction -reservePrice)
                              :starting-price (.. auction -startingPrice)
                              :total-bid (.. auction -totalBid)
                              :value (.. auction -value)})
                :founder founder
                :auditor auditor
                :custodian custodian
                :owner owner
                :status {:auction (.. tx -status -auction)
                         :buy-it-now (.. tx -status -buyItNow)
                         :claim (.. tx -status -claim)
                         :custodian-sign (.. tx -status -custodianSign)
                         :freeze (.. tx -status -freeze)
                         :lock-in-collection (.. tx -status -lockInCollection)
                         :offer (.. tx -status -offer)
                         :redeem (.. tx -status -redeem)}
                :fee {:max-weight (.. tx -fee -maxWeight)
                      :audit-fee (.. tx -fee -auditFee)
                      :custodian-general-fee (.. tx -fee -custodianGeneralFee)
                      :custodian-redeem-weight (.. tx -fee -custodianRedeemWeight)
                      :custodian-weight (.. tx -fee -custodianWeight)
                      :founder-general-fee (.. tx -fee -founderGeneralFee)
                      :founder-redeem-weight (.. tx -fee -founderRedeemWeight)
                      :founder-weight (.. tx -fee -founderWeight)
                      :mint-fee (.. tx -fee -mintFee)}
                :total-auction (.. tx -totalAuction)
                :buy-it-now {:owner owner
                             :value (.. tx -buyItNow -value)}
                :bid-id (.. tx -bidId)
                :offer {:bidder offer-bidder
                        :close-offer-timestamp (.. tx -offer -closeOfferTimestamp)
                        :open-offer-timestamp (.. tx -offer -openOfferTimestamp)
                        :owner owner
                        :value (.. tx -offer -value)}
                :redeem-timestamp (.. tx -offer -redeemTimestamp)})))))))))

(defn nft-open-auction
  [params callback]
  (p/let [_ (.send provider-metamask "eth_requestAccounts" [])
          signer (.getSigner provider-metamask)
          address (.getAddress signer)
          tx (.openAuction (get-contract (:nft-factory contract-address)
                                         (:nft-factory contract-abi)
                                         signer)
                           (:token-id params)
                           (:close-auction-period-second params)
                           (:starting-price params)
                           (:reserve-price params)
                           (:max-weight params)
                           (:next-bid-weight params))]
    (callback {:result (js->clj tx)})))

(defn nft-auction-by-auction-id
  [params callback]
  (p/let [tx (.getAuctionByAuctionId (get-contract (:nft-factory contract-address)
                                                   (:nft-factory contract-abi)
                                                   provider)
                                     (:token-id params)
                                     (:auction-id params))
          owner (member-by-address {:address (.. tx -owner)} (fn [x] x))
          bidder (member-by-address {:address (.. tx -bidder)} (fn [x] x))]
    (callback (js->clj {:bidder bidder
                        :owner owner
                        :open-auction-timestamp (.. tx -openAuctionTimestamp)
                        :close-auction-timestamp (.. tx -closeAuctionTimestamp)
                        :max-bid (.. tx -maxBid)
                        :max-weight (.. tx -maxWeight)
                        :meet-reserve-price (.. tx -meetReservePrice)
                        :next-bid-weight (.. tx -nextBidWeight)
                        :reserve-price (.. tx -reservePrice)
                        :starting-price (.. tx -startingPrice)
                        :total-bid (.. tx -totalBid)
                        :value (.. tx -value)}))))

(defn nft-get-auction-bid
  [params callback]
  (p/let [tx (.getAuctionBid (get-contract (:nft-factory contract-address)
                                           (:nft-factory contract-abi)
                                           provider)
                             (:token-id params)
                             (:bid-id params))
          bidder (member-by-address {:address (.. tx -bidder)} (fn [x] x))]
    (callback (js->clj {:auction-id (.. tx -auctionId)
                        :token-id (:token-id params)
                        :bid-id (:bid-id params)
                        :auto-rebid (.. tx -autoRebid)
                        :bidder bidder
                        :meet-reserve-price (.. tx -meetReservePrice)
                        :timestamp (.. tx -timestamp)
                        :value (.. tx -value)}))))

(defn nft-get-auction-bids
  [params callback]
  (->
   (for [bid-id (range (:current-bid-id params))
         :when (>= bid-id (- (:current-bid-id params) (:total-bid params)))]
     (nft-get-auction-bid {:token-id (:token-id params) :bid-id bid-id}
                          (fn [x] x)))
   (vec)
   (p/all)
   (p/then (fn [x] (callback (js->clj x))))))

(defn nft-bid-auction
  [params callback]
  (p/let [_ (.send provider-metamask "eth_requestAccounts" [])
          signer (.getSigner provider-metamask)
          address (.getAddress signer)
          ;; Check spend limit
          approve-spend (.approve (get-contract (:ara-token contract-address)
                                                (:ara-token contract-abi)
                                                signer)
                                  (:nft-factory contract-address)
                                  MAX_APPROVE_SPEND_LIMIT)
          tx (.bidAuction (get-contract (:nft-factory contract-address)
                                        (:nft-factory contract-abi)
                                        signer)
                          (:token-id params)
                          (:bid-value params)
                          (:max-bid params))]
    (callback {:result (js->clj tx)})))

