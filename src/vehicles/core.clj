(ns vehicles.core
  (:require [clojure.java.jdbc :as db]
            [jdbc.pool.c3p0 :as pool]
            [ring.adapter.jetty :as jetty]
            [clojure.data.json :as json]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]))

(def my-db {:subprotocol "postgresql"
            :subname "//127.0.0.1:5432/zipcsv-project"
            :user "zipcsv-project"
            :password "zipcsv-project"})

(def my-pool (pool/make-datasource-spec my-db))

(def vehicles (db/query my-pool ["SELECT * FROM vehicles LIMIT 10"]))

(defn json-response [data & [status]]
  {:status  (or status 200)
   :headers {"Content-Type" "application/json; charset=utf-8"}
   :body    (json/write-str data)})

(defroutes myapp
  (GET "/api/vehicles" [] (json-response vehicles))
  (route/not-found "<h1>Page not found</h1>"))

(defn -main []
  (jetty/run-jetty myapp {:port 3000}))
