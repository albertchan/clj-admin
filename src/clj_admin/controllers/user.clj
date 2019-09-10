(ns clj-admin.controllers.user)

(defn index
  "List all users."
  [_]
  "index")

(defn create-user
  "Create a new user."
  [{user :body}]
  "create" user)

(defn show-user
  "View a user."
  [req]
  (let [{{:keys [id]} :params} req]
    (str "show id: " id)))

(defn update-user
  "Updates a user."
  [{user :body}]
  "update" user)

(defn delete-user
  "Deletes a user."
  [req]
  "delete")