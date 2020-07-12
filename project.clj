(defproject replnote "0.1.0-SNAPSHOT"
  :description "Write notes with a built in Clojure REPL."
  :url "https://github.com/gmemstr/replnote"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"] [seesaw "1.5.0"]]
  :main ^:skip-aot replnote.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
