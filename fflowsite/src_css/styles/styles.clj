(ns styles.styles
  (:refer-clojure :exclude [rem])
  (:require [garden.def :refer [defstylesheet defstyles]]
            [garden.units :refer [px rem em percent]]
            [mesh.grid :as grid]
            [mesh.typography :as typo]

            [clojure.pprint :refer [pprint]]

            )

  (:use [styles.base])
  )


(def gutter (px 28))
(def wrap-width 1170)
(def grids
  (list (grid/create ".grid" gutter)
        (grid/wrap-widths 1170)
        (grid/create-nested-units)
        (grid/nuke-gutters-and-padding)
        ))



(def box-shadow-params [[0 (px 8) (px 30) (px 1) "rgba(20,20,20,0.25)"]])

(def ms
  (let [f (typo/modular-scale-fn 16 (:golden typo/scales))]
    (fn [n]
      (px (f n)))
    ))

(def sans ["\"Open Sans\"" "Avenir" "Helvetica" "sans-serif"])
(def serif ["\"David Libre\"" "serif"])
(def monospace ["\"Source Code Pro\"" "monospace"])

(def color-fg-500 "#585858")
(def color-fg-700 "#888888")
(def color-fg-900 "#f8f8f8")

(def color-primary-50 "#E3F2FD")
(def color-primary-200 "#90CAF9")
(def color-primary-500 "#2962ff")

(def sizes
  (into
    [[:.u-sizeFull {:width (percent 100)}]]
    (mapcat (fn [ncols]
              (mapcat (fn [idx]
                        [[(keyword (format ".u-size%sof%s"
                                           idx
                                           ncols))
                          {:width (percent (* idx (/ 100 ncols)))}]
                         [(keyword (format ".u-before%sof%s"
                                           idx ncols))
                          {:padding-left (percent (* idx (/ 100 ncols)))}]
                         [(keyword (format ".u-after%sof%s"
                                           idx ncols))
                          {:padding-right (percent (* idx (/ 100 ncols)))}]]
                        )
                      (range 1 (inc ncols))))
            [2 3 4 6 8 12 24]
            ))
  )
