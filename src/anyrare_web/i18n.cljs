(ns anyrare-web.i18n
  (:require
   [re-frame.core :refer [reg-event-db]]
   [anyrare-web.lib.localstorage :refer [get-lang]]))

(defn dicts []
  {:founder
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
   :current-highest-bid
   {:th "ราคาสูงสุดขณะนี้"
    :en "Current highest bid"}
   :total-bid
   {:th "จำนวนการเสนอราคา"
    :en "Total bid"}
   :auction-ends-in
   {:th "เหลือเวลาประมูล"
    :en "Auction ends in"}
   :auction-closed
   {:th "ปิดประมูล"
    :en "Closed"}
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
   {:th "ราคาที่ต้องการเสนอ"
    :en "Your bid"}
   :max-bid
   {:th "ราคาเสนอสูงสุด"
    :en "Max bid"}
   :you-are-about-to-place-a-bid-for
   {:th "คุณกำลังเสนอราคารายการประมูล"
    :en "You are about to place a bid for"}
   :your-bidding-balance
   {:th "ยอดเงินในกระเป๋าสตางค์ของคุณ"
    :en "Your balance"}
   :fee
   {:th "ค่าธรรมเนียม"
    :en "Fee"}
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
   :audit-title
   {:th "การตรวจสอบสินทรัพย์"
    :en "Audit"}
   :audit-details 
   {:th "รายละเอียดการตรวจสอบ"
    :en "Audit details"}
   :audit-date
   {:th "วันที่ตรวจสอบ"
    :en "Audit date"}
   :audit-certificate
   {:th "เอกสารการตรวจสอบ"
    :en "Certificate"}
   :custodian-title
   {:th "การเก็บรักษาสินทรัพย์"
    :en "Custodian details"}
   :custodian-date
   {:th "วันที่เริ่มต้นเก็บรักษา"
    :en "Contract date"}
   :custodian-contract
   {:th "สัญญาการเก็บรักษา"
    :en "Contract document"}
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
   :open-auction
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
    :en "Loading"}
   :ARA
   {:th "ARA"
    :en "ARA"}
   :by
   {:th "โดย"
    :en "by"}
   :tools
   {:th "เครื่องมือ"
    :en "Tools"}
   :meet-reserve-price
   {:th "ถึงราคาขั้นต่ำแล้ว"
    :en "Meet reserve price"}
   :not-meet-reserve-price
   {:th "ยังไม่ถึงราคาขั้นต่ำ"
    :en "Not meet reserve price"}
   :close-auction-timestamp
   {:th "เวลาปิดประมูล"
    :en "Close Auction"}
   :bid-auction
   {:th "ประมูล"
    :en "Bid"}})

(defn get-dicts-by-lang [lang] 
  (into {} (map (fn [[k v]] {k (v lang)}) (dicts))))
