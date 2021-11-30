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
    :end-date 1702932302}})

(def app-db
  {:active-page :home
   :asset-page asset-page})