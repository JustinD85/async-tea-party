(ns async-tea-party.tea-party
  (:require [clojure.core.async :as async]))

(def tea-channel (async/chan 20))

(async/>!! tea-channel :cup-of-tea)
(async/>!! tea-channel :cup-of-tea-2)
(async/>!! tea-channel :cup-of-tea-3)

(async/<!! tea-channel)

(async/close! tea-channel)
