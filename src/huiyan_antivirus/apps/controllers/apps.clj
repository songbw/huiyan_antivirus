(ns huiyan-antivirus.apps.controllers.apps
  (:require [clojure.string :as str]
            [ring.util.response :as resp]
            [cheshire.core :as json])
  (:require [huiyan-antivirus.apps.models.appm :as appm]
            [utils.web :as web]
            [utils.common :as common]
            [utils.log :as log])
  (:use [compojure.core]
        [utils.web]))

(def apps-key [:fid :appName :appVersion :packageName :cacheSize :dataSize])

(defn add-apps
  [req]
  (log/info (select-keys (:params req) apps-key))
  (appm/create! (select-keys (:params req) apps-key))
  (resp/response {:success true}))
