(defproject vehicles "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/java.jdbc "0.4.1"]
                 [org.postgresql/postgresql "9.4-1202-jdbc42"]
                 [clojure.jdbc/clojure.jdbc-c3p0 "0.3.2"]
                 [ring "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [ring/ring-json "0.4.0"]
                 [org.clojure/data.json "0.2.6"]
                 [compojure "1.4.0"]]
  :profiles {:dev {:plugins [[cider/cider-nrepl "0.10.0-SNAPSHOT"]]}
            }
  :main vehicles.core
  :aot [vehicles.core])
