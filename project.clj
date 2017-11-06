(defproject rgen "0.1.0-SNAPSHOT"
  :description "CLI tool to generate UUID, Base64 etc."
  :url "https://github.com/droptheplot/rgen"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.cli "0.3.5"]
                 [org.clojure/data.codec "0.1.0"]
                 [org.clojure/data.generators "0.1.2"]
                 [lein-binplus "0.6.2"]]
  :main ^:skip-aot rgen.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
