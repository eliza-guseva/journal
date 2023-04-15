(ns labyrinth-of-dreams.journal.events)

(def listeners (atom []))                                  ;; <1>

(defn emit!                                                ;; <2>
  ([type] (emit! type nil))
  ([type payload]
   (doseq [listen-fn @listeners]
     (listen-fn type payload))))

(defn register-listener! [listen-fn]                       ;; <3>
  (swap! listeners conj listen-fn))