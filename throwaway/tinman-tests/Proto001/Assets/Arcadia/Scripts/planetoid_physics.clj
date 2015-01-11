(ns planetoid_physics
	(:use arcadia.core))

(defcomponent AxialRotator [^float speed]
	(Update [this]
		(.. this transform 
			(Rotate 1 1 speed))))