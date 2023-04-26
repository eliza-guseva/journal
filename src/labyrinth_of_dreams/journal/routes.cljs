(ns labyrinth-of-dreams.journal.routes
  (:require [labyrinth-of-dreams.journal.events :refer [emit!]]
            [bide.core :as bide]))

(defonce router 
  (bide/router [["/" :home]
                ["/journal/new" :writer]
                ["/journal/entry-id" :edit-entry]]))

(defn get-url [route-params]
  (str "#"
       (apply bide/resolve router route-params)))

(defn navigate! [route-params]
  (apply bide/navigate! router route-params))

(defn on-navigate [name params query]
  (emit! :route/navigated [name params query]))

(defn initialize! []
  (bide/start! router {:default :routes/writer
                       :on-navigate on-navigate}))

