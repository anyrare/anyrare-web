(ns app.config.i18n)

(def dicts
  [:founder
   {:th "ผู้ค้นพบสินทรัพย์"
    :en "Founder"}
   :owner
   {:th "เจ้าของปัจจุบัน"
    :en "Owner"}
   :auditor
   {:th "ผู้ตรวจสอบสินทรัพย์"
    :en "Auditor"}
   :custodian
   {:th "ผู้เก็บรักษาสินทรัพย์"
    :en "Custodian"}
   :highest-bid
   {:th "ราคาสูงสุด"
    :en "Higest bid"}
   :highest-bid-by
   {:th "ผู้ให้ราคาสูงสุด"
    :en "Higest bid by"}
   :auction-ends-in
   {:th "เหลือเวลาประมูล"
    :en "Auction ends in"}
   :place-a-bid
   {:th "เสนอราคา"
    :en "Place a bid"}
   :bids
   {:th "เสนอราคา"
    :en "Bids"}
   :minimum-bid 
   {:th "ราคาขั้นต่ำ"
    :en "Minimum bid"}
   :details 
   {:th "รายละเอียด"
    :en "Details"}
   :history 
   {:th "ประวัติ"
    :en "History"}
   :withdraw 
   {:th "ถอน"
    :en "Withdraw"}
   :buy 
   {:th "ซื้อ"
    :en "Buy"}
   :sell 
   {:th "ขาย"
    :en "Sell"}
   :your-bid 
   {:th "ราคาที่คุณเสนอ"
    :en "Your bid"}
   :you-are-about-to-place-a-bid-for
   {:th "คุณกำลังเสนอราคารายการประมูล"
    :en "You are about to place a bid for"}
   :your-bidding-balance
   {:th "ยอดเงินในกระเป๋าสตางค์ของคุณ"
    :en "Your balance"}
   :you-will-pay
   {:th "ยอดชำระ"
    :en "You will pay"}
   :not-enough-funds
   {:th "ยอดเงินไม่พอจ่าย"
    :en "Not enough funds"}
   :must-be-at-least 
   {:th "ราคาขั้นต่ำ"
    :en "Must be at least"}
   :confirm-bid 
   {:th "ยืนยันการเสนอราคา"
    :en "Confirm bid"}
   :confirm-buy
   {:th "ยืนยันการซื้อ"
    :en "Confirm buy"}
   :buy-more-ara 
   {:th "ต้องการซื้อ ARA เพิ่ม ?"
    :en "Want to buy more ARA ?"}
   :asset-id 
   {:th "รหัสสินทรัพย์"
    :en "Asset Id"}
   :audit-details 
   {:th "รายละเอียดการตรวจสอบ"
    :en "Audit details"}
   :royalty-fee 
   {:th "ค่าสิทธิ"
    :en "Royalty fee"}
   :collection
   {:th "ชุดสะสม"
    :en "Collection"}
   :collector
   {:th "นักสะสม"
    :en "Collector"}
   :view-collection
   {:th "ดูรายละเอียดชุดสะสม"
    :en "View collection"}
   :in-collection
   {:th "สินทรัพย์ของชุดสะสม"
    :en "In collection"}
   :set-auction
   {:th "จัดประมูล"
    :en "Auction"}
   :set-price
   {:th "ตั้งราคาขาย"
    :en "Set price"}
   :create-collection
   {:th "สร้างชุดสะสม"
    :en "Create collection"}
   :withdraw-asset
   {:th "ถอนสินทรัพย์"
    :en "Withdraw asset"}
   :stop-selling
   {:th "ปิดการขาย"
    :en "Stop selling"}
   :day
   {:th "วัน"
    :en "Day"}
   :hour
   {:th "ชั่วโมง"
    :en "Hour"}
   :minute
   {:th "นาที"
    :en "Minute"}
   :second
   {:th "วินาที"
    :en "Second"}
   :loading
   {:th "กำลังโหลด"
    :en "Loading"}])

(defn get-lang []
  (or (.getItem (.-localStorage js/window) "lang") "th"))