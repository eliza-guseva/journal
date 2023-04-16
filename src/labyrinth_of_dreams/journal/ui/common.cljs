(ns labyrinth-of-dreams.journal.ui.common
  (:require [labyrinth-of-dreams.journal.command :refer [dispatch!]]))

(defn handle-navigate [route-params]
  (fn [_]
    (dispatch! :route/navigate route-params)))

(defn handle-dispatch [command-data]
  (fn [e]
    (.preventDefault e)
    (apply dispatch! command-data)))

(defn button [text & {:keys [route-params dispatch on-click class]
                    :or {class ""
                         route-params nil
                         dispatch nil
                         on-click identity
                         }}]
  [:button
   {:class (str "button " class)
    :on-click (
               (cond
                route-params (handle-navigate route-params)
                dispatch (handle-dispatch dispatch)
                on-click on-click
                :else #(js/console.error "No action provided for button")))}
   text])
