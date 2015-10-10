(ns huiyan-antivirus.route
  (:refer-clojure :exclude [resultset-seq])
  (:require [compojure.route :as route]
            [clojure.string :as str]
            [huiyan-antivirus.customers.controllers.customers :as customers]
            [huiyan-antivirus.client-infos.controllers.client-infos :as client-infos]
            [huiyan-antivirus.killings.controllers.killings :as killings]
            [huiyan-antivirus.feedbacks.controllers.feedbacks :as feedbacks]
            [huiyan-antivirus.virus-version.controllers.virus-versionc :as virus-versionc]
            [huiyan-antivirus.sdk-version.controllers.sdk-versionc :as sdk-versionc]
            )
  (:use [compojure.core]
        [cheshire.core]
        [ring.util.response]))

(defroutes huiyan-routes
  (POST "/customer" [] customers/add-customer)
  (POST "/baseinfo" [] client-infos/add-client-info)
  (PUT "/version" [] client-infos/modify-client-info)
  (POST "/killing" [] killings/add-killing)
  (POST "/feedback" [] feedbacks/add-feedback)
  (GET "/dataversion" [] virus-versionc/get-version)
  (POST "/dataversion" [] virus-versionc/add-virusv)
  (GET "/sdkversion" [] sdk-versionc/get-version)
  (POST "/sdkversion" [] sdk-versionc/add-sdkv)
)
