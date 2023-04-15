(ns labyrinth-of-dreams.journal.command
    (:require [learn-cljs.notes.events :refer [emit!]]))

(defn handle-test-hello! [name]
  (println "Hello" name)                                   ;; <1>
  (emit! :test/greeting-dispatched {:name name}))          ;; <2>

(defn dispatch!
  ([command] (dispatch! command nil))
  ([command payload]
   (js/setTimeout                                          ;; <3>
     #(case command
        :test/hello (handle-test-hello! payload)

        (js/console.error (str "Error: unhandled command: " command)))
     0))
)