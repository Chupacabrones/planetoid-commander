using UnityEngine;
using System.Collections;

public class CameraMovement : MonoBehaviour {

    public float smoothing = 5.0f;

    private Vector3 offset;
    private Vector3 targetPos = new Vector3();

    void Start()
    {
        //starting offset = difference from camera to (0,0,0) at game start
        offset = transform.position - targetPos;
    }

    void Update()
    {
        //if (Input.GetButtonUp("Fire1"))
        //{
        //    //cast ray from mouseposition 
        //    Ray mouseray = Camera.main.ScreenPointToRay(Input.mousePosition);
        //    RaycastHit hit;
        //    //check if the ray hits a Player object
        //    if (Physics.Raycast(mouseray, out hit))
        //    {
        //        if (hit.transform.tag == "Board")
        //        {
        //            targetPos = hit.point;
        //            Debug.Log(targetPos);
        //        }
        //    }
        //}
    }

    void FixedUpdate()
    {
        // follow the mouse clicked position
        Vector3 targetCamPos = targetPos + offset;
        transform.position = Vector3.Lerp(transform.position, targetCamPos, smoothing * Time.deltaTime);
    }

    public void SetLookTarget(Vector3 pos)
    {
        targetPos = pos;
    }
}
