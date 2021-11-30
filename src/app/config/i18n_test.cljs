(ns app.config.i18n-test
  (:require
   [cljs.test :refer-macros [deftest testing is]]
   [app.lib.localstorage :refer [get-lang]]
   [app.config.i18n :refer [get-dicts-by-lang]]))

(deftest test-get-lang
  (testing "should get lang var from localStorage"
   (is (= (get-lang) "th"))))


(deftest test-get-dicts-by-lang
  (testing "should get dicts by lang th"
    (-> (get-dicts-by-lang :th)
        (as-> dicts (is (= (dicts :day) "วัน")))))
  (testing "should get dicts by lang en"
    (-> (get-dicts-by-lang :en)
        (as-> dicts (is (= (dicts :day) "Day"))))))