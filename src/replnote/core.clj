(ns replnote.core (:gen-class)
  (:use seesaw.core seesaw.mig seesaw.font seesaw.chooser replnote.ui))

(defn -main [& args]
 (invoke-later
  (-> main-window
   pack!
   show!)))
