(require
  '[figwheel-sidecar.components.figwheel-server :as server]
  '[figwheel-sidecar.components.file-system-watcher :as fsw]
  '[figwheel-sidecar.system :as sys]
  '[figwheel-sidecar.utils :as utils]
  '[com.stuartsierra.component :as component])

(def figwheel-config
  {:figwheel-options {:server-port 6503
                      :css-dirs    ["resources/public/css"]
                      }
   :build-ids        ["dev"]
   :all-builds       [{:id           "dev"
                       :figwheel     true
                       :source-paths ["src/cljs"]
                       :compiler     {:main                 "sample.dev"
                                      :asset-path           "js/compiled/out"
                                      :output-to            "resources/public/js/compiled/sample.js"
                                      :output-dir           "resources/public/js/compiled/out"
                                      :source-map-timestamp true
                                      :verbose              true}}]})

(defn make-file [path]
  {:file (utils/remove-root-path path)
   :type :html})

(defn send-files [figwheel-server files]
  (server/send-message figwheel-server
                       ::server/broadcast
                       {:msg-name :html-files-changed
                        :files    files}))

(defn handle-notification [watcher files]
  (when-let [changed-files (not-empty (filter #(.endsWith % ".html") (map str files)))]
    (let [figwheel-server (:figwheel-server watcher)
          sendable-files (map #(make-file %) changed-files)]
      (send-files figwheel-server sendable-files)
      (doseq [f sendable-files]
        (println "sending changed HTML file:" (:file f))))))

(def system
  (atom
    (-> (sys/create-figwheel-system figwheel-config)
        (assoc :html-watcher
               (component/using
                 (fsw/file-system-watcher {:watcher-name         "HTML watcher"
                                           :watch-paths          ["resources/public" "resources/templates"]
                                           :notification-handler handle-notification})
                 {:figwheel-server :figwheel-system}))
        )))

(defn start []
  (swap! system component/start))

(defn stop []
  (swap! system component/stop))

(defn reload []
  (stop)
  (start))

(defn repl []
  (sys/cljs-repl (:figwheel-system @system)))

;; Start the components and the repl
(start)
(repl)
