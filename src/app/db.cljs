(ns app.db)

(def asset-page
  {:address "0x139a0bfa0323"
   :status "LIVE_AUCTION"
   :title "พระปิดตาหลวงพ่อปานวัดเครือวัลย์ ปีพ.ศ. 2515"
   :description "บัตรพระแท้+พระปิดตาหลวงพ่อปาน วัดเครือวัลย์ พิมพ์พุทโธหลังเรียบ เนื้อผงลงรักปิดทอง จ.ชลบุรี พระปิดตาหลวงพ่อปาน วัดเครือวัลย์ พิมพ์พุทโธหลังเรียบ เนื้อผงลงรักปิดทอง จ.ชลบุรี ผสมผงเก่าอิทธิเจ \"หลวงพ่อแก้ว วัดเครือวัลย์\""
   :attachments
   [{:type "IMAGE" :url "http://g-pra.com/Auctions1/get_auc1_img.php?data=front&id=24721270&date=2021-11-14"}
    {:type "IMAGE" :url "http://g-pra.com/Auctions1/get_auc1_img.php?data=back&id=24721270&date=2021-11-14"}
    {:type "IMAGE" :url "http://g-pra.com/Auctions1/get_auc1_img.php?data=third&id=24721270&date=2021-11-14"}
    {:type "IMAGE" :url "http://g-pra.com/Auctions3/get_auc3_img.php?id=16443819"}]
   :auction
   {:highest-price 12978002356940234334252
    :highest-price-denominator 100000000000000000
    :start-date 169304392
    :end-date 1702932302
    :total-bid 43
    :highest-bidder
    {:name "JammyJam"
     :thumbnail "https://www.brighttv.co.th/wp-content/uploads/2021/07/89f81a8c5ca14803a73345e8397c21b7.jpeg"
     :address "0x9324029323432acccc"
     :total-bid 143}
    :bids
    [{:bid-id "0x9203231121"
      :price 12234
      :price-denominator 1000
      :name "JammyJam"
      :thumbnail "https://www.brighttv.co.th/wp-content/uploads/2021/07/89f81a8c5ca14803a73345e8397c21b7.jpeg"
      :address "0x92342345bbbbbb"
      :total-bid 143
      :date 15482934523}
     {:bid-id "0x123"
      :price 10392
      :price-denominator 1000
      :name "Christy Chung"
      :thumbnail "https://theindependent.sg/wp-content/uploads/2021/02/chung.png"
      :address "0x832342345bbbbbb"
      :total-bid 0
      :date 14482934523}
     {:bid-id "0x12393"
      :price 9234
      :price-denominator 1000
      :name "BinBangkruai"
      :thumbnail "https://www.newtv.co.th/images/content/ct_20190908113755446.jpg"
      :address "0x92342345bbbbbb"
      :total-bid 21
      :date 13402934523}]}
   :founder
   {:name "panasun"
    :thumbnail "https://i.pinimg.com/originals/d3/8a/dd/d38addafcff4bee00bd99530ebc9efc5.jpg"
    :address "0x13942305bbbc0329423aaaa"
    :fee 10000
    :fee-denominator 100000}
   :owner
   {:name "lisaBP"
    :thumbnail "https://s.isanook.com/sr/0/rp/rc/w0h0/ya0xacm1w0/aHR0cDovL2pvb3gtY21zLWltYWdlLTEyNTEzMTYxNjEuZmlsZS5teXFjbG91ZC5jb20vMjAyMS8wOS8yNy9lMTY5ZjVlNy05NmNkLTQ1NjEtODk3Zi1mNTczOGFiOWNhOGMuanBnLzEwMDA=.jpg"
    :address "0x13942305bbbc0329423aaaa"}
   :auditor
   {:name "GPraAuditor"
    :full-name "บริษัท การันตีพระ จำกัด"
    :thumbnail "http://www2.g-pra.com/information/showimage_front.php?table=data&No=1211"
    :address "0x1349baaaa0392032bcf99099"
    :auditor-report
    {:th "พระปิดตาหลวงพ่อปาน วัดเครือวัลย์ พิมพ์พุทโธหลังเรียบ เนื้อผงลงรักปิดทอง จ.ชลบุรี"
     :en "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam feugiat nec odio ac facilisis. In malesuada, quam in maximus auctor, neque lacus cursus lorem, et tempor diam ante at dolor. Morbi nec erat sed massa imperdiet blandit"}
    :audit-date 1537215643
    :audit-address "0x93023ca39202223455"}
   :custodian
   {:name "GPraCustodian"
    :full-name "บริษัท การันตีพระ รักษาสินทรัพย์ จำกัด"
    :thumbnail "http://www2.g-pra.com/information/showimage_back.php?table=data&No=1211"
    :address "0x9930cafb9320239caff03666"
    :fee 25000
    :fee-denominator 100000
    :contract-date 1437215643
    :contract-address "0x3484820bcaf3234234"}})

(def app-db
  {:active-page :home
   :asset-page asset-page})