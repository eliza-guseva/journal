(ns labyrinth-of-dreams.journal.ui.main
  (:require [reagent.core :as r]))

(def click-count (r/atom 0))

(defn counting-component []
   [:textarea.main-textarea {:placeholder "Write about it"}])

(defn main[]
  [:div.main
   [counting-component]])
