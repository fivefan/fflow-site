(ns fflowsite.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[fflowsite started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[fflowsite has shut down successfully]=-"))
   :middleware identity})
