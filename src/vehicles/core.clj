(ns vehicles.core
  (:gen-class)
  (:require [vehicles.api :as vehicles.api]
            [ring.adapter.jetty :as jetty]))

(defn -main []
  (jetty/run-jetty vehicles.api/app {:port 3000}))
