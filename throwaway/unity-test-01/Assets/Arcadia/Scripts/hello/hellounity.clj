;; hello unity example initial testing.
(ns hello.hellounity
	(:use arcadia.core)
	(:import [UnityEngine Input Application Debug Vector3]))

(declare move-update)

(defcomponent MoveSomething [^float speed]
	(Update [this]
		(move-update this)))

(defcomponent LightDisabler []
	(Update [this]
		(let [light (get-component this "Light")]
			(if (.. Input (GetKeyUp "space"))
				(set! (.. light enabled) false)))))

(defn move-update [^MoveSomething this]
	(.. this transform (Translate (* 
									(.speed this) 
									Time/deltaTime)
								   0 
								   0))
	;(Debug/Log "calling update..")
	(if (< (.. this transform position x)
			-2)
		(set! (.. this transform position)
			(Vector3. 4 0 0))))

(defn reset-world []
	(Application/LoadLevel Application/loadedLevel))

(defn do-stuff []
	(reset-world)
	(set! (.. (object-named "Cube")
		  (GetComponent hello.hellounity.MoveSomething)
		  speed)
	  (float -6))
	)




