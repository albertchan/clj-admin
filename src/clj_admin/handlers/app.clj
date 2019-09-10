(ns clj-admin.handlers.app)

(defn handler [_]
  {:status 200, :body "ok"})

(defn ping [_]
  {:status 200, :body "pong"})