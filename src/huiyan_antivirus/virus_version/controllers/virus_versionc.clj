(ns huiyan-antivirus.virus-version.controllers.virus-versionc
  (:require [clojure.string :as str]
            [ring.util.response :as resp]
            [cheshire.core :as json])
  (:require [huiyan-antivirus.virus-version.models.virus-versionm :as virus-versionm]
            [utils.web :as web]
            [utils.common :as common]
            [utils.log :as log])
  (:use [compojure.core]
        [utils.web]))

(def virusv-key [:md5 :dataurl :virusDataVersion :scope :amount])

(defn add-virusv
  [req]
  (log/info (select-keys (:params req) virusv-key))
  (virus-versionm/create! (select-keys (:params req) virusv-key))
  (resp/response {:success true}))

(defn get-version
  [req]
  (let [params (:params req)
        version (:virusDataVersion params)
        id (:id (first (virus-versionm/find-by-version version)))]
    (resp/response (first (virus-versionm/find-version id)))))
