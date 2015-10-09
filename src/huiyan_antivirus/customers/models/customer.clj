(ns huiyan-antivirus.customers.models.customer
  (:require [clojure.java.jdbc :as sql]
            [utils.log :as log]
            [utils.uuid :as uuid]
            huiyan-antivirus.config.database)
  (:use [korma.core]
        [korma.db]
        [huiyan-antivirus.entity.entities]))

(defn create!
  [params]
  (let [cid (uuid/gen-uuid)]
    (transaction
     (insert customers
             (values (assoc params :cid cid))))
    cid)
  )

(defn find-by-secert
  [secert]
  (select customers
          (fields :cid)
          (where {:secert secert})))
