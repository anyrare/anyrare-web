(ns anyrare-web.lib.format
  (:require
   [goog.i18n.NumberFormat.Format]
   [goog.string :as gstring]
   [goog.string.format]
   ["moment" :as moment]
   ["moment/locale/th"]
   ["bignumber.js" :refer [BigNumber]])
  (:import
   (goog.i18n NumberFormat)
   (goog.i18n.NumberFormat Format)))

(def erc20-digit 18)
(def erc20-digit-scaling-factor "1000000000000000000")

(defn num->big-number
  [num]
  (BigNumber. (str num)))

(defn format-money
  [num digit]
  (-> (gstring/format (str "%." digit "f") num)))

(defn format-currency
  [num]
  (prn (BigNumber. (str num)))
  (->
   (num->big-number num)
   (.dividedBy erc20-digit-scaling-factor)
   (.toFixed 4)))

(defn format-currency->number
  [currency]
  (-> (num->big-number currency)
      (.times erc20-digit-scaling-factor)
      (.toFixed 0)))

(defn unix-timestamp-to-local-date
  [unix-timestamp]
  (-> (moment. unix-timestamp "X")
      (.locale "th") (.add 543 "year") (.format "ll")))

(defn unix-timestamp-to-local-datetime
  [unix-timestamp]
  (-> (moment. unix-timestamp "X")
      (.locale "th") (.add 543 "year") (.format "lll")))

(defn trim-username [text & [len]]
  (let [text-len (if (nil? len) 12 len)]
    (if (<= (count text) text-len)
      text
      (str (.substring text 0 text-len) "..."))))

