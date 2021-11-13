(ns app.config.i18n-test
  (:require
   [cljs.test :refer-macros [deftest testing is]]
   [app.config.i18n :refer [get-lang]]))

(deftest test-get-lang
  (testing "should get lang var from localStorage"
   (is (= (get-lang) "th"))))