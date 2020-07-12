(ns replnote.ui
 (:use seesaw.core seesaw.mig seesaw.font seesaw.chooser replnote.editor
 [clojure.java.io :only [file]]))

; Some/most of the UI code is taken from 
; https://github.com/daveray/seesaw/blob/master/test/seesaw/test/examples/text_editor.clj
; Hoping to iterate and clean it up to be more specific.

(def current-file (atom (file (System/getProperty "user.home") ".scratch.replnote")))

(when-not (.exists @current-file) (spit @current-file ""))

(defn enter-event [event editor]
 (if (= (.getKeyCode event) 10) (text! editor (string->stream (replace-things (text editor))))
))

(def editor (editor-pane
 :text @current-file
 :font (font :name :monospaced :size 18)
 :listen [:key-pressed (fn [e] (enter-event e editor))])
)

(def current-file-label (label :text @current-file))

(defn set-current-file [f] (swap! current-file (constantly f)))

(def status-label (label :text ""))

(defn set-status [& strings] (text! status-label (apply str strings)))

; Debug label ;
(defn get-debug-str [new] (str "Persist Statements: " (if (= true new) "On" "Off")))

(def debug-label (label :text (get-debug-str @debug)))

(defn set-debug [e] (text! debug-label (get-debug-str (toggle-debug))))

(def main-panel
     (mig-panel :constraints ["fill, ins 0"]
         :items [[(scrollable editor) "grow"]
         [current-file-label "dock south"]
         [(separator) "dock south"]
         [status-label "dock south"]
         [debug-label "dock south"]]))

(defn select-file [type] (choose-file main-panel :type type))

(defn a-new [e]
  (let [selected (select-file :save)] 
    (if (.exists @current-file)
      (alert "File already exists.")
      (do (set-current-file selected)
          (text! editor "")
          (set-status "Created a new file.")))))

(defn a-open [e]
  (let [selected (select-file :open)] (set-current-file selected))
  (text! editor (slurp @current-file))
  (set-status "Opened " @current-file "."))

(defn a-save [e]
  (spit @current-file (text editor))
  (set-status "Wrote " @current-file "."))

(defn a-save-as [e]
  (when-let [selected (select-file :save)]
    (set-current-file selected)
    (spit @current-file (text editor))
    (set-status "Wrote " @current-file ".")))

(defn a-exit  [e] (dispose! e))
(defn a-copy  [e] (.copy editor))
(defn a-cut   [e] (.cut editor))
(defn a-paste [e] (.paste editor))

(def menus
     (let [a-new (action :handler a-new :name "New" :tip "Create a new file." :key "menu N")
           a-open (action :handler a-open :name "Open" :tip "Open a file" :key "menu O")
           a-save (action :handler a-save :name "Save" :tip "Save the current file." :key "menu S")
           a-exit (action :handler a-exit :name "Exit" :tip "Exit the editor.")
           a-copy (action :handler a-copy :name "Copy" :tip "Copy selected text to the clipboard." :key "menu C")
           a-paste (action :handler a-paste :name "Paste" :tip "Paste text from the clipboard." :key "menu V")
           a-cut (action :handler a-cut :name "Cut" :tip "Cut text to the clipboard." :key "menu X")
           a-save-as (action :handler a-save-as :name "Save As" :tip "Save the current file." :key "menu shift S")
           debug-toggle (action :handler set-debug :name "Persist Statements")]
       (menubar
        :items [(menu :text "REPLNote" :items [debug-toggle])
                (menu :text "File" :items [a-new a-open a-save a-save-as a-exit])
                (menu :text "Edit" :items [a-copy a-cut a-paste])])))

(def main-window (frame :title "REPLNote",
         :menubar menus
         :content main-panel
         :on-close :exit :minimum-size [1280 :by 720]))
