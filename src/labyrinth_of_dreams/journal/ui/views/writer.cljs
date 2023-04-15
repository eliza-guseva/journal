(ns labyrinth-of-dreams.journal.ui.views.writer
  (:require [reagent.core :as r]
            [labyrinth-of-dreams.journal.state :refer [app]]
            [labyrinth-of-dreams.journal.ui.common :refer [button]]))

(defn writer []
  [:div.writer
  [:textarea.main-textarea {:placeholder "Here..."}]])
