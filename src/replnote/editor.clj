(ns replnote.editor)

(def debug (atom false))

(defn toggle-debug [] (reset! debug (not @debug)))

(defn find-rep [string] (re-seq #"#(\(.+?\)+)" string))

(defn eval-str [string]
	(cond
		(= false @debug) (str (eval (read-string string)))
		:else (str "[" (eval (read-string string)) "]" string)
))

(defn replace-things [string]
 (apply str (let [x (find-rep string)] (if (nil? x) string
  (let [y (first x)] (replace-things (clojure.string/replace string (first y)
     (str (eval-str (second y)))))
  ))
)))

; From https://stackoverflow.com/a/38284236.
(defn string->stream
  ([s] (string->stream s "UTF-8"))
  ([s encoding]
   (-> s
       (.getBytes encoding)
       (java.io.ByteArrayInputStream.))))
