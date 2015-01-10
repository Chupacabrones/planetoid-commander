;; hello unity example initial testing.
(ns hello.hellounity
	(:use arcadia.core)
	(:import [UnityEngine Application]))

(declare move-update)

(defcomponent MoveSomething [^float speed]
	(Update [this]
		(move-update this)))

(defn move-update [^MoveSomething this]
	(.. this transform (Translate (.speed this) 0 0)))

(defn reset-world []
	(Application/LoadLevel Application/loadedLevel))

(defn do-stuff[]
	(reset-world)
	;; this isn't working right now... 
	(set! (.. (object-named "Cube")
		  (GetComponent hello.hellounity.MoveSomething)
		  speed)
	  (float 0.1)))




