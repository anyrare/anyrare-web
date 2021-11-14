(ns app.collection.db)

(def collection-db
  {:address "0x123"
   :title  "กรุพระปิดตาสายชลบุรี ของเก่าหายาก"
   :description "คอลเลคชั่นพระปิดตา สายชลบุรี ยุคปีพ.ศ. 2515 - 2530 สวยๆ หายาก เก็บสะสมไว้ราคาขึ้นแน่นอน คัดสรรมาให้คุณลงทุน"
   :symbol "CECH"
   :stock-price 23542
   :current-supply 130395234
   :your-shares 564234
   :market-cap 1203923423
   :volume-7days 13432423
   :volume-30days 2304234234
   :total-shareholders 432
   :total-assets 123
   :total-liked 342
   :collector
   {:address "0x123"
    :thumbnail "https://image.com/1.jpg"
    :username "panasun"
    :royalty-fee 0.25}
   :asset
   [{:address "0x123"
     :thumbnail "https://image.com/1.jpg"
     :title "พระปิดตาหลวงปู่เฮี้ยง วัดพนัญเชิง"
     :total-liked 256}]
   :shareholders
   [{:address "0x042"
     :thumbnail "https://image.com/1.jpg"
     :total-shares 1352
     :weight 0.3246}]
   :open-auction
   {:target-price 15300.1234
    :your-price 18309.234
    :your-vote-shares 12343.23
    :your-novote-shares 13343.23
    :total-vote-shares 23423423443
    :status "PENDING"}
   })