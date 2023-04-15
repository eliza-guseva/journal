(ns labyrinth-of-dreams.journal.routes
  (:require [labyrinth-of-dreams.journal.events :refer [emit!]]
            [bide.core :as bide]))

(defonce router 
  (bide/router [["/" :home]
                ["/journal/new" :writer]
                ["/journal/:entry-id" :edit-entry]]))

(defn navigate! [route-params]
  (apply bide/navigate! router route-params))

(defn on-navigate [name params query]
  (emit! :route/navigated [name params query]))

(defn initialize! []
  (bide/start! router {:default :routes/home
                       :on-navigate on-navigate}))

