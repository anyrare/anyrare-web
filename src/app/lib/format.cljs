(ns app.lib.format
  (:require
   [goog.i18n.NumberFormat.Format]
   [goog.string :as gstring]
   [goog.string.format]
   ["moment" :as moment]
   ["moment/locale/th"])
  (:import
   (goog.i18n NumberFormat)
   (goog.i18n.NumberFormat Format)))

(defn format-money
  [num digit]
  (.format (NumberFormat. Format/DECIMAL) (gstring/format (str "%." digit "f") num)))

(defn unix-timestamp-to-local-date
  [unix-timestamp]
  (-> (moment. unix-timestamp "X") (.locale "th") (.add 543 "year") (.format "ll")))