(ns huiyan-antivirus.client-infos.controllers.client-infos
  (:require [clojure.string :as str]
            [ring.util.response :as resp]
            [cheshire.core :as json])
  (:require [huiyan-antivirus.client-infos.models.client-info :as client-info]
            [utils.web :as web]
            [utils.common :as common]
            [utils.log :as log])
  (:use [compojure.core]
        [utils.web]))

(def client-key [:ciid :cid :uname :sex :age :nickname :email :telphone :cpuModel :cpuKernel :cpuFrequency :ramSize :romSize :screenSize :hardwareId :operators :sysName :sysVersion :telVersion :telBrand :telModel :version :virusDataVersion :plusHostVersion :appTime])

(defn def-value
  [value def_value]
  (if (integer? value)
    value
    (if (empty? value)
      def_value
      value)))

(defn add-client-info
  [req]
  (log/info (select-keys (:params req) client-key))
  (resp/response {:uuid (client-info/create! (select-keys (:params req) client-key))}))

(defn modify-client-info
  [req]
  (log/info (select-keys (:params req) client-key))
  (let [m-client (first (client-info/find-by-ciid (select-keys (:params req) [:ciid])))
        param (dissoc (:params req) :appTime)
        params {:apptime (java.sql.Timestamp. (:appTime (:params req)))
                :plushostversion (:plusHostVersion param)
                :version (:version param)
                :virusdataversion (:virusDataVersion param)
                :ciid (:ciid param)}]
    (log/info m-client "\n" params)
    (client-info/update! (merge-with def-value (select-keys params client-key) m-client))
    (resp/response {:success true})))
