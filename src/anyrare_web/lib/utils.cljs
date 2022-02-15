(ns anyrare-web.lib.utils
  (:require [goog :as goog]
            [goog.object :as goog.object]
            [anyrare-web.env :as env]))

(defn json->clj
  [e]
  (js->clj
   (-> e (js/JSON.stringify) (js/JSON.parse)) :keywordize-keys true))

(defn clj->json
  [e]
  (-> e
      (clj->js)
      (js/JSON.stringify)))

(defn obj->clj
  [obj]
  (-> (fn [result key]
        (let [v (goog.object/get obj key)]
          (if (= "function" (goog/typeOf v))
            result
            (assoc result key v))))
      (reduce {} (.getKeys goog/object obj))))

(defn jsx->clj
  [x]
  (into {} (for [k (.keys js/Object x)] [k (aget x k)])))

(defn ipfs-url
  [hash]
  (str env/IPFS_SERVER_URL "/ipfs/" hash))

(defn ipfs->url
  [ipfs]
  (clojure.string/replace
   ipfs #"ipfs://" (str env/IPFS_SERVER_URL "/ipfs/")))

