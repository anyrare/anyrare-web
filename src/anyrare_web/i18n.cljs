(ns anyrare-web.i18n
  (:require [re-frame.core :refer [reg-event-db]]
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
   :your-balance
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
   :you-are-about-to-open-auction
   {:th "คุณกำลังจัดประมูล"
    :en "You are about to open an auction for"}
   :starting-price
   {:th "ราคาเริ่มต้น"
    :en "Starting price"}
   :reserve-price
   {:th "ราคาสงวนขั้นต่ำ"
    :en "Reserve price"}
   :auction-duration
   {:th "ระยะเวลาเปิดประมูล"
    :en "Auction duration"}
   :day-1
   {:th "1 วัน"
    :en "1 Day"}
   :day-3
   {:th "3 วัน"
    :en "3 Days"}
   :day-5
   {:th "5 วัน"
    :en "5 Days"}
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
    :en "Bid"}
   :open-auction-cta
   {:th "เปิดประมูล"
    :en "Open auction"}
   :connect-to-wallet
   {:th "เชื่อมต่อบัญชีกระเป๋าสตางค์"
    :en "Connect wallet"}
   :you-are-not-connect-to-wallet
   {:th "คุณยังไม่ได้เชื่อมต่อบัญชีกระเป๋าสตางค์ กรุณาเลือกบัญชีที่ต้องการ"
    :en "You are not connect to wallet. Please select your wallet provider."}
   :wallet-address
   {:th "รหัสกระเป๋า"
    :en "Wallet address"}
   :mint-asset-name
   {:th "ชื่อสินทรัพย์"
    :en "Name"}
   :mint-asset-description
   {:th "คำอธิบาย"
    :en "Description"}
   :mint-asset-address
   {:th "รหัสกระเป๋า"
    :en "Wallet address"}
   :founder-fee
   {:th "ค่าสิทธิผู้ค้นพบสินทรัพย์"
    :en "Founder fee"}
   :custodian-fee
   {:th "ค่าธรรมเนียมผู้ดูแลสินทรัพย์"
    :en "Custodian fee"}
   :founder-redeem-fee
   {:th "ค่าสิทธิผู้ค้นพบสินทรัพย์ถ้ามีการถอน"
    :en "Founder redeem fee"}
   :custodian-redeem-fee
   {:th "ค่าธรรมเนียมผู้ดูแลสินทรัพย์ถ้ามีการถอน"
    :en "Custodian redeem fee"}
   :founder-general-fee
   {:th "ค่าสิทธิผู้ค้นพบสินทรัพย์ทั่วไป"
    :en "Founder general fee"}
   :custodian-general-fee
   {:th "ค่าธรรมเนียมผู้ดูแลสินทรัพย์ทั่วไป"
    :en "Custodian general fee"}
   :attribute
   {:th "คุณลักษณะ"
    :en "Attribute"}
   :created-date
   {:th "ปีที่สร้าง"
    :en "Created date"}
   :created-by
   {:th "ผู้สร้าง"
    :en "Created by"}
   :origin
   {:th "แหล่งกำเนิด"
    :en "Origin"}
   :model
   {:th "แบบ"
    :en "Model"}
   :edition
   {:th "รุ่น"
    :en "Edition"}
   :material
   {:th "วัสดุ"
    :en "Material"}
   :ingredient
   {:th "ส่วนผสม"
    :en "Ingredient"}
   :condition
   {:th "สภาพ"
    :en "Condition"}
   :width
   {:th "กว้าง"
    :en "Width"}
   :length
   {:th "ยาว"
    :en "Length"}
   :height
   {:th "สูง"
    :en "Height"}
   :weight
   {:th "น้ำหนัก"
    :en "Weight"}
   :mint-asset-submit
   {:th "ยืนยันการสร้าง NFT"
    :en "Submit mint NFT"}
   :image
   {:th "รูปภาพ"
    :en "Image"}})

(defn get-dicts-by-lang [lang]
  (into {} (map (fn [[k v]] {k (v lang)}) (dicts))))