(defn u-size
  [k]
  (->> sizes
       (filter #(= (first %) k))
       first
       last)
  )

(def utils
  [
   [:.u-container {:max-width (px wrap-width)
                   :margin    [[0 :auto]]
                   }]

   [:.u-textAlignCenter {:text-align :center}]

   [:.u-flexEmbed FlexEmbed]

   [:.u-flexEmbedRatio (FlexEmbed-ratio 1 1)]
   (mapv (fn [[width-ratio height-ratio]]
           [(keyword (format ".u-flexEmbedRatio--%sby%s"
                             width-ratio height-ratio)
                     )
            (FlexEmbed-ratio width-ratio height-ratio)])
         [[3 1]
          [2 1]
          [16 9]
          [4 3]
          ])
   [:.u-flexEmbedContent FlexEmbed-content]
   [:.u-flex1 (Flex 1)]


   [:.u-sectionTitle {:padding     [[(ms 1) 0]]
                      :line-height (ms 2.5)
                      :font-size   (ms 2)
                      :font-weight :bold

                      }]
   (->> (range 1 12)
        (mapcat (fn [v]
                  [[(keyword (format ".u-marginTop--%sms" v))
                    {:margin-top (ms v)}
                    ]
                   [(keyword (format ".u-marginBottom--%sms" v))
                    {:margin-bottom (ms v)}]
                   ])))
   ]
  )

(defstyles
  typography

  [
   [:body {:font-family sans}]

   [:h1 {:padding     [[0 (ms 2)]]
         :font-size   (ms 5)
         :line-height (ms 5)}]
   [:h2 {:padding     [[0 (ms 1)]]
         :font-size   (ms 4)
         :line-height (ms 4)}]
   [:h3 {:padding     [[0 (ms 1)]]
         :font-size   (ms 3)
         :line-height (ms 3)}]

   [:h4 {:padding     [[0 (ms 1)]]
         :font-size   (ms 2)
         :line-height (ms 2)}]

   [:h5 {:padding     [[0 (ms 1)]]
         :font-size   (ms 1)
         :line-height (ms 1)}]

   [:h6 {:padding     [[0 (ms 1)]]
         :font-size   (ms 0)
         :line-height (ms 0)}]

   [:p.large {:padding     [[0 (ms 1)]]
              :font-size   (ms -1)
              :line-height (ms -1)}]

   [:p.medium {:padding     [[0 (ms 1)]]
               :font-size   (ms -2)
               :line-height (ms -2)}]

   [:p.small {:padding     [[0 (ms 1)]]
              :font-size   (ms -3)
              :line-height (ms -3)
              }]
   ]

  )


(def ButtonProps {:display         :inline-block
                  :padding         [[(ms 0.25) (ms 1)]]
                  :border-radius   (ms 2)
                  :font-weight     :bold
                  :font-size       (ms -0.25)
                  :text-decoration :none
                  :letter-spacing  (px 1)
                  :cursor          :pointer
                  })
(def Button
  [
   [:.Button ButtonProps
    :.Button--blue {:background-color color-primary-500
                    :color            :white
                    }
    ]])

(def LinkButton
  [
   [:.LinkButton {:display         :inline-block
                  :text-decoration :none
                  :color           color-fg-500}]
   ])

(def Nav
  [
   [:.Nav (++ (Flexbox)
              (AlignItems :center)
              (Fg color-fg-500)
              {:height (rem 6.3)}
              )]
   [:.Nav-title {:text-transform :uppercase
                 :letter-spacing (px 3)
                 :font-size      (ms 0)}]
   [:.Nav-logo (++ (Flexbox)
                   (Flex 1)
                   (AlignItems :center)
                   {:font-weight 800})]
   [:.Nav-link {:padding-left (ms 1)}]
   ])


(def Hero
  [
   [:.Hero (++ (Flexbox)
               (FlexboxDirection :column)
               (AlignItems :center))]
   [:.Hero-title {:font-weight   :bold
                  :margin-top    (ms 3)
                  :margin-bottom (ms 1)}]
   [:.Hero-loveIcon {:color   :blue
                     :padding [[0 (ms 0.25)]]}]
   [:.Hero-desc {:font-family serif
                 :font-size   (ms 0.5)
                 :color       color-fg-700
                 :padding-top (ms 1)
                 }]
   [:.Hero-descPoint {:color       color-primary-500
                      :font-weight :bold}]

   [:.Hero-image (++ (Flexbox)
                     (FlexboxDirection :column)
                     (AlignItems :center)
                     {:padding [[(ms 4) 0]]
                      })]
   [:.Hero-imageLayoutRoot {
                            :box-shadow box-shadow-params
                            }]
   [:.Hero-download {:padding        [[(ms 2) 0]]
                     :padding-bottom (ms 4)}]

   ])


(def Features
  [
   [:.Features {:padding          [[(ms 4) 0]]
                :background-color color-fg-900}]

   [:.Features-layoutRoot (++ (Flexbox)
                              (FlexboxDirection :column)
                              (AlignItems :center))]

   [:.Features-desc {:padding     [[(ms 1) 0]]
                     :font-size   (ms 1)
                     :font-family serif
                     }]
   [:.Features-items (++ (Flexbox)
                         )]
   [:.Features-learnMore {:padding        [[(ms 3) 0]]
                          :padding-bottom (ms 1)}]
   ])

(def FeatureItem
  [
   [:.FeatureItem (++ (Flexbox)
                      (FlexboxDirection :column)
                      (AlignItems :center)

                      {:padding [[0 (ms 0.5)]]}
                      )]
   [:.FeatureItem-title {:padding   [[(ms 1) 0]]
                         :font-size (ms 1)}]
   [:.FeatureItem-desc {
                        :padding     [[0 (ms 0.5) (ms 1) (ms 0.5)]]
                        :font-family serif
                        :font-size   (ms 0)
                        :line-height (ms 1)
                        :text-align  :center
                        }]
   [:.FeatureItem-photoImageContent {:background-size :contain
                                     }]

   ])

(def Promotion
  [
   [:.Promotion {}]
   [:.Promotion-layoutRoot (++ (Flexbox)
                               )]
   [:.Promotion-verbiage (++ (Flexbox)
                             (FlexboxDirection :column)
                             (JustifyContent :center))]
   [:.Promotion-photo {:background-image    "url(../img/promotion_bg.jpg)"
                       :background-size     :cover
                       :background-repeat-x :no-repeat
                       :background-repeat-y :no-repeat
                       :min-height          (px 520)
                       :box-shadow          box-shadow-params
                       }]
   [:.Promotion-verbiage [:p {:padding     [[(rem 0.75) 0]]
                              :line-height 1.5
                              :font-family serif
                              :font-size   (ms 0)}
                          ]]
   [:.Promotion-download {:padding [[(ms 1) 0]]}]

   ])


(def Footer
  [
   [:.Footer {:background-color color-fg-900
              :padding          [[(ms 2) 0]]
              :font-size        (ms -0.5)
              }]
   [:.Footer-layoutRoot (++ (Flexbox)
                            (AlignItems :center)
                            )]
   [:.Footer-link {:padding-left (ms 1)}]
   ])

(def Docs
  [
   [:.Docs (++ (Flexbox))]
   [:.Docs-layoutRoot (++ (Flexbox)
                          {:position :relative})]
   [:.Docs-menuBar (++ {:position :relative
                        :width    "25% !important"})]
   [:.Docs-menuBar.menubar.fixed {:position   :fixed
                                  :width      "100% !important"
                                  :overflow-y :hidden
                                  :left       0
                                  :right      0
                                  :top        0
                                  :bottom     0
                                  }
    [:.u-container {:height   :100vh
                    :position :relative}]
    [:.Docs-menu (++ (u-size :.u-size1of4)
                     {:height       :100vh
                      :margin-right (percent 75)
                      :overflow-y   :auto}
                     )
     ["&::-webkit-scrollbar" {:width  (rem 0.5)
                              :height (rem 0.5)}]]
    ]

   [:.Docs-menu [:li {:font-size   (rem 0.9)
                      :line-height 1.25}]]
   [:.Docs-menu [:a
                 :a:visited {:text-decoration :none
                             :color           color-primary-500}
                 ]
    ]
   [:.Docs-menu [:> [:ul {:padding-bottom "25vh"}]]]

   [:.Docs-menu
    [:li.level-2 {:margin-bottom (rem 0.5)}]
    [:a {:position    :relative
         :line-height 1.5}]
    [:a.level-1 {:font-weight :bold
                 :display     :inline-block
                 :margin      [[(rem 2) 0 (rem 1) 0]]}]
    [:a.level-2]
    [:a.level-3 {:padding-left (rem 1.5)}]

    #_[:a.active {:color "#3F51B5"}]
    [:a.active:after {:content       "\"\""
                      :position      :absolute
                      :left          0
                      :right         0
                      :bottom        (px -2)
                      :border-bottom [[(px 2) :solid color-primary-500]]}]
    [:a.level-3.active:after {:left (rem 1.5)}]
    ]


   [:.Docs-content {:position :relative}]
   [".Docs-menuBar.menubar.fixed + .Docs-content" {:margin-left (percent 25)}]

   [:.Docs-content [:h1:first-child {:padding-top (ms 0)}]]
   [:.Docs-content
    [:h1 {:font-size   (ms 2)
          :font-weight :bold
          :line-height 2
          :padding     [[(ms 5) 0 (ms 1)]]}]
    [:h2 {:font-size   (ms 1.25)
          :font-weight :bold
          :line-height 2
          :padding     [[(ms 2) 0 (ms 0.5) 0]]
          }]

    [:h1+h2 {:padding-top 0}]

    [:h3 {:font-size   (ms 0.25)
          :line-height 3
          :padding     0
          :font-weight :bold
          }]

    [:h4 {:font-size      (ms 0)
          :line-height    2
          :padding        [[(ms 0) 0]]
          :font-weight    :bold
          :text-transform :uppercase
          :color          color-fg-500
          }
     ]

    [:p {:font-size   (ms 0)
         :line-height 1.5
         }]
    [:p+p {:margin-top (ms 1)}]
    [:p+ul
     :p+ol {:margin [[(ms 1) (rem 2)]
                     ]}]]

   [:.Docs-content [:ul [:> [:li {:list-style-type :disc}]]]]
   [:.Docs-content [:ol [:> [:li {:list-style-type :decimal}]]]]
   [:.Docs-content [:ul :ol {:line-height 1.5}]]
   [:.Docs-content [:ul :ol [:li {:padding-left (ms 1)
                                  :line-height  (ms 1.25)}]]]

   [:.Docs-content [:a.button (++ ButtonProps
                                  {:background-color color-primary-500
                                   :color            :white
                                   :padding          [[(rem 0.5) (rem 1)]]
                                   :margin           [[(rem 1) 0 (rem 1.5) 0]]})
                    ]]


   ;; Give some huge space when image + i happened.
   [:.Docs-content [:img+i {:min-height (rem 5)
                            :display    :block}]]


   [:.Docs-content [:blockquote {:position :relative
                                 :padding  (rem 1)
                                 :margin   [[(ms 0) 0]]
                                 :color    color-fg-500}]
    [:blockquote:before {:content     "\"\""
                         :position    :absolute
                         :left        0
                         :top         0
                         :bottom      0
                         :border-left [[(px 4) :solid color-primary-200]]}]]

   [:.Docs-content [:pre {:background-color color-primary-50
                          :border           [[(px 1) :solid color-primary-200]]
                          :padding          (ms 1)
                          :margin           [[(ms 0) 0]]
                          :font-family      monospace
                          :font-size        (ms 0)
                          }]]
   (let [inline-code {:font-family      monospace
                      :background-color color-primary-50
                      :border           [[(px 1) :solid color-primary-200]]
                      :padding          [[0 (rem 0.5)]]
                      }]
     [
      [:.Docs-content [:p [:> [:code inline-code]]]]
      [:.Docs-content [:li [:code inline-code]]]
      ])
   ])


(def About
  [:.About
   :.Contact
   [
    [:h4
     :h5 {:padding-left  0
          :padding-right 0}]
    [:h5 {:margin-top    (ms 1)
          :margin-bottom (ms 0)
          }]
    [:p {:line-height 1.5}]
    [:section {:margin-top (ms 1.25)}]
    ]])


(defstyles
  screen
  [
   typography
   grids
   utils
   sizes

   Button
   LinkButton

   Nav
   Hero
   Features
   FeatureItem
   Promotion
   Footer

   Docs
   About
   ]
  )






