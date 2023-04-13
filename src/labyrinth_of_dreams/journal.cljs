(ns labyrinth-of-dreams.journal
  (:require ["react-dom/client" :refer [createRoot]]
            [goog.dom :as gdom]
            [reagent.core :as r]))

(def click-count (r/atom 0))

(defn counting-component []
  [:div
   "The atom " [:code "click-count"] " has value: "
   @click-count ". "
   [:input {:type "button" :value "Click me!"
            :on-click #(swap! click-count inc)}]])

(defn app []
  [:main.container.mx-auto
   [:h1 "Hello, World!"]
   [:p "This is a Reagent app."]
   [counting-component]])


(defonce root (createRoot (gdom/getElement "app")))

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