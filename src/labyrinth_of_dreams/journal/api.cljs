(ns labyrinth-of-dreams.journal.api)

(defn create-note! [note]
  (js/console.log 
   (str "make believe creating note!"
        "make believe note id:" (rand-int 1000)
        (pr-str note))))

(defn update-note! [note]
    (js/console.log 
     (str "make believe updating note!"
            (pr-str note))))