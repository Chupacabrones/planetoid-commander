(ns planetoid.camera
	(:use arcadia.core
		  planetoid.vectormath)
	(:import [UnityEngine Vector3]))

;; class vars??
(def cube2 (object-named "Cube2"))

(defcomponent CameraFollower []
	(Update [this]
		(.. this transform (LookAt (.. cube2 transform)))
		))