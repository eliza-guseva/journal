(ns labyrinth-of-dreams.journal.ui.sidebar)

(defn sidebar []
  [:nav.sidebar
   [:button
    {:route-params [:create-note]                         ;; <1>
     :class "inverse"}
    "+ New Entry"]])

