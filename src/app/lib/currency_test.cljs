(ns app.lib.currency-test
  (:require
   [cljs.test :refer-macros [deftest testing is]]
   [app.lib.currency :refer [convert-ara-to-local-currency convert-ara-to-thb]]))

(deftest test-convert-ara-to-local-currency-test
  (is (= (convert-ara-to-local-currency 2) (convert-ara-to-thb 2))))
