(ns labyrinth-of-dreams.journal
  (:require ["react-dom/client" :refer [createRoot]]
            [goog.dom :as gdom]
            [reagent.core :as r]
            [labyrinth-of-dreams.journal.ui.header :refer [header]]
            [labyrinth-of-dreams.journal.ui.main :refer [main]]
            [labyrinth-of-dreams.journal.ui.sidebar :refer [sidebar]]
            [labyrinth-of-dreams.journal.ui.footer :refer [footer]]
            [labyrinth-of-dreams.journal.routes :as routes]
            [labyrinth-of-dreams.journal.event-handlers.core]))



(defn app []
  [:main.app
   [:div  [header]]
   [:div.body-div 
    [sidebar] [main]
    ]
   [:div [footer]]])


(defonce root (createRoot (gdom/getElement "app")))

(defonce initialized? 
  (do 
    (routes/initialize!)
    true))

(defn init
  []
  (.render root (r/as-element [app])))

(defn reload []
  (init))

(defn ^:dev/after-load re-render
  []
  ;; The `:dev/after-load` metadata causes this function to be called
  ;; after shadow-cljs hot-reloads code.
  ;; This function is called implicitly by its annotation.
  (init))
