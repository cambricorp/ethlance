(ns ethlance.server.contract.ethlance-jobs
  (:require [ethlance.server.contract.ethlance-issuer :as ethlance-issuer]
            [district.server.smart-contracts :as contracts]
            [ethlance.server.contract :as ethlance-contracts]))

(defn apply-as-candidate [address [job-id candidate :as args] opts]
  (contracts/contract-send [ethlance-issuer/*ethlance-jobs-key* address] :apply-as-candidate args (merge {:gas 5e6} opts)))

(defn accept-candidate [address [job-id candidate :as args] opts]
  (contracts/contract-send [ethlance-issuer/*ethlance-jobs-key* address] :accept-candidate args (merge {:gas 5e6} opts)))

(defn fulfill-job [address [sender job-id fulfillers ipfs-hash amount :as args] opts]
  (contracts/contract-send [ethlance-issuer/*ethlance-jobs-key* address] :fulfill-job args (merge {:gas 5e6} opts)))
