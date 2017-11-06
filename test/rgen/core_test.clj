(ns rgen.core-test
  (:require [clojure.test :refer :all]
            [rgen.core :refer :all]))

(deftest generate-uuid-test
  (testing "returns uuid string"
    (is (= (count (generate-uuid)) 36))))

(deftest generate-base64-test
  (testing "returns base64 string"
    (is (= (count (generate-base64)) 48))))

(deftest generate-test
  (testing "returns n size list with strings"
    (is (= (count (generate :uuid 3)) 3))))
