(ns fflowsite.routes.home
  (:require [fflowsite.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]))

(defn home-page []
  (layout/render
    "home.html" {:docs     (-> "docs/docs.md" io/resource slurp)
                 :features [{:title "ES6 JavaScript"
                             :desc  "Just use your shiny JavaScript skill\nwith learning powerful ES6 features"
                             :img   "url(/img/feature_javascript.png)"
                             }
                            {:title "Coding Tools"
                             :desc  "Instant watch, built-in REPL\nsupport and many, many \nmodern editing features are\nwaiting for you"
                             :img   "url(/img/feature_code.png)"
                             }
                            {:title "Design Tools"
                             :desc  "Layer hierarchy, state simulation\nand inspection tools are give you\nbetter idea how things work"
                             :img   "url(/img/feature_design.png)"
                             }
                            {:title "Share"
                             :desc  "Mirroring, sharing and import/\nexport for better communication"
                             :img   "url(/img/feature_share.png)"
                             }
                            ]}))

(defn docs-page []
  (layout/render
    "docs.html" {:docs     (-> "docs/docs.md" io/resource slurp)
                 :features [{:title "ES6 JavaScript"
                             :desc  "Just use your shiny JavaScript skill\nwith learning powerful ES6 features"
                             :img   "url(/img/feature_javascript.png)"
                             }
                            {:title "Coding Tools"
                             :desc  "Instant watch, built-in REPL\nsupport and many, many \nmodern editing features are\nwaiting for you"
                             :img   "url(/img/feature_code.png)"
                             }
                            {:title "Design Tools"
                             :desc  "Layer hierarchy, state simulation\nand inspection tools are give you\nbetter idea how things work"
                             :img   "url(/img/feature_design.png)"
                             }
                            {:title "Share"
                             :desc  "Mirroring, sharing and import/\nexport for better communication"
                             :img   "url(/img/feature_share.png)"
                             }
                            ]}))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
           (GET "/" [] (home-page))
           (GET "/docs" [] (docs-page))
           (GET "/about" [] (about-page)))

