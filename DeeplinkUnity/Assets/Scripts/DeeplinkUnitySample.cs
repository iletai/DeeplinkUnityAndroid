using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class DeeplinkUnitySample : MonoBehaviour
{
     
    void Start()
    {
        gameObject.GetComponent<Text>().text = PlayerPrefs.HasKey("deeplink") ? PlayerPrefs.GetString("deeplink") : "Your application don't start with deeplink";
    }

    private void OnApplicationQuit()
    {
        PlayerPrefs.DeleteAll();
    }

}
