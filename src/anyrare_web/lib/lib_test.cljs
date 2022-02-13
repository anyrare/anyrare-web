(ns anyrare-web.lib.lib-test
  (:require [cljs.test :refer-macros [deftest testing is async]]
            [anyrare-web.lib.ipfs :as ipfs]
            [goog.object :as gobj]
            [anyrare-web.lib.utils :as utils]))

(deftest ipfs-upload-file
  (async
   done
   (ipfs/upload-file "/home/ps/Downloads/ss3.png" (fn [] (done)))))


;; (deftest ipfs-upload-json
;;   (async
;;    done
;;    (ipfs/upload-json {:key "hello world"}
;;                      (fn [res]
;;                        (is (string? (:ipfs-hash res)))
;;                        (done)))))


