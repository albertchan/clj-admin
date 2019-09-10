(ns clj-admin.utils.string)

(defn uuid-from-string [data]
  (java.util.UUID/fromString data))
