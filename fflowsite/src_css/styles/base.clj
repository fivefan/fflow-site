(ns styles.base
  (:refer-clojure :exclude [rem])
  (:require
    [garden.def :refer [defstylesheet defstyles]]
    [garden.units :as u :refer [px rem em percent ms s]]
    [garden.color :as c :refer [hsl rgb darken lighten mix rgba]]
    [garden.util :refer [space-join]]
    [garden.arithmetic :as ar]

    [clojure.core.match :refer [match]]
    [clojure.string :as string :refer [capitalize]]
    )
  )

(def ++ clojure.core/merge)

(defmacro gen-unit-shortcut
  [unit unit-func start end step]
  (let [vals (range start end step)]
    `(do ~@(map (fn [v]
                  (if-not (symbol? v)
                    `(def ~(symbol (str (name unit) v))
                       (~unit-func ~v)
                       ))
                  )
                vals)
         ))
  )


(gen-unit-shortcut px
                   garden.units/px
                   0 25 1
                   )
(gen-unit-shortcut pct
                   garden.units/percent
                   0 100 50
                   )


(defn ->camel-case [^String method-name]
  (->> (string/split method-name #"-")
       (map capitalize)
       (string/join)
       )
  )

(defmacro gen-4params
  [names]
  `(do ~@(map (fn [n]
                `(defn ~(symbol (->camel-case (name n)))
                   [a1# a2# a3# a4#]
                   {~n [[a1# a2# a3# a4#]]}
                   ))
              names))
  )

#_(gen-4params [:box-shadow])


(defmacro gen-5params
  [names]
  `(do ~@(map (fn [n]
                `(defn ~(symbol (->camel-case (name n)))
                   [a1# a2# a3# a4# a5#]
                   {~n [[a1# a2# a3# a4# a5#]]}
                   ))
              names))
  )




(defmacro gen-padding-like-props
  [names]
  `(do ~@(map (fn [n]
                `(defn ~(symbol (->camel-case (name n)))
                   ([v#]
                     {~n v#})
                   ([v# h#]
                     {~n [[v# h#]]})
                   ([t# r# b# l#]
                     {~n [[t# r# b# l#]]})
                   )
                )
              names)))

(gen-padding-like-props [:padding
                         :margin
                         :border-radius
                         ])


(defmacro gen-simple-value
  [names]
  `(do ~@(map (fn [n]
                `(defn ~(symbol (->camel-case (name n)))
                   ([v#]
                     {~n v#})
                   )
                )
              names)))
(gen-simple-value [:padding-right
                   :padding-top
                   :padding-left
                   :padding-bottom
                   :margin-left
                   :margin-right
                   :margin-top
                   :margin-bottom



                   :font
                   :font-size
                   :font-weight
                   :font-family
                   :cursor
                   :pointer-events
                   :opacity
                   :display

                   :position

                   :text-decoration
                   :text-transform

                   :letter-spacing
                   :border-color

                   :top
                   :bottom
                   :right
                   :bottom

                   :width
                   :height
                   :overflow
                   :max-width
                   :min-width
                   :max-height
                   :min-height

                   :content
                   :color
                   :background-color
                   :line-height

                   :outline
                   ])



(defmacro gen-border-like-css-func
  [names]
  `(do ~@(map (fn [n]
                `(defn ~(symbol (->camel-case (name n)))
                   ([px#]
                     {~n [[px#]]})
                   ([px# type# color#]
                     {~n [[px# type# color#]]})
                   )
                )
              names))
  )


(gen-border-like-css-func [:border
                           :border-left
                           :border-top
                           :border-right
                           :border-bottom
                           ])

(defn Bg
  [bg]
  {:background bg})
(defn Fg
  [fg]
  {:color fg})
(defn BgFg
  [bg fg]
  {:background-color bg
   :color            fg})

(defn FgBg
  [fg bg]
  {:background-color bg
   :color            fg})

(defn BoxSizing
  [value]
  {:-webkit-box-sizing value
   :-moz-box-sizing    value
   :-ms-box-sizing     value
   :box-sizing         value
   })


(defn Flexbox []
  {:display (sorted-set "-webkit-box"
                        "-webkit-flex"
                        "-moz-flex"
                        "-ms-flexbox"
                        "flex"
                        )})

(defn FlexboxInline []
  {:display (sorted-set "-webkit-inline-box"
                        "-webkit-inline-flex"
                        "-moz-inline-flex"
                        "-ms-inline-flexbox"
                        "inline-flex"
                        )})


(defn FlexWrap
  [wrap]
  {:flex-wrap wrap}
  )

(defn FixedFullscreen []
  {:position "fixed"
   :left     0
   :top      0
   :width    "100%"
   :height   "100%"
   })
(defn AbsFullscreen []
  {:position "absolute"
   :left     0
   :top      0
   :width    "100%"
   :height   "100%"
   })

(defn FixedFullStretch []
  {:position "fixed"
   :left     0
   :top      0
   :right    0
   :bottom   0
   })
(defn AbsFullStretch []
  {:position "absolute"
   :left     0
   :top      0
   :right    0
   :bottom   0
   })

(defn FixedFullStretch []
  {:position "fixed"
   :left     0
   :top      0
   :right    0
   :bottom   0
   })

(defn FlexboxDirection
  [dir]
  (match dir
         :column {:-webkit-box-direction  :normal
                  :-webkit-box-orient     :vertical
                  :-webkit-flex-direction :column
                  :-moz-flex-direction    :column
                  :-ms-flex-direction     :column
                  :flex-direction         :column}
         :row {:-webkit-box-direction  :normal
               :-webkit-box-orient     :horizontal
               :-webkit-flex-direction :row
               :-moz-flex-direction    :row
               :-ms-flex-direction     :row
               :flex-direction         :row
               }
         :row-reverse {:-webkit-box-direction  :normal
                       :-webkit-box-orient     :horizontal
                       :-webkit-flex-direction :row-reverse
                       :-moz-flex-direction    :row-reverse
                       :-ms-flex-direction     :row-reverse
                       :flex-direction         :row-reverse
                       }
         )
  )

(defn AlignItems
  [align]
  {:-webkit-box-align   align
   :-ms-flex-align      align
   :-webkit-align-items align
   :-moz-align-items    align
   :align-items         align}
  )
(defn JustifyContent
  [val]
  {:-webkit-box-pack        val
   :-ms-flex-pack           val
   :-webkit-justify-content val
   :-moz-justify-content    val
   :justify-content         val
   })

(defn Flex [n]
  {:-webkit-flex n
   :-moz-flex    n
   :flex         n})


(defn CenterCenter
  []
  (++ (Flexbox)
      (AlignItems :center)
      (JustifyContent :center)))

(defn StretchStretch
  []
  (++ (Flexbox)
      (AlignItems :stretch)
      (JustifyContent :stretch)))

(defn WebkitTapHighlightColorTransparent
  []
  {:-webkit-tap-highlight-color (rgba 0 0 0 0)})


(def ease-out-function "cubic-bezier(0.23, 1, 0.32, 1)")
(def ease-inout-function "cubic-bezier(0.445, 0.05, 0.55, 0.95)")


(defn Transition
  [duration property delay ease-function]
  (let [duration (or duration 450)
        delay (or delay 0)
        ease-function (or ease-function "linear")
        property (or property "all")
        ]
    {:-webkit-transition [[(ms duration) property (ms delay) ease-function]]}
    )
  )

(defn TransitionEaseout
  ([]
   (Transition nil nil nil ease-out-function))
  ([duration property]
   (Transition duration property nil ease-out-function))
  ([duration property delay]
   (Transition duration property delay ease-out-function))
  ([duration property delay easing-func]
   (Transition duration property delay (or easing-func
                                           ease-out-function)))
  )


(defn ++++
  [& props]
  (let [result (reduce (fn [acc prop]
                         (reduce (fn [acc [k v]]
                                   (assoc acc
                                     k
                                     (conj (or (get acc k) [])
                                           (-> v flatten vec)
                                           ))
                                   )
                                 acc
                                 prop)
                         )
                       {}
                       props)]
    result)
  )


(def dpi (atom 160))
(defn dp
  [v]
  (px (/ (* v @dpi)
         160))
  )

(defn sp
  [v]
  (dp v)
  )


(defn fade
  [c a]
  (cond (c/rgb? c) (assoc c
                     :alpha
                     (* (or (:alpha c) 1)
                        a)
                     )
        (c/hsl? c) (fade (c/hsl->rgb c) a)
        (c/hex? c) (fade (c/hex->rgb c) a)
        (number? c) (rgba (-> (mapv c/rgb-clip [c c c])
                              (conj a))
                          )
        :else (throw (ex-info (str "Can't find a right way to convert color " c) {}))
        )
  )

(comment
  (fade 1.0 0.3)
  (fade (rgb 128 128 0) 0.5)
  (fade (hsl 0 1 0.5) 0.5)
  )


(defn UserSelect
  [v]
  {:-webkit-user-select v})


(defn Transform
  [v]
  {:-webkit-transform v
   :-moz-transform    v
   :-ms-transform     v
   :transform         v
   })


(defn scalex [x] (str "scaleX(" x ")"))
(defn scaley [y] (str "scaleY(" y ")"))
(defn scale
  ([x]
   (str "scale(" x ")"))
  ([x y]
   (str "scale(" x "," y ")")))


(def weight-table
  {:light   300
   :regular 400
   :medium  500
   })

(defn TypographyDefine
  [size weight]
  {:font-size   (sp size)
   :font-weight (get weight-table weight)})

(defn TypographyDisplay4 [] (TypographyDefine 112 :light))
(defn TypographyDisplay3 [] (TypographyDefine 56 :regular))
(defn TypographyDisplay2 [] (TypographyDefine 45 :regular))
(defn TypographyDisplay1 [] (TypographyDefine 34 :regular))
(defn TypographyHeadline [] (TypographyDefine 24 :regular))
(defn TypographyTitle [] (TypographyDefine 20 :medium))
(defn TypographySubhead [] (TypographyDefine 15 :regular))
(defn TypographyBody2 [] (TypographyDefine 13 :medium))
(defn TypographyBody1 [] (TypographyDefine 13 :regular))
(defn TypographyCaption [] (TypographyDefine 12 :regular))
(defn TypographyButton [] (++ (TypographyDefine 14 :medium)
                              {:text-transform :uppercase}))


(defn url
  [url]
  (str "url(\"" url "\")"))

(defn url [v]
  (str "url(\"" v "\")"))



(def FlexEmbed
  {:display  :block
   :overflow :hidden
   :position :relative}
  )

(defn FlexEmbed-ratio
  [width-ratio height-ratio]
  {:display        :block
   :padding-bottom (percent (/ (* 100 height-ratio)
                               width-ratio))
   :width          (percent 100)
   })

(def FlexEmbed-content
  {:position :absolute
   :left     0
   :top      0
   :width    (percent 100)
   :height   (percent 100)}
  )



