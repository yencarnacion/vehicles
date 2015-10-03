(ns vehicles.db
  (:require [clojure.java.jdbc :as db]
            [jdbc.pool.c3p0 :as pool]
            ))

;; URI follows http://www.ietf.org/rfc/rfc2396.txt
(def db-uri
  (java.net.URI. (or
    (System/getenv "DATABASE_URL")
    "postgresql://zipcsv-project:zipcsv-project@localhost:5432/zipcsv-project")))

(def user-and-password
  (if (nil? (.getUserInfo db-uri))
    nil (clojure.string/split (.getUserInfo db-uri) #":")))

(def spec
  (pool/make-datasource-spec
    {:classname "org.postgresql.Driver"
    :subprotocol "postgresql"
    :user (get user-and-password 0)
    :password (get user-and-password 1)
    :subname (if (= -1 (.getPort db-uri))
                (format "//%s%s" (.getHost db-uri) (.getPath db-uri))
                (format "//%s:%s%s" (.getHost db-uri) (.getPort db-uri) (.getPath db-uri)))}))

(def pool spec)



