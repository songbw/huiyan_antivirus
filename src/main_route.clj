(ns main-route
  (:refer-clojure :exclude [resultset-seq])
  (:require [compojure.route :as route])
  (:use [compojure.core]
        [cheshire.core]
        [ring.util.response])
  (:use [huiyan-antivirus.route])
  )

(defroutes core-routes
  (GET "/" [] (response {:hello "It works!"}))
  (context "/huiyan" [] huiyan-routes)
  (route/not-found "Not Found"))
