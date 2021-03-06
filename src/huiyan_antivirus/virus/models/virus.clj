(ns huiyan-antivirus.virus.models.virus
  (:require [clojure.java.jdbc :as sql]
            [utils.log :as log]
            [utils.uuid :as uuid]
            huiyan-antivirus.config.database)
  (:use [korma.core]
        [korma.db]
        [huiyan-antivirus.entity.entities]))

(defn create!
  [params]
  (log/info params)
  (transaction
   (insert virus
           (values (assoc params :vid (uuid/gen-uuid))))))
