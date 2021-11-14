(ns app.config.i18n-test
  (:require
   [cljs.test :refer-macros [deftest testing is]]
   [app.lib.localstorage :refer [get-lang]]
   [app.config.i18n :refer [i18n]]))

(deftest test-get-lang
  (testing "should get lang var from localStorage"
   (is (= (get-lang) "th"))))

(deftest test-get-dict
  (testing "should get dict key"
    (is (= (i18n :collector) "นักสะสม"))))