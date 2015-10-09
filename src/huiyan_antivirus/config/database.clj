(ns huiyan-antivirus.config.database
  (:require [clojure.string :as string])
  (:use [korma.db])
  (:use [utils.common]))

(def db-pg (postgres {:db (getParam "db_name" "huiyan_antivirus")
                      :user (getParam "db_user" "postgres")
                      :password (getParam "db_password" "school_car")
                      :host (getParam "db_host" "182.92.186.153")
                      :port (getParam "db_port" "5432")
                      :delimiters ""
                      :nameing {:keys string/lower-case
                                :fields string/upper-case}}))

(defdb db db-pg)
