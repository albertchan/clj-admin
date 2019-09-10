(ns clj-admin.db
  (:require
   [hikari-cp.core :refer :all :as hikari]
   [mount.core :refer [defstate] :as mount]))

(def datasource-options
  {:auto-commit        true
   :read-only          false
   :connection-timeout 30000
   :validation-timeout 5000
   :idle-timeout       600000
   :max-lifetime       1800000
   :minimum-idle       10
   :maximum-pool-size  10
   :pool-name          "db-pool"
   :adapter            "postgresql"
   :username           "postgres"
   :password           "postgres"
   :database-name      "clj_admin_dev"
   :server-name        "localhost"
   :port-number        5432
   :register-mbeans    false})

(mount/defstate datasource
  :start (hikari/make-datasource datasource-options)
  :stop (hikari/close-datasource datasource))