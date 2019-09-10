(ns scripts.migration
  (:require
   [ragtime.jdbc :as jdbc]
   [ragtime.repl :as repl]))

(defonce db-spec  
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname (str "//localhost:5432/clj_admin_dev")
   :user "postgres"
   :password "postgres"})

(defn load-config []
  {:datastore  (jdbc/sql-database db-spec)
   :migrations (jdbc/load-resources "migrations")})

(defn migrate []
  (repl/migrate (load-config)))
  
(defn rollback []
  (repl/rollback (load-config)))