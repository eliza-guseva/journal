(ns labyrinth-of-dreams.journal.command
    (:require [labyrinth-of-dreams.journal.events :refer [emit!]]
              [labyrinth-of-dreams.journal.routes :as routes]))

(defn handle-test-hello! [name]
  (println "Hello" name)                                   
  (emit! :test/greeting-dispatched {:name name}))       

(defn handle-navigate! [route-params]
  (routes/navigate! route-params))

(defn dispatch!
  ([command] (dispatch! command nil))
  ([command payload]
   (js/setTimeout                                         
     #(case command
        :test/hello (handle-test-hello! payload)
        :route/navigate (handle-navigate! payload)

        (js/console.error (str "Error: unhandled command: " command)))
     0))
)
  