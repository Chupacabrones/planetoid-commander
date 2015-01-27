using UnityEngine;
using System.Collections;

public class LauncherController : MonoBehaviour {

	public GameObject projectile;
	public Transform spawnPoint;

	public float fireRate;
	private float nextFire;

	void Start(){
		nextFire = Time.time;
	}

	void Update(){
		if(Input.GetButton("Fire1") && nextFire < Time.time){
			nextFire = Time.time + fireRate;
			Fire();
		}
	}

	void Fire(){
		Instantiate(projectile, spawnPoint.position, spawnPoint.rotation);
	}
}
