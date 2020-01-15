(ns ui.project
  (:require
   [clojure.string :as string :refer [split replace]])
  (:refer-clojure :exclude [replace])
  #?(:cljs
     (:require-macros
      [ui.project :refer [define-project-version define-project-build-time]])))

(declare project-version)
(declare project-build-time)

#?(:clj
   (defmacro define-project-version []
     `(def ~'project-version ~(string/replace (nth (string/split (slurp "project.clj") #"\s") 2) #"\"" ""))))

#?(:clj 
   (def df (java.text.SimpleDateFormat. "ddMMMyyyy-HH:mm:ss")))

#?(:clj
   (defmacro define-project-build-time []
     `(def ~'project-build-time ~(.format df (java.util.Date.)))))

(define-project-version)
(define-project-build-time)

