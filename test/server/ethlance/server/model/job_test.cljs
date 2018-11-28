(ns ethlance.server.model.job-test
  "Unit tests for the job model."
  (:require
   [clojure.test :refer [deftest is are testing]]
   [bignumber.core :as bn]
   [cuerdas.core :as str]
   [district.server.config]
   [district.server.db :as district.db]
   [taoensso.timbre :as log]
   [ethlance.server.db :as ethlance.db]
   [ethlance.server.model.user :as user]
   [ethlance.server.model.arbiter :as arbiter]
   [ethlance.server.model.candidate :as candidate]
   [ethlance.server.model.employer :as employer]
   [ethlance.server.model.job :as job]
   [ethlance.server.test-utils.db :refer [deftest-database] :include-macros true]
   [ethlance.shared.enum.currency-type :as enum.currency]
   [ethlance.shared.enum.bid-option :as enum.bid-option]
   [ethlance.shared.enum.boolean :as enum.boolean]
   [ethlance.shared.enum.contract-status :as enum.status]
   [ethlance.shared.enum.payment-type :as enum.payment]))


(deftest-database main-job-model {}
  ;;
  ;; Setup
  ;;

  ;; Employer
  (user/register! {:user/id 1
                   :user/address "0x1"
                   :user/country-code "CA"
                   :user/email "john.doe@gmail.com"
                   :user/profile-image ""
                   :user/date-last-active 0
                   :user/date-joined 0})

  (employer/register! {:user/id 1
                       :employer/biography "A testy fellow"
                       :employer/date-registered 0
                       :employer/professional-title "Project Manager"})

  ;; Candidate
  (user/register! {:user/id 2
                   :user/address "0x2"
                   :user/country-code "US"
                   :user/email "jane.doe@gmail.com"
                   :user/profile-image ""
                   :user/date-last-active 1
                   :user/date-joined 1})

  (candidate/register! {:user/id 2
                        :candidate/biography "A testy fellow"
                        :candidate/date-registered 0
                        :candidate/professional-title "Software Developer"})

  ;; Arbiter
  (user/register! {:user/id 3
                   :user/address "0x3"
                   :user/country-code "RU"
                   :user/email "nicholai.pavlov@gmail.com"
                   :user/profile-image ""
                   :user/date-last-active 3
                   :user/date-joined 3})

  (arbiter/register! {:user/id 3
                      :arbiter/biography "I am testy."
                      :arbiter/date-registered 3
                      :arbiter/currency-type ::enum.currency/eth
                      :arbiter/payment-value 5
                      :arbiter/payment-type ::enum.payment/percentage})

  (testing "Creating a job"
    (job/create-job! {:job/id 1
                      :job/title "Full-Stack Java Developer"
                      :job/availability 0
                      :job/bid-option ::enum.bid-option/hourly-rate
                      :job/category "Software Development"
                      :job/description "The job is great."
                      :job/date-created 5
                      :job/employer-uid "0x1"
                      :job/estimated-length-seconds (* 30 24 60 60)
                      :job/include-ether-token? true
                      :job/is-invitation-only? false}))

  (testing "Adding arbiter requests"
    (is (= (count (job/arbiter-request-listing 1)) 0))

    (job/add-arbiter-request! {:job/id 1
                               :user/id 3
                               :arbiter-request/date-requested 6
                               :arbiter-request/is-employer-request? true})

    (is (thrown? js/Error (job/add-arbiter-request! {:job/id 1
                                                     :user/id 3
                                                     :arbiter-request/date-requested 6
                                                     :arbiter-request/is-employer-request? true})))

    (is (= (count (job/arbiter-request-listing 1)) 1))

    (let [[arbiter-request] (job/arbiter-request-listing 1)]
      (is (true? (:arbiter-request/is-employer-request? arbiter-request)))))

  (testing "Adding and updating work contract."
    (is (= (count (job/work-contract-listing 1)) 0))


    (job/create-work-contract! {:job/id 1
                                :work-contract/index 0
                                :work-contract/contract-status ::enum.status/initial
                                :work-contract/date-created 7
                                :work-contract/date-updated 7})

    (is (= (count (job/work-contract-listing 1)) 1))

    (let [[work-contract] (job/work-contract-listing 1)]
      (is (= (:work-contract/contract-status work-contract) ::enum.status/initial)))

    ;; Update the work contract
    (job/update-work-contract! {:job/id 1
                                :work-contract/index 0
                                :work-contract/contract-status ::enum.status/request-candidate-invite
                                :work-contract/date-created 7
                                :work-contract/date-updated 7})
    
    (let [[work-contract] (job/work-contract-listing 1)]
      (is (= (:work-contract/contract-status work-contract) ::enum.status/request-candidate-invite)))))