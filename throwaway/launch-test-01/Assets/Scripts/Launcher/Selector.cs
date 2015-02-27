using UnityEngine;
using System.Collections;

public enum SelectionState
{
    Unselected,
    Selected,
    Launching,
    Launched
}

public class Selector : MonoBehaviour {

    public GameObject HUD;

    private SelectionState selectionState = SelectionState.Unselected;
    private HudDisplay hudDisplay;
    private Color c1 = Color.yellow;
    private Color c2 = Color.red;

    private int boardMask;
    private float maxLaunchDistance = 10.0f;
    private Vector3 lastLaunchPoint;

    void Awake()
    {
        boardMask = LayerMask.GetMask("BoardLayer");
        hudDisplay = HUD.GetComponent<HudDisplay>();
        HUD.SetActive(false);
    }

    void Start()
    {
        LineRenderer lineRenderer = gameObject.AddComponent<LineRenderer>();
        lineRenderer.material = new Material(Shader.Find("Particles/Additive"));
        lineRenderer.SetColors(c1, c2);
        lineRenderer.SetWidth(0.2F, 0.2F);
        lineRenderer.SetVertexCount(2);
        lineRenderer.SetPosition(0, transform.position);
        lineRenderer.enabled = false;
    }

	void Update () {
        LineRenderer lineRenderer = GetComponent<LineRenderer>();

        switch (selectionState)
        {
            case SelectionState.Unselected:
                // check for mouse button
                if (Input.GetButtonUp("Fire1"))
                {
                    //cast ray from mouseposition 
                    Debug.Log("Mouse clicked");
                    Ray mouseray = Camera.main.ScreenPointToRay(Input.mousePosition);
                    RaycastHit hit;
                    //check if the ray hits a Player object
                    if (Physics.Raycast(mouseray, out hit))
                    {
                        Debug.Log("Hit something.. " + hit.transform.tag);
                        if (hit.transform.tag == "Player")
                        {
                            //bring up the launch menu
                            selectionState = SelectionState.Selected;
                            hudDisplay.SetHudAtWorldPosition(hit.transform.position);
                        }
                    }
                }
                break;
            case SelectionState.Selected:

                if (Input.GetButton("Fire1"))
                {
                    //Draw a ray from center of gameObject to mousePosition
                    lineRenderer.enabled = true;
                    Ray camRay = Camera.main.ScreenPointToRay(Input.mousePosition);
                    RaycastHit boardHit;
                    if (Physics.Raycast(camRay, out boardHit, boardMask))
                    {
                        lastLaunchPoint = boardHit.point;
                        lineRenderer.SetPosition(1, lastLaunchPoint);
                    }
                }

                if (Input.GetButtonUp("Fire1"))
                {
                    selectionState = SelectionState.Launching;
                    LaunchSomething(lastLaunchPoint);
                }
                break;
            case SelectionState.Launching:
                selectionState = SelectionState.Unselected;
                lineRenderer.enabled = false;
                break;
            default:
                break;
        }

        HUD.SetActive(selectionState == SelectionState.Selected);
	}
    void LaunchSomething(Vector3 launchVector)
    {
        Debug.Log("Launched from: " + launchVector.ToString());
    }
}
