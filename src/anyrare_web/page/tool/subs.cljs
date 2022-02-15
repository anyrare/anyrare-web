(ns anyrare-web.page.tool.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 ::image-by-id
 (fn [db [_ id]]
   (get-in db [id])))
