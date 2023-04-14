(ns labyrinth-of-dreams.journal.ui.main
  (:require [reagent.core :as r]))

(def click-count (r/atom 0))

(defn counting-component []
  [:div
   [:input {:type "text"}]])

(defn main[]
  [:div.main
   [counting-component]])
