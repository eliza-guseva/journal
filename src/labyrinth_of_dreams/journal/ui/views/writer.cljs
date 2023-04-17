(ns labyrinth-of-dreams.journal.ui.views.writer
  (:require [reagent.core :as r]
            [labyrinth-of-dreams.journal.state :refer [app]]
            [labyrinth-of-dreams.journal.ui.common :refer [button]]))

(defn update-data [data key]
  (fn [e]
    (swap! data assoc key (.. e -target -value))))

(defn input [data key label]
  (let [id (str "field-" (name key))]
    [:div.field
     [:div.control
      [:input.any-text {:id id
               :type "text"
               :on-change #(swap! data assoc key (.. % -target -value))
               :value (get @data key "")}]]]))

(defn textarea [data key label]
  (let [id (str "field-" (name key))]
    [:div.field
     [:div.label
      [:label {:for id} label]]
     [:div.control
  [:textarea.main-textarea 
   {:id id
    :on-change (update-data data key)
    :value (get @data key "")
    :placeholder "Here..."}
   ]
      ]]))

(defn is-new? [data]
  (js/console.log (pr-str data))
  (-> data :id nil?))

(defn submit-button [data]
  (let [[action text] (if (is-new? @data)
                        [:notes/create "Create"]
                        [:notes/update "Save"])]
    [button text {:dispatch [action @data]}]))

(defn writer []
  (let [form-data (r/cursor app [:entry-form])]
     [:form.entry-form
      (input form-data :title "Title")
      (textarea form-data :content "")
      (submit-button form-data)]))
