(ns labyrinth-of-dreams.journal.command
    (:require [labyrinth-of-dreams.journal.events :refer [emit!]]
              [labyrinth-of-dreams.journal.routes :as routes]
              [labyrinth-of-dreams.journal.api :as api]))

(defn handle-test-hello! [name]
  (println "Hello" name)                                   
  (emit! :test/greeting-dispatched {:name name}))       

(defn handle-navigate! [route-params]
  (routes/navigate! route-params))

(defn handle-create-note! [note]
  (api/create-note! note))

(defn handle-add-notification! [notification]
  (emit! :notification/added notification))

(defn handle-remove-notification! [id]
  (emit! :notification/removed id))

(defn dispatch!
  ([command] (dispatch! command nil))
  ([command payload]
   (js/setTimeout                                         
     #(case command
        :test/hello (handle-test-hello! payload)
        :route/navigate (handle-navigate! payload)
        :notes/create (handle-create-note! payload)

        :notification/add (handle-add-notification! payload)
        :notification/remove (handle-remove-notification! payload)

        (js/console.error (str "Error: unhandled command: " command)))
     0))
)
  