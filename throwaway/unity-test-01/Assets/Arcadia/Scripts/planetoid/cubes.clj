;; cube testing in unity
(assembly-load-with-partial-name "System.Drawing")
(ns planetoid.cubes
	(:use arcadia.core
		  planetoid.vectormath)
	(:import 
		[UnityEngine Input Application Debug Vector3]
		[System.Drawing Color]))

;; class vars??
(def cube1 (object-named "Cube1"))
(def max-move-distance (float 10.0))

;; orbit component definition
(defcomponent Orbit [^float speed]
	(Awake [this])

	(Update [this]
		(if (.. Input (GetKeyUp "space"))
			(doto (.. this rigidbody)
			(.AddForce (Vector3. 0 50 10)))))
		
	(FixedUpdate [this]
		(let [moveDistance (* (float max-move-distance) Time/deltaTime)
			  sourcePos (.. this transform position)
			  targetPos (.. cube1 transform position)
			  velTargetVec (v-seek 
			  				  sourcePos 
			  				  targetPos 
			  				  (float moveDistance))
			  velocityVec  (v-clamp 
			  				 (v- velTargetVec (.. this rigidbody velocity))
			  				  moveDistance)]
				;; move position
				;(set! (.. this transform position) (Vector3. 0 0 0))
				
				;; Translate position
				;(.. this transform (Translate 0 0 0))
				
				;; LookAt cube 1
				;;(doto (.. this transform)
				;;	(.LookAt (.. cube1 transform)))

				;; add force to the rigidBody
				(doto (.. this rigidbody)
					(.AddForce velocityVec))

				;; debug rays
				;;(v-drawray sourcePos (v* (.. velocityVec normalized) (float 100.0)) (.. Color (FromName "Blue")))
				;;(v-drawray sourcePos (.. this rigidbody velocity) (.. Color (FromName "Yellow")))
				(Debug/DrawRay 
					(.. this transform position)
					(v* (.. this transform (TransformDirection Vector3/forward)) 5))
				;; can't get Color to return correct type.. keep getting an invalid cast exception

				)))







