(ns labyrinth-of-dreams.journal.ui.views.home
  (:require [labyrinth-of-dreams.journal.ui.common :refer [button]]
            [labyrinth-of-dreams.journal.state :as state]))

(defn home []
  [:div.home.hero
   [:h1.title "Labyrinth of Dreams"]
   (button "Write about it..." [:writer] "inverse")])