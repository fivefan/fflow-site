(ns user
  (:require [mount.core :as mount]
            fflowsite.core))

(defn start []
  (mount/start-without #'fflowsite.core/repl-server))

(defn stop []
  (mount/stop-except #'fflowsite.core/repl-server))

(defn restart []
  (stop)
  (start))


