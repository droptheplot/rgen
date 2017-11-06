(ns rgen.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [clojure.data.codec.base64 :as base64]
            [clojure.data.generators :as generators])
  (:gen-class))

(defn generate-uuid []
  (str (generators/uuid)))

(defn generate-base64 []
  (String. (base64/encode (.getBytes (generate-uuid)))))

(def fmt-titles
  {:uuid "UUID"
   :base64 "Base64"})
(def fmt-funcs
  {:uuid generate-uuid
   :base64 generate-base64})

(defn generate [fmt size]
  (take size (repeatedly #((get fmt-funcs fmt)))))

(defn copy-to-clipboard [text]
  (.setContents
    (.getSystemClipboard (java.awt.Toolkit/getDefaultToolkit))
    (java.awt.datatransfer.StringSelection. text) nil)
  (println (str "\n\033[2mCopied to clipboard!\033[0m")))

(defn print-title [size title]
  (println (str "\n\033[92mGenerating " size " " title "...\033[0m\n")))

(defn call [options arguments errors summary]
  (def fmt   (get options :format))
  (def result (generate fmt (get options :size)))

  (print-title (get options :size) (get fmt-titles fmt))
  (doseq [s result] (println s))

  (if (get options :copy) (copy-to-clipboard (clojure.string/join "\n" result))))

(def cli-options
  [["-f" "--format FORMAT" "Format"
    :id :format
    :default :uuid
    :parse-fn #(keyword %)
    :validate [#(contains? fmt-titles %) "Only `uuid` and `base64` are allowed."]]
   ["-s" "--size SIZE" "Size"
    :id :size
    :default 1
    :parse-fn #(Integer/parseInt %)
    :validate [#(< 1 % 0x10000) "Must be a number between `1` and `65536`."]]
   ["-c" "--copy" "Copy to clipboard"
    :id :copy
    :default false]
   ["-h" "--help" "Help"]])

(defn -main [& args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
    (call options arguments errors summary)))
