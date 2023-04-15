(ns labyrinth-of-dreams.journal.state
  (:require [reagent.core :as r]
            [labyrinth-of-dreams.journal.events :as events]))

(def initial-state
  {:current-route [:writer]
   :notifications {:messages []
                   :next-id 0}
   :data {:notes {}
          :tags {}}})

(defonce app (r/atom initial-state))

(def handlers (atom {}))

(defn register-handler! [event-type handler-fn]
  (swap! handlers assoc event-type handler-fn))

(events/register-listener! 
 (fn [event-type payload]
   (when-let [handler-fn (get @handlers event-type)]
     (swap! app #(handler-fn % payload)))))
