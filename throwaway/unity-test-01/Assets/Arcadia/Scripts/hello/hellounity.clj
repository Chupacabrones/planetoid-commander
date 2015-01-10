;; hello unity example initial testing.
(ns hello.hellounity
	(:use arcadia.core)
	(:import [UnityEngine Application]))



(defcomponent MoveSomething [^float speed]
	(Update [this]
		(.. this transform (Translate speed
									  0
									  0))))