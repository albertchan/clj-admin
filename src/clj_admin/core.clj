(ns clj-admin.core
  (:require
   [clojure.java.jdbc :as jdbc]
   [mount.core :refer [defstate] :as mount]
   [org.httpkit.server :refer [run-server] :as http]
   [clj-admin.db :as db]
   [clj-admin.routes :as routes]))

(def SERVER_PORT 3000)

; Http server instance
(defonce server (atom nil))

(defn start-server
  "Start server and get connection pool"
  []
  (println "🌎  Started server on port:" SERVER_PORT)
  (reset! server (http/run-server routes/handlers {:port SERVER_PORT})))

(defn stop-server 
  "Graceful shutdown, wait 200ms for existing requests to be finished"
  []
  (when-not (nil? @server)
    (@server :timeout 200)
    (reset! server nil)))

(mount/defstate http-server
  :start (start-server)
  :stop (stop-server))

(defn -main
  "Main entry ..."
  [& args]
  (mount/start))
