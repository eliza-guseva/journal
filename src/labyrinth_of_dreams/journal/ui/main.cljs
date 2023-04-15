(ns labyrinth-of-dreams.journal.ui.main
  (:require [reagent.core :as r]
            [labyrinth-of-dreams.journal.state :as state]
            [labyrinth-of-dreams.journal.ui.views.home :refer [home]]
            [labyrinth-of-dreams.journal.ui.views.writer :refer [writer]]))

(defn not-found []
  [:section.hero
   [:h1.title "Not Found"]])

(defn main[]
  (let [[route params query ] (:current-route @state/app)]
    [:div.main
     (case route
       :home [home]
       :writer [writer]
       [not-found])]))
