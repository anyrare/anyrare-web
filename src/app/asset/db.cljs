(ns app.asset.db)

(def asset-db
  {:address "0x9930bbbacff0329022"
   :status "LIVE_AUCTION"
   :title "พระปิดตาหลวงพ่อปานวัดเครือวัลย์ ปีพ.ศ. 2515"
   :description "+บัตรพระแท้+พระปิดตาหลวงพ่อปาน วัดเครือวัลย์ พิมพ์พุทโธ<br>หลังเรียบ เนื้อผงลงรักปิดทอง จ.ชลบุรี<br>- พระปิดตาหลวงพ่อปาน วัดเครือวัลย์ พิมพ์พุทโธหลังเรียบ เนื้อ<br>ผงลงรักปิดทอง จ.ชลบุรี<br>- ผสมผงเก่าอิทธิเจ \"หลวงพ่อแก้ว วัดเครือวัลย์\""
   :attachments
   [{:type "IMAGE" :url "http://g-pra.com/Auctions1/get_auc1_img.php?data=front&id=24631469&date=2021-10-26"}
    {:type "IMAGE" :url "http://g-pra.com/Auctions1/get_auc1_img.php?data=back&id=24631469&date=2021-10-26"}
    {:type "IMAGE" :url "http://g-pra.com/Auctions3/get_auc3_img.php?id=16381841"}]
   :founder
   {:name "panasun"
    :thumbnail "https://i.pinimg.com/originals/d3/8a/dd/d38addafcff4bee00bd99530ebc9efc5.jpg"
    :address "0x13942305bbbc0329423aaaa"
    :fee 10000
    :fee-denominator 100000}
   :owner
   {:name "panasun"
    :thumbnail "https://i.pinimg.com/originals/d3/8a/dd/d38addafcff4bee00bd99530ebc9efc5.jpg"
    :address "0x13942305bbbc0329423aaaa"}
   :auditor
   {:name "GPraAuditor"
    :full-name "บริษัท การันตีพระ จำกัด"
    :thumbnail "https://lh3.googleusercontent.com/proxy/BL69WMBB3PHl7ZuRMgJLO1army-fAY0Kd648xKKs1rYQSrXyKG5apM5G3dPjlyPwHxbc91QFJ8xcp74AZUALnw"
    :address "0x1349baaaa0392032bcf99099"
    :auditor-report
    {:th "พระปิดตาหลวงพ่อปาน วัดเครือวัลย์ พิมพ์พุทโธหลังเรียบ เนื้อผงลงรักปิดทอง จ.ชลบุรี"
     :en "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam feugiat nec odio ac facilisis. In malesuada, quam in maximus auctor, neque lacus cursus lorem, et tempor diam ante at dolor. Morbi nec erat sed massa imperdiet blandit"}
    :audit-date 169303922
    :audit-address "0x93023ca39202223455"}
   :custodian
   {:name "GPraCustodian"
    :full-name "บริษัท การันตีพระ รักษาสินทรัพย์ จำกัด"
    :thumbnail "https://lh3.googleusercontent.com/proxy/6rtejUaqyw_SaypbJ6mm42zl5QPm7IKdiMb5dgzt8usITRXW-WrrASJVZQ8KYRsVpI0aH7LtUtKEZYmFXQq68IeNXRHOh-2nIXQMlA"
    :address "0x9930cafb9320239caff03666"
    :fee 25000
    :fee-denominator 100000
    :contract-date 169303922
    :contract-address "0x3484820bcaf3234234"}
   :liked 256
   :auction
   {:highest-price 12023569402343
    :highest-price-denominator 100000000000000000
    :start-date 169304392
    :end-date 1702932302
    :highest-bid
    {:name "JammyJam"
     :address "0x93240234acccc"
     :total-bid 143}
    :histories
    [{:name "JammyJam"
      :address "0x93240234acccc"
      :thumbnail "https://www.brighttv.co.th/wp-content/uploads/2021/07/89f81a8c5ca14803a73345e8397c21b7.jpeg"
      :total-bid 143
      :bid-price 1203393234
      :bid-price-denominator 100000000000000000
      :date 16049234
      :meet-minimum-price true}
     {:name "BinBangkruai"
      :address "0x234234234"
      :thumbnail "https://i.pinimg.com/originals/53/25/cd/5325cdb4f50eb759589aa9d7d25de4df.jpg"
      :total-bid 23
      :bid-price 12343633
      :bid-price-denominator 100000000000000000
      :date 16043234
      :meet-minimum-price true}]}
   :histories []})