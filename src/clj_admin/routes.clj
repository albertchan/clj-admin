(ns clj-admin.routes
  (:require
   [reitit.ring :as ring]
   [reitit.ring.coercion :as coercion]
   [reitit.ring.middleware.muuntaja :as muuntaja]
   [reitit.ring.middleware.parameters :as parameters]
   [reitit.coercion.spec]
   [muuntaja.core :as m]
   [ring.middleware.params :as params]
   [clj-admin.handlers.app :as app]
   [clj-admin.handlers.user :as user]))

(def app-routes
  [["/" {:get app/handler}]
   ["/ping" {:get app/ping}]])

(def user-routes
  [["/api/v1/users"
    {:get user/index
     :post user/create-user}]
   ["/api/v1/users/:id"
    {:get user/show-user
     :put user/update-user
     :delete user/delete-user}]])

; Routing
(def handlers
  (ring/ring-handler
    (ring/router
      [app-routes
       user-routes]
      {:data
        {:coercion reitit.coercion.spec/coercion
         :muuntaja m/instance
         :middleware [params/wrap-params
                      muuntaja/format-middleware
                      coercion/coerce-exceptions-middleware
                      coercion/coerce-request-middleware
                      coercion/coerce-response-middleware
                      parameters/parameters-middleware]}})))
