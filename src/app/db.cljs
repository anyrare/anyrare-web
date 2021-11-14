(ns app.db)

(def default-db
  {:active-page :home
   :name "re-frame"
   :test "Query Jam"})

(def public-account-profile
  {:address "0x123"
   :username "panasun"
   :thumbnail "https://image.com/1.jpg"})

(def profile
  {:total-asset 123
   :collection-value 134324
   :total-won-auction 590
   :assets 
   [{:address "0x123"
     :thumbnail "https://image.com/1.jpg"
     :title "พระนางพญา หลวงปู่โต๊ะ"
     :total-liked 256}]
   :collections 
   [{:address "0x123"
     :title "หลวงปู่ทวดพิมพ์นิยม"
     :thumbnail "https://image.com/1.jpg"
     :total-shares 1234343
     :value 3923423
     :weight 0.6846}]
   :royalties 
   [{:founders []
     :collectors []
     :referrals []}]
   :auctions []})

(def auction
  {:opens[]
   :closes[]
   :wons []
   :favorites []})

(def app-db
  {:local-storage
   {:account-id "0x12345"
    :login-method "METAMASK"
    :jwt-token "EA23242340arst"
    :lang "th"
    :local-currency "thb"}
   :profile
   {:account-id "0x12345"
    :thumbnail "https://i.pinimg.com/originals/53/25/cd/5325cdb4f50eb759589aa9d7d25de4df.jpg"
    :username "panasun"
    :email  "panasun@i17.co"
    :biography "นักสะสมพระมืออาชีพ"
    :referral-id "0x12345"
    :lang "th"
    :local-currency "thb"}
   :home 
   {:features 
    [{:asset-id "0x124"
      :thumbnail "https://image.com/1.jpg"
      :title "พระปิดตาหลวงพ่อปาน"
      :subtitle "วัดเครือวัลย์ ปีพ.ศ.2515"}]
    :sub-features ["0x1", "0x2", "0x3"]
    :top-collections ["0x1", "0x2", "0x3"]
    :top-auctions ["0x1", "0x2", "0x3"]
    :assets ["0x1", "0x2", "0x3"]}
   :asset {}
   :asset-db []
   :collection {}
   :mint {}
   :create-collection {}
   :auction {}
   :withdraw-asset {}
   :exchange {}
   :proposal {}
   :explorer {}
   :currency 
   {:ara-usd 13.3253
    :ara-thb 304.2392}
   })