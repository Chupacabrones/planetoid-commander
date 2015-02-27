using UnityEngine;
using System.Collections;

public class HudDisplay : MonoBehaviour {

    public RectTransform UIPanel;
    public Canvas UICanvas;

    private RectTransform CanvasRect;

	// Use this for initialization
	void Awake () {
        CanvasRect = UICanvas.GetComponent<RectTransform>();
	}
	
	// Update is called once per frame
	void Update () {
        
	}

    Vector2 ScreenPositionOfWorldObject(Vector3 worldPosition, RectTransform CanvasRect)
    {
        //0,0 for the canvas is at the center of the screen, whereas WorldToViewPortPoint treats the lower left corner as 0,0. 
        //Because of this, you need to subtract the height / width of the canvas * 0.5 to get the correct position.
        Vector2 ViewportPosition = Camera.main.WorldToViewportPoint(worldPosition);
        Debug.Log(ViewportPosition.ToString());
        Vector2 WorldObject_ScreenPosition = new Vector2(
            ((ViewportPosition.x * CanvasRect.sizeDelta.x) - (CanvasRect.sizeDelta.x * 0.5f)),
            ((ViewportPosition.y * CanvasRect.sizeDelta.y) - (CanvasRect.sizeDelta.y * 0.5f))
        );
        return WorldObject_ScreenPosition;
    }

    public void SetHudAtWorldPosition(Vector3 worldPosition)
    {
        //calculate the position of the UI element
        UIPanel.anchoredPosition = ScreenPositionOfWorldObject(worldPosition, CanvasRect);
    }
}
