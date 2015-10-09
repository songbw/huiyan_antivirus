(ns huiyan-antivirus.killings.controllers.killings
  (:require [clojure.string :as str]
            [ring.util.response :as resp]
            [cheshire.core :as json])
  (:require [huiyan-antivirus.killings.models.killing :as killing]
            [huiyan-antivirus.virus.models.virus :as virusm]
            [utils.web :as web]
            [utils.common :as common]
            [utils.log :as log])
  (:use [compojure.core]
        [utils.web]))

(def killing-key [:cid :ciid :spendTime :killingTime :killingType :status])

(defn add-killing
  [req]
  (log/info (select-keys (:params req) killing-key))
  (let [kid (killing/create! (select-keys (:params req) killing-key))]
    (doseq [x (:virusList (:params req))]
      (virusm/create! (assoc x :kid kid)))
    )
  (resp/response {:success true}))
