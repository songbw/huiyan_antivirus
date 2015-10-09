(ns huiyan-antivirus.virus-version.models.virus-versionm
  (:require [clojure.java.jdbc :as sql]
            [utils.log :as log]
            [utils.uuid :as uuid]
            huiyan-antivirus.config.database)
  (:use [korma.core]
        [korma.db]
        [huiyan-antivirus.entity.entities]))

(defn create!
  [params]
  (transaction
   (insert virus_version
           (values (assoc params :svid (uuid/gen-uuid)))))
  )

(defn find-by-version
  [version]
  (select virus_version
          (fields :id)
          (where {:virusDataVersion version}))
  )

(defn find-version
  [id]
  (select virus_version
          (fields :md5 :dataurl :virusDataVersion :scope :amount :createAt)
          (where {:id (+ id 1)})))
