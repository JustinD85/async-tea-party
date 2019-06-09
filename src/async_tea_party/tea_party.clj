(ns async-tea-party.tea-party
  (:require [clojure.core.async :as async]))

(def tea-channel (async/chan 20))

(async/>!! tea-channel :cup-of-tea)
(async/>!! tea-channel :cup-of-tea-2)
(async/>!! tea-channel :cup-of-tea-3)

(async/<!! tea-channel)

;can still get values from channel before it was closed
(async/close! tea-channel)

(defn example-async []
(let [tea-channel-2 (async/chan)]
  (async/go (async/>! tea-channel-2 :cup-of-tea-1))
  (async/go (println "Thanks for the" (async/<! tea-channel-2)))))
