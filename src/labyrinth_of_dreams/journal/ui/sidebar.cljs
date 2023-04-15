(ns labyrinth-of-dreams.journal.ui.sidebar
  (:require [labyrinth-of-dreams.journal.ui.common :refer [button]]))

(defn sidebar []
  [:nav.sidebar
   [button "Write about it"
    {:route-params [:create-note]                        
     :class "inverse"}
    ]])

