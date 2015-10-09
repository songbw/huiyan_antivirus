(ns huiyan-antivirus.customers.controllers.customers
  (:require [clojure.string :as str]
            [ring.util.response :as resp]
            [cheshire.core :as json])
  (:require [huiyan-antivirus.customers.models.customer :as customer]
            [utils.web :as web]
            [utils.common :as common]
            [taoensso.carmine :as car :refer (wcar)]
            [utils.log :as log])
  (:use [compojure.core]
        [utils.web]))

(def server1-conn {:pool {} :spec {:host (common/getParam "redis_host" "182.92.186.153"):port 6379}})

(defmacro wcar* [& body] `(car/wcar server1-conn ~@body))

(def customer-key [:company :address :telephone :appKey :secert :username :passwd :status])

(defn def-value
  [value def_value]
  (if (integer? value)
    value
    (if (empty? value)
      def_value
      value)))

(defn add-customer
  [req]
  (log/info (select-keys (:params req) customer-key))
  (let [cid (customer/create! (select-keys (:params req) customer-key))]
    (log/info cid)
    (wcar*
     (car/ping)
     (car/set (:appKey (:params req)) (str cid))))
  (log/info "woxiangzhidaozheshishuidayinchulai de " (wcar*
                                                      (car/ping)
                                                      (car/get (:secert (:params req)))))
  (resp/response {:success true}))


