(defproject async-tea-party "0.1.0-SNAPSHOT"
  :description "Project that uses async features"
  :url "git@github.com/justnid85/tea-party"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/core.async "0.4.490"]]
  :repl-options {:init-ns async-tea-party.tea-party})
