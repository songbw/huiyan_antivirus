(ns huiyan-antivirus.feedbacks.controllers.feedbacks
  (:require [clojure.string :as str]
            [ring.util.response :as resp]
            [cheshire.core :as json])
  (:require [huiyan-antivirus.feedbacks.models.feedback :as feedback]
            [huiyan-antivirus.apps.models.appm :as appm]
            [utils.web :as web]
            [utils.common :as common]
            [utils.log :as log])
  (:use [compojure.core]
        [utils.web]))

(def feedback-key [:cid :ciid :appTime :actionType :text])

(defn add-feedback
  [req]
  (log/info (select-keys (:params req) feedback-key))
  (let [fid (feedback/create! (select-keys (:params req) feedback-key))]
    (doseq [x (:appList (:params req))]
      (appm/create! (assoc x :fid fid))))
  (resp/response {:success true}))
