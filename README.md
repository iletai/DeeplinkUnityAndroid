# Mobile	deep	linking

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

When you working with Unity, we will facing some case trigger app/ads from another application. So in repository will help you make that clearly.

  - Sample Unity Project Listner URl when deeplink was click on Android Application
  - Sample Arr library deeplink Android
  - Notes and know issues

# [Config Android Studio to build Java Library](https://github.com/iletai/JavaNativeUnity/tree/develop/NativeLibrary)

  - Create blank application on Android Studio

  - Config build.gradel(Module:app)
   ```apply plugin: 'com.android.library'```         

  - Config Appmanifest.xml
    ```
        <application
            android:allowBackup="true"
            android:label="@string/app_name"
            android:supportsRtl="true"
        />
    ```        
    

- Integraion lib java of Unity Engine to our lib: 
  - Redriect to `Unity/PlaybackEngines/AndroidPlayer/Variations/mono/Release/Classes.jar` and copy it
  - Paste it on
  - In `build.gradle`. Remove `  applicationId "com.tl.deeplinknativeandroid" `


  - Create a new class to call native code:
 ```public final class TLDeeplink``` extends from ` UnityPlayerActivity ` we was import in before step.
Define a key to save it to storage device in Unity. `    private String DEEP_LINK_URL_KEY = "deeplink";
`
@Overwrride function of Android `onCreate` and save that key was define to storage device Android. 
When user trigger from link, App will connect to that function and writedown key. In Unity only get key and value on it. Build to get library .aar. More detail in project android library of repository




# What	is	mobile	deep	linking?

![Deeplink](https://raw.githubusercontent.com/iletai/DeeplinkUnityAndroid/develop/Image/flowdeeplink.png)

Deep	link	in	the	context	of	the	web	is	a	hyperlink	linking to	a	specific	content	instead	
of	a	homepage	of	a	website.	The	term	mobile	deep	linking	stands	for	the	ability	to	
link	specific	content	of	a	mobile	app	with	the	use	of	URIs,	which allows	users	to	share	
content	outside	the	traditional	boundaries	of	the	application.	Tapping	a	deep link	on	
a	mobile	device	will	open the	application	in	question	and	show the linked content	to	
the	user.	If	the	application was	not	already	installed	on	the	system,	app	store	page	
will	be	presented	to	the	user	as	illustrated	

After build dll file. We need copy it to Unity. I recommend using Batscripts to save time
 

### Flow function

![How to implement](https://raw.githubusercontent.com/iletai/DeeplinkUnityAndroid/develop/Image/implementflow.png)


 
 

### Sample behavior trigger:

![trigger](https://raw.githubusercontent.com/iletai/DeeplinkUnityAndroid/develop/Image/samplebehavior.png)

 

# Using on Sample Unity 

- Create file `AndroidManifest.xml` .
- Param `android:name` package name and class java created.
- Param `android:scheme` is your application name in `PlayerSettings` ![here](https://raw.githubusercontent.com/iletai/DeeplinkUnityAndroid/develop/Image/bundelpackagename.png)

```
<?xml version="1.0" encoding="utf-8"?>
<manifest 
  xmlns:android="http://schemas.android.com/apk/res/android">
  <application 
    android:icon="@drawable/app_icon" 
    android:label="@string/app_name">
    <activity 
      android:name="com.tl.deeplinknativeandroid.TLDeeplink"
      android:label="@string/app_name" 
      android:launchMode="singleTask">
      <intent-filter>
        <action 
          android:name="android.intent.action.MAIN" />
        <category 
          android:name="android.intent.category.LAUNCHER" />
      </intent-filter>
      <intent-filter>
        <data 
          android:scheme="dlsample" />
        <action 
          android:name="android.intent.action.VIEW" />
        <category 
          android:name="android.intent.category.DEFAULT" />
        <category 
          android:name="android.intent.category.BROWSABLE" />
      </intent-filter>
    </activity>
  </application>
</manifest>
```
Create and save `AndroidManifest.xml` to folder with exactly path: `Assets/Plugins/Android/`
Unzip file .aar in `..\AndroidLibrary\app\build\outputs\aar` of frist step and copy file .jar to `Assets/Plugins/Android/`
 

Create an script to get key in sample app:
```sh
$  gameObject.GetComponent<Text>().text = PlayerPrefs.HasKey("deeplink") ? PlayerPrefs.GetString("deeplink") : "Your application don't start with deeplink";
```

Testing
- Created a html file and copy it to device:
```
<!DOCTYPE html>
<html>

<head>

</head>

<body>
    <p>Hello. Do you want to visit the website <a href="dlsample://a?b">Result</a>?<br />
        It's a good website! ;-)</p>
</body>

</html>
```

Result success when you click tag a in html file. File will open your app unity was build in device. And show link by storage if that trigger from deeplink. And package of this sample here: [Release](https://github.com/iletai/DeeplinkUnityAndroid/releases/tag/0.0.1)
