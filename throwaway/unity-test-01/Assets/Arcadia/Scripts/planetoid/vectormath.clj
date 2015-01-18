(ns planetoid.vectormath
	(:import [UnityEngine Vector3]))

;; ==============
;; vector math
;; ==============

(defn v3 ^Vector3 [x y z]
	(Vector3. x y z))


(defn v- ^Vector3 [^Vector3 v1 ^Vector3 v2]
	(Vector3/op_Subtraction v1 v2))