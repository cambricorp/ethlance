(ns ethlance.server.model.synchronization
  "For inserting and getting data from the :EthlanceSynchronizationLog
  table in the ethlance database."
  (:require
   [clojure.spec.alpha :as s]
   [bignumber.core :as bn]
   [cuerdas.core :as str]
   [district.server.config]
   [district.server.db :as district.db]
   [taoensso.timbre :as log]
   [ethlance.server.db :as ethlance.db]
   
   ;; Includes additional spec namespaces
   [ethlance.shared.spec :as espec]))


(defn log!
  "Log a synchronization event"
  [log-data]
  (ethlance.db/insert-row! :EthlanceSynchronizationLog log-data))


(defn log-event!
  "Logs an event returned by an Event Watcher with the given `status`."
  [{:keys [] :as event}]
  (let []))


(defn get-log-listing
  "Gets all of the logging data"
  []
  (ethlance.db/get-list :EthlanceSynchronizationLog {}))