;; cube testing in unity
(ns planetoid.cubes
	(:use arcadia.core)
	(:import [UnityEngine Input Application Debug Vector3]))

(declare clamp-magnitude)
(declare velocity-to-target)
(declare square)

;; class vars??
(def cube1 (object-named "Cube1"))
(def max-move-distance (float 500.0))

;; orbit component definition
(defcomponent Orbit [^float speed]
	(FixedUpdate [this]
		(let [moveDistance (* (float max-move-distance) Time/deltaTime)
			  sourcePos (.. this transform position)
			  targetPos (.. cube1 transform position)
			  velocityVec (clamp-magnitude 
			  				(- (velocity-to-target sourcePos targetPos (float moveDistance)) 
			  				   (.. this rigidbody velocity))
			  				 moveDistance)]
				;; move position
				;(set! (.. this transform position) (Vector3. 0 0 0))
				
				;; Translate position
				;(.. this transform (Translate 0 0 0))
				
				;; LookAt cube 1
				(.. this transform (LookAt (.. cube1 transform)))

				;; add force to the rigidBody
				(.. this rigidbody (AddForce velocityVec))
			  )
	
	 ))

(defn clamp-magnitude [^Vector3 the-vector max-magnitude]
	(if (> (.. the-vector magnitude)
			(float max-magnitude))
		(* (.. the-vector normalized) (float max-magnitude))
		the-vector))

(defn velocity-to-target ^Vector3 [^Vector3 source ^Vector3 target move-distance]
	(let [directionToTarget (.. Vector3 (Normalize (- target source)))]
		   (v* (float move-distance) directionToTarget)))


(defn square [x]
	(float (* x x)))




