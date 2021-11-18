(ns app.lib.format
  (:require
   [goog.i18n.NumberFormat.Format]
   [goog.string :as gstring]
   [goog.string.format])
  (:import
   (goog.i18n NumberFormat)
   (goog.i18n.NumberFormat Format)))

(defn format-money
  [num digit]
  (.format (NumberFormat. Format/DECIMAL) (gstring/format (str "%." digit "f") num)))

(defn unix-time-to-local-string
  [unix-time]
  (str 3485736776))