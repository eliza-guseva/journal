(ns labyrinth-of-dreams.journal.event-handlers.routes
  (:require [labyrinth-of-dreams.journal.state :refer [register-handler!]]))

(register-handler! 
 :route/navigated
 (fn [db route-params]
   (assoc db :current-route route-params)))
