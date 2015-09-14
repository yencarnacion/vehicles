(ns vehicles.core
  (:require [clojure.java.jdbc :as db]
            [jdbc.pool.c3p0 :as pool]
            [ring.adapter.jetty :as jetty]
            [clojure.data.json :as json]))

(def my-db {:subprotocol "postgresql"
            :subname "//127.0.0.1:5432/zipcsv-project"
            :user "zipcsv-project"
            :password "zipcsv-project"})

(def my-pool (pool/make-datasource-spec my-db))

(def vehicles (db/query my-pool ["SELECT * FROM vehicles LIMIT 10"]))

(defn myapp [request]
  {:body (json/write-str vehicles)
   :status 200
   :headers {"Content-Type" "text/html"}})

(defn -main []
  (jetty/run-jetty myapp {:port 3000}))
