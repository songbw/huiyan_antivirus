(ns huiyan-antivirus.killings.models.killing
  (:require [clojure.java.jdbc :as sql]
            [utils.log :as log]
            [utils.uuid :as uuid]
            huiyan-antivirus.config.database)
  (:use [korma.core]
        [korma.db]
        [huiyan-antivirus.entity.entities]))

(defn create!
  [params]
  (let [kid (uuid/gen-uuid)
        params (merge params {:cid (bigdec (:ciid params)) :ciid (bigdec (:ciid params)) :killingTime (java.sql.Timestamp. (:killingTime params))})]
    (transaction
     (insert killing
             (values (assoc params :kid kid))))
    kid)
  )
