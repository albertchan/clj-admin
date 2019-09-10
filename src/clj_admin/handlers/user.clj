(ns clj-admin.handlers.user
  (:refer-clojure :exclude [update])
  (:require
   [clojure.java.jdbc :as jdbc]
   [honeysql.core :as sql]
   [honeysql.helpers :refer [delete-from insert-into from select sset update values where] :as helpers]
   [clj-admin.db :refer [datasource]]
   [clj-admin.utils.string :refer [uuid-from-string]]))

(defn find-by-id 
  [id]
  (jdbc/with-db-connection [conn {:datasource datasource}]
    (let [query (-> (select :*)
                    (from :users)
                    (where [:= :id (uuid-from-string id)])
                    sql/format)]
      (->> (jdbc/query conn query)
           (map #(dissoc % :password))))))

(defn index-handler
  [_]
  (jdbc/with-db-connection [conn {:datasource datasource}]
    (let [rows (->> (jdbc/query conn "SELECT * from users")
                    (map #(dissoc % :password)))]
      {:status 200
       :body rows})))

(defn create-handler
  [{{:keys [email name avatar_url bio locale]} :body-params}]
  (jdbc/with-db-connection [conn {:datasource datasource}]
    (let [user {:email email 
                :name name
                :avatar_url avatar_url
                :bio bio
                :locale locale}
          row (->> (jdbc/insert! conn :users user)
                   (map #(dissoc % :password)))]
      {:status 201
       :body row})))

(defn show-handler
  [{{:keys [id]} :path-params}]
    {:status 200
     :body (find-by-id id)})

(defn update-handler
  [{{:keys [id]} :path-params
    {:keys [email name avatar_url bio locale]} :body-params
    params :body-params}]
  (jdbc/with-db-connection [conn {:datasource datasource}]
    (let [query (-> (helpers/update :users)
                    (sset {:email email
                           :name name
                           :avatar_url avatar_url
                           :bio bio
                           :locale locale})
                    (where [:= :id (uuid-from-string id)])
                    sql/format)
          _ (-> (jdbc/execute! conn query))]
      {:status 200
       :body (find-by-id id)})))

(defn delete-handler
  [{{:keys [id]} :path-params}]
  (jdbc/with-db-connection [conn {:datasource datasource}]
    (let [query (-> (delete-from :users)
                    (where [:= :id (uuid-from-string id)])
                    sql/format)
          row (-> (jdbc/execute! conn query))]
      {:status 204})))

; GET /api/v1/users
(def index
  {:summary "List all users"
   :handler index-handler})

; POST /api/v1/users
(def create-user
  {:summary "Creates a new user"
   :handler create-handler})

; GET /api/v1/users/:id
(def show-user
  {:summary "Show user with id"
   :handler show-handler})

; PUT /api/v1/users/:id
(def update-user
  {:summary "Update user with id"
   :handler update-handler})

; DELETE /api/v1/users/:id
(def delete-user
  {:summary "Delete user with id"
   :handler delete-handler})