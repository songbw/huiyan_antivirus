(ns huiyan-antivirus.virus.controllers.virusc
  (:require [clojure.string :as str]
            [ring.util.response :as resp]
            [cheshire.core :as json])
  (:require [huiyan-antivirus.virus.models.virus :as virus]
            [utils.web :as web]
            [utils.common :as common]
            [utils.log :as log])
  (:use [compojure.core]
        [utils.web]))

(def virus-key [:kid :virusId :virusName :hostAppName])

(defn add-virus
  [req]
  (log/info (select-keys (:params req) virus-key))
  (virus/create! (select-keys (:params req) virus-key))
  (resp/response {:success true}))
