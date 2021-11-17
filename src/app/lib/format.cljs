(ns app.lib.format
  (:require
   [goog.i18n.NumberFormat.Format]
   [goog.string :as gstring]
   [goog.string.format])
  (:import
   (goog.i18n NumberFormat)
   (goog.i18n.NumberFormat Format)))

(defn format-money
  [num]
  (.format (NumberFormat. Format/DECIMAL) (gstring/format "%.4f" num)))
