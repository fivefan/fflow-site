(ns fflowsite.routes.home
  (:require [fflowsite.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :as response]
            [fflowsite.config :refer [env]]
            [clojure.java.io :as io]))

(defn test-page []
  (layout/render
    "test.html"
    {}))

(defn home-page []
  (layout/render
    "home.html" {:title    "FramerFlow"
                 :dev      (:dev env)
                 :docs     (-> "docs/docs.md" io/resource slurp)
                 :features [{:title "ES6 JavaScript"
                             :desc  "Just use your shiny JavaScript skill\nwith learning powerful <a href='/docs#user-guide-coding-tools-es6-overview'>ES6 features</a>"
                             :img   "url(/img/feature_javascript.png)"
                             }
                            {:title "Coding Tools"
                             :desc  "<a href='/docs#user-guide-coding-tools-instant-watch'>Instant watch</a>, built-in <a href='/docs#user-guide-coding-tools-repl-and-console'>REPL</a>\nsupport and \n<a href='/docs#user-guide-editing-tools'>some modern editing features</a> are\nwaiting for you"
                             :img   "url(/img/feature_code.png)"
                             }
                            {:title "Design Tools"
                             :desc  "<a href='/docs#user-guide-design-tools-layer-inspection'>Layer hierarchy</a>, <a href='/docs#user-guide-design-tools-states'>state simulation</a>\nand inspection tools are give you\nbetter idea how the design stuffs work under the hood."
                             :img   "url(/img/feature_design.png)"
                             }
                            {:title "Share"
                             :desc  "<a href='/docs#user-guide-publishing-tools-mirroring'>Mirroring</a>, <a href='/docs#user-guide-publishing-tools-sharing'>sharing</a> and import/\nexport for better communication"
                             :img   "url(/img/feature_share.png)"
                             }
                            ]}))

(defn docs-page []
  (layout/render
    "docs.html" {:title     "FramerFlow - Documentation"
                 :no-footer true
                 :dev       (:dev env)
                 :readmeuri (:readmeuri env)
                 }))
(defn contact-page []
  (layout/render
    "contact.html" {:title "FramerFlow - Contact"
                    :dev   (:dev env)
                    }))

(defn about-page []
  (layout/render "about.html" {:title "FramerFlow - About"
                               :dev   (:dev env)
                               }))

(defroutes home-routes
           (GET "/" [] (home-page))
           (GET "/test" [] (test-page))
           (GET "/docs" [] (docs-page))
           (GET "/contact" [] (contact-page))
           (GET "/about" [] (about-page)))

