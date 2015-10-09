(ns huiyan-antivirus.feedbacks.models.feedback
  (:require [clojure.java.jdbc :as sql]
            [utils.log :as log]
            [utils.uuid :as uuid]
            huiyan-antivirus.config.database)
  (:use [korma.core]
        [korma.db]
        [huiyan-antivirus.entity.entities]))

(defn create!
  [params]
  (let [fid (uuid/gen-uuid)
        params (merge params {:cid (bigdec (:ciid params)) :ciid (bigdec (:ciid params)) :appTime (java.sql.Timestamp. (:appTime params))})]
    (transaction
     (insert feedback
             (values (assoc params :fid fid))))
   fid)
  )
