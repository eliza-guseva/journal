(ns labyrinth-of-dreams.journal.api
  (:require [learn-cljs.notes.events :refer [emit!]]
            [learn-cljs.notes.errors :as err]
            [camel-snake-kebab.core :as csk]
[camel-snake-kebab.extras :as cske]))

(defn- display-error [err]
  (emit! :notification/added
         {:type :error
          :text (str "API Error: " (ex-message err))}))

(defn- with-error-handling [f]
  (fn [res]
    (->> res
         (err/map f)
         (err/unwrap-or display-error))))

(defn do-request!
  ([method path cb] (do-request! method path nil cb))
  ([method path body cb]
   (let [serialized-body (when body
                           (->> body
                                (cske/transform-keys csk/->camelCaseString)
                                (clj->js)
                                (js/JSON.stringify)))]
     (-> (js/fetch (str js/API_URL path)
                   (cond-> {:method (name method)
                            :headers {"Authorization" (str "Bearer " js/API_TOKEN)}
                            :credentials "include"}
                     (some? body)
                     (->
                      (assoc :body serialized-body)
                      (update :headers merge {"content-type" "application/json"}))

                     :always
                     clj->js))
         (.then (fn [res]
                  (if (.-ok res)
                    (when (= 200 (.-status res))
                      (.json res))
                    (throw (ex-info "API Request Failed"
                                    {:status-code (.-status res)
                                     :status (.-statusText res)}
                                    :api-failure)))))
         (.then #(->> %
                      (js->clj)
                      (cske/transform-keys csk/->kebab-case-keyword)
                      (err/ok)
                      (cb)))
         (.catch #(cb (err/error %)))))))

(defn create-note! [note]
  (js/console.log 
   (str "make believe creating note!"
        "make believe note id:" (rand-int 1000)
        (pr-str note))))

(defn update-note! [note]
    (js/console.log 
     (str "make believe updating note!"
            (pr-str note))))

(defn get-notes! []
  (do-request! :get "/notes"
               (with-error-handling #(emit! :notes/received %))))