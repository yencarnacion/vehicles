(ns vehicles.api
  (:require [vehicles.db :refer [pool]]
            [clojure.java.jdbc :as db]
            [jdbc.pool.c3p0 :as pool]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :as ring-json]
            [ring.util.response :as resp]
            ))

(def vehicles (db/query pool ["SELECT * FROM vehicles LIMIT 10"]))

(defroutes app-routes
  (GET "/" [] (resp/content-type (resp/resource-response "index.html" {:root "public"}) "text/html"))
  (GET "/api/vehicles" [] (resp/content-type (resp/response vehicles) "application/json; charset=utf-8"))
  (route/not-found "<h1>Page not found</h1>"))

(def app
  (-> app-routes
      (wrap-defaults site-defaults)
      (ring-json/wrap-json-response)))
