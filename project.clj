(defproject clj-admin "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"

  :dependencies 
  [[org.clojure/clojure "1.10.0"]
   [org.postgresql/postgresql "42.2.2"]
   [org.slf4j/slf4j-simple "1.7.28"]
   [hikari-cp "2.9.0"]
   [honeysql "0.9.8"]
   [http-kit "2.3.0"]
   [metosin/muuntaja "0.6.4"]
   [metosin/reitit "0.3.9"]
   [mount "0.1.16"]
   [ragtime "0.8.0"]
   [ring/ring-defaults "0.3.2"]]

  :plugins
  [[lein-ring "0.12.5"]]

  :ring {:handler clj-admin.handler/app}

  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}}

  :aliases {"migrate"  ["run" "-m" "scripts.migration/migrate"]
            "rollback" ["run" "-m" "scripts.migration/rollback"]})
