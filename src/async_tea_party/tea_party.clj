(ns async-tea-party.tea-party
  (:gen-class)
  (:require [clojure.core.async :as async]))

(def result-chan (async/chan 10))
(def google-tea-service-chan (async/chan 10))
(def yahoo-tea-service-chan (async/chan 10))

(def random-add
  (fn []
    (reduce + (conj [] (repeat 1 (rand-int 100000))))))

(defn request-google-tea-service []
  (async/go
    (random-add)
    (async/>! google-tea-service-chan "Tea compliments of Google")))

(defn request-yahoo-tea-service []
  (async/go
    (random-add)
    (async/>! yahoo-tea-service-chan "Tea compliments of Yahoo")))

(defn request-tea []
  (request-google-tea-service)
  (request-yahoo-tea-service)
  (async/go (let [[v] (async/alts! [google-tea-service-chan
                                    yahoo-tea-service-chan])]
              (async/>! result-chan v))))

(defn -main [& args]
  (println "Requesting Tea!!")
  (request-tea)
  (println (async/<!! result-chan)))

;; Section 1
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

;; Section 2
;; example function taking value from channel, non-blocking
;; (defn example-async []
;;   (let [tea-channel-2 (async/chan)]
;;     (async/go (async/>! tea-channel-2 :cup-of-tea-1))
;;     (async/go (println "Thanks for the" (async/<! tea-channel-2)))))

;; ;; example background service worker
;; (defn example-loop []
;;   (let [tea-channel (async/chan 10)]
;;     (async/go-loop []
;;       (println "Thanks for the" (async/<! tea-channel))
;;       (recur))))

;; ;; multi-channel usage
;; (def tea-channel (async/chan 10))
;; (def milk-channel (async/chan 10))
;; (def sugar-channel (async/chan 10))

;; ;; service worker that will print a message given to from multi sources
;; (async/go-loop []
;;   (let [[v ch] (async/alts! [tea-channel
;;                              milk-channel
;;                              sugar-channel])]
;;     (println "Got " v " from " ch)
;;     (recur)))
