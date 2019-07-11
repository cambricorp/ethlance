(ns ethlance.ui.component.splash-layout
  (:require
   [re-frame.core :as rf]

   ;; Ethlance Components
   [ethlance.ui.component.ethlance-logo :refer [c-ethlance-logo]]
   [ethlance.ui.component.button :refer [c-button c-button-label]]
   [ethlance.ui.component.inline-svg :refer [c-inline-svg]]
   [ethlance.ui.component.icon :refer [c-icon]]
   [ethlance.ui.component.circle-button :refer [c-circle-icon-button]]
   [ethlance.ui.component.splash-navigation-bar :refer [c-splash-navigation-bar]]
   [ethlance.ui.component.splash-mobile-navigation-bar :refer [c-splash-mobile-navigation-bar]]))


(defn c-splash-layout
  []
  [:div.splash-layout
   [:div.mobile-header
    [c-splash-mobile-navigation-bar]]
   [:div.header
    [c-splash-navigation-bar]]
   [:div.main-content
    ;; BEGIN BANNER
    [:div.banner
     [:div.part-text
      [:h2 "The Future of Work is Now"]
      [:span "Hire or Work for Ether cryptocurrency"]
      [:div.button-listing
       [c-button       
        {:color :primary :size :auto}
        [c-button-label [:span "Become a "] [:b "Freelancer"]]]
       [c-button
        {:color :primary :size :auto}
        [c-button-label [:span "Become an "] [:b "Arbiter"]]]
       [c-button
        {:color :primary :size :auto}
        [c-button-label [:span "Become an "] [:b "Employer"]]]]
      [:div.district
       [:figure.img-district
        [:img {:src "./images/district.png"}]]
       [:p "Participate in Ethlance's governance processes:"]
       [:a {:href "http://district0x.io"}
        "Introducing the district0x Network"]]]
     
     [:div.part-figure
      [:figure
       [:img {:src "./images/img-top-banner.png"}]]]]

    ;; BEGIN WELCOME TO
    [:div.welcome
     [:h3 "Welcome to"]
     [:h2 "A Smarter Way to Work"]
     [:div.box-listing
      [:div.box-ball
       [:div.vertical-box
        [:figure
         [:img {:src "./images/icon-service.png"}]]
        [:div.text "0% service fees."]]]

      [:div.box-separator "+"]

      [:div.box-ball
       [:div.vertical-box
        [:figure
         [:img {:src "./images/icon-decentralised.png"}]]
        [:div.text "Fully decentralized on blockchain."]]]

      [:div.box-separator "+"]

      [:div.box-ball
       [:div.vertical-box
        [:figure
         [:img {:src "./images/icon-membership.png"}]]
        [:div.text "No restrictions and free membership."]]]]]
    
    [:div.created
     [:h3 "We Created"]
     [:h2 "Ethlance for Everyone!"]
     [:div.flex-box-listing
      [:div.horizontal-box
       [:figure
        [:img {:src "./images/take-no-cut.png"}]]
       [:div.text
        [:h3 "We take no cut"]
        [:p "Ethlance doesn't take a percentage of your earned
        Ether. The amount of Ether the employer pays is the amount the
        freelancer gets."]]]

      [:div.horizontal-box
       [:figure
        [:img {:src "./images/blockchain.png"}]]
       [:div.text
        [:h3 "It's all on blockchain"]
        [:p "The Ethlance database is distributed on the Ethereum
        public blockchain and the sources files are on IPFS. Ethlance
        is accessible to everyone forever, without any central
        authority having control over it."]]]

      [:div.horizontal-box
       [:figure
        [:img {:src "./images/costs.png"}]]
       [:div.text
        [:h3 "No artificial costs or restrictions"]
        [:p "Everybody can apply for, or create, an unlimited number
        of jobs. All that is needed is to pay Ethereum gas fees
        associated with these operations."]]]

      [:div.horizontal-box
       [:figure
        [:img {:src "./images/network.png"}]]
       [:div.text
        [:h3 "Part of the district0x Network"]
        [:p "Ethlance is the first district on the "
         [:a {:href "http://district0x.io"} "district0x Network"]
         ", a collective of decentralized marketplaces and
         communities."]]]]]

    [:div.checkout
     [:h3 "Check Out"]
     [:h2 "How Ethlance Works"]
     ;; TODO: owl listing component
     [:div.button-listing
      [c-button       
       {:color :primary}
       [c-button-label [:span "Freelancer"]]]
      [c-button       
       {:color :primary}
       [c-button-label [:span "Employer"]]]
      [c-button       
       {:color :primary}
       [c-button-label [:span "Arbiter"]]]]]]
   
   
   [:div.footer
    [:div.footer-content
     [:div.header-section
      [c-ethlance-logo {:color :white}]
      [:h2 "The Future of Work is Now."]
      [:h2 "Say up-to-date with Ethlance."]
      ;; TODO: email input component
      [:input {:type "email" :placeholder "Enter Email"}]]

     [:div.splash-section
      [:img {:src "./images/icon-coins.png"}]]

     [:div.links-section
      [:div.listing
       [:span.title "Learn More"]
       [:a {:href "#"} "About Us"]
       [:a {:href "#"} "How it Works"]
       [:a {:href "#"} "Blog"]]
      [:div.listing
       [:span.title "Get Started"]
       [:a {:href "#"} "Become a Freelancer"]
       [:a {:href "#"} "Become an Employer"]
       [:a {:href "#"} "Find Work"]
       [:a {:href "#"} "Find Candidates"]]
      [:div.listing
       [:span.title "Connect With Us"]
       [:div.button-listing
        [c-circle-icon-button {:name :twitter :size :small}]
        [c-circle-icon-button {:name :github :size :small}]
        [c-circle-icon-button {:name :slack :size :small}]
        [c-circle-icon-button {:name :facebook :size :small}]]]]
     [:div.buttons-section
      [c-button       
       {:color :primary :size :auto}
       [c-button-label [:span "Become a " [:b "Freelancer"]]]]
      [c-button
       {:color :primary :size :auto}
       [c-button-label [:span "Become an " [:b "Arbiter"]]]]
      [c-button
       {:color :primary :size :auto}
       [c-button-label [:span "Become an " [:b "Employer"]]]]]
     [:div.footer-section
      [:span "Copyright © 2019 Ethlance.com. All rights reserved."]]]]])
