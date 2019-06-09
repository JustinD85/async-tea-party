(ns async-tea-party.tea-party
  (:require [clojure.core.async :as async]))

;; example opening a buffered channel
;; (def tea-channel (async/chan 20))

;; example usage of a channel
;; (async/>!! tea-channel :cup-of-tea)
;; (async/>!! tea-channel :cup-of-tea-2)
;; (async/>!! tea-channel :cup-of-tea-3)

;; example usage of taking a value from a channel
;;can still get values from channel before it was closed

;; blocking
;; (async/<!! tea-channel)

;; non-blocking
;; (async/<! tea-channel)

;; example usage of closing a channel
;; (async/close! tea-channel)

;; example function taking value from channel, non-blocking
(defn example-async []
(let [tea-channel-2 (async/chan)]
  (async/go (async/>! tea-channel-2 :cup-of-tea-1))
  (async/go (println "Thanks for the" (async/<! tea-channel-2)))))

;; example background service worker
(defn example-loop []
  (async/go-loop []
    (println "Thanks for the" (async/<! tea-channel))
    (recur)))

