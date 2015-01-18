(ns planetoid.vectormath
	(:import [UnityEngine Vector3]))

;; ===================
;; vector math helpers
;; ===================

(defn v- ^Vector3 [^Vector3 v1 ^Vector3 v2]
	(Vector3/op_Subtraction v1 v2))

(defn v+ ^Vector3 [^Vector3 v1 ^Vector3 v2]
	(Vector3/op_Addition v1 v2))

(defn v* ^Vector3 [^Vector3 v d]
	(Vector3/op_Multiply v (float d)))

(defn v÷ ^Vector3 [^Vector3 v1 ^Vector3 v2]
	(Vector3/op_Division v1 v2))

;; clamp a vectors magnitude by a maximum value
(defn v-clamp ^Vector3 [^Vector3 v clamp]
	(if (> (.. v magnitude)
		   (float clamp))
		;; clamp
		(v* (.. v normalized) (float clamp))
		;; don't clamp
		 v))

(defn v-normalize ^Vector3 [^Vector3 v]
	(.. Vector3 (Normalize v)))

(defn v-seek ^Vector3 [^Vector3 source ^Vector3 target move-distance]
	(let [directionToTarget (v-normalize (v- target source))]
		   (v* directionToTarget (float move-distance))))

(defn v-test-01 []
	(let [v1 (Vector3. 1 1 1)
		  v2 (Vector3. 2 2 2)]
		(v- v1 v2)))

(defn v-test-02 []
	(let [v1 (Vector3. 1 1 1)
		  v2 (Vector3. 2 2 2)]
		(v+ v1 v2)))

(defn v-test-03 []
	(let [v1 (Vector3. 1 1 1)
		  d (float 2.0)]
		(v* v1 d)))

(defn v-test-04 []
	(let [v1 (Vector3. 1 1 1)
		  v2 (Vector3. 2 2 2)]
		(v÷ v1 v2)))

(defn v-test-05 []
	(let [v1 (Vector3. 3 1 5)]
		(v-clamp v1 1.0)))

(defn v-test-06 []
	(let [v1 (Vector3. 3 1 5)]
		(v-normalize v1)))

(defn v-test-07 []
	(let [v1 (Vector3. 1 1 1)
		  v2 (Vector3. 2 2 2)
		  d (float 1.0)]
		(v-seek v1 v2 d)))

(defn run-tests []
	(v-test-01)
	(v-test-02)
	(v-test-03)
	;(v-test-04)
	(v-test-05)
	(v-test-06)
	(v-test-07)
	)
