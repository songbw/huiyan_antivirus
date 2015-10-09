(ns huiyan-antivirus.client-infos.models.client-info
  (:require [clojure.java.jdbc :as sql]
            [utils.log :as log]
            [utils.uuid :as uuid]
            huiyan-antivirus.config.database)
  (:use [korma.core]
        [korma.db]
        [huiyan-antivirus.entity.entities]))

(defn create!
  [params]
  (let [ciid (uuid/gen-uuid)
        params (merge params {:cid (bigdec (:cid params)) :appTime (java.sql.Timestamp. (:appTime params))})]
    (transaction
     (insert client_info
             (values (assoc params :ciid ciid))))
   ciid)
  )

(defn update!
  [params]
  (transaction
   (update client_info
           (set-fields (dissoc params :ciid))
           (where (update-in (select-keys params [:ciid]) [:ciid] bigdec))))
  )

(defn find-by-ciid
  [ciid]
  (select client_info
          (fields :cid :uname :sex :age :nickname :email :telphone :cpuModel :cpuKernel :cpuFrequency :ramSize :romSize :screenSize :hardwareId :operators :sysName :sysVersion :telVersion :telBrand :telModel :version :virusDataVersion :plusHostVersion :appTime)
          (where (update-in ciid [:ciid] bigdec))))


