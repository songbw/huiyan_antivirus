(ns huiyan-antivirus.entity.entities
  (:use [korma.core]))

(declare customers client_info killing virus feedback apps)

(defentity customers
  (table :customers))

(defentity client_info
  (table :client_info))

(defentity killing
  (table :killing))

(defentity virus
  (table :virus))

(defentity feedback
  (table :feedback))

(defentity apps
  (table :apps))

(defentity sdk_version
  (table :sdk_version))

(defentity virus_version
  (table :virus_version))
