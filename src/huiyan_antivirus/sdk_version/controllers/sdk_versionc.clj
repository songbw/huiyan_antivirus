(ns huiyan-antivirus.sdk-version.controllers.sdk-versionc
  (:require [clojure.string :as str]
            [ring.util.response :as resp]
            [cheshire.core :as json])
  (:require [huiyan-antivirus.sdk-version.models.sdk-versionm :as sdk-versionm]
            [utils.web :as web]
            [utils.common :as common]
            [utils.log :as log])
  (:use [compojure.core]
        [utils.web]))

(def sdkv-key [:md5 :sdkurl :version :scope :size :decrip])


(defn add-sdkv
  [req]
  (log/info (select-keys (:params req) sdkv-key))
  (sdk-versionm/create! (select-keys (:params req) sdkv-key))
  (resp/response {:success true}))

(defn get-version
  [req]
  (let [params (:params req)
        version (:version params)
        id (:id (first (sdk-versionm/find-by-version version)))]
    (resp/response (first (sdk-versionm/find-version id)))))
