(ns fflowsite.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [fflowsite.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[fflowsite started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[fflowsite has shut down successfully]=-"))
   :middleware wrap-dev})
