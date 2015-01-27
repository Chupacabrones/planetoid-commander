using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class TargetController : MonoBehaviour {

	public Text winText;

	void Start(){
		winText.text = "";
	}

	void Update(){

	}

	void OnTriggerEnter(Collider other){
		if(other.tag == "Boundary")
			return;

		Destroy(other.gameObject);
		winText.text = "YOU WIN!";
	}
}
