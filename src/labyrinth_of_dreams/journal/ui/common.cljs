(ns labyrinth-of-dreams.journal.ui.common
  (:require [labyrinth-of-dreams.journal.command :refer [dispatch!]]))

(defn handle-navigate [route-params]
  (fn [_]
    (dispatch! :route/navigate route-params)))

(defn button [text route-params {:keys [class]
                                 :or {class ""}}]
  [:button {:class (str "button " class)
            :on-click (handle-navigate route-params)}
   text])