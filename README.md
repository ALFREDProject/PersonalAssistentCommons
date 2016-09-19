# PersonalAssistentCommons

---

### Usage Of The Personal Assistent, Personal Assistent Commons And App Development

---

## 1. Install The Personal Assistent On Your Phone

Creating an application integrated with the Personal Assistant, enables the application to have a controlled dialogue to interact faster and more human friendly than ever.
This is a system that requires the Personal Assistant, which is the controller for all the components and CADE, which handles the voice logic to enable the developers an easy access to the voice action events, which can be human friendly!

[Personal Assistent](https://github.com/ALFREDProject/PersonalAssistentApp/)

## 2. Personal Assistent Commons (PAC) - What Is This? 

This libary enables the developer to access functionalities from 

* CADE (Context-Aware Dialogue Engine) 
* KIS (Knowledge and Information Storage) 
* GM (Game Manager) 
* HM (Health Monitor) 
* PM (Personalization Manager) 
 
… which are all part of the Personal Assistent. As a result, with the help of PAC your 3rd party app may use all this functionality.  

## Execution And Usage

This project has the following path:

> \personalassistantcommons\PersonalAssistantShared\build\outputs\aar
 
You can find <b>PersonalAssistentShared-debug.aar</b> there. Copy it an paste the file into 

> [yourproject]\app\libs 

Run Android Studio. There, put into your build.gradle (module): 

 <dd> dependencies { </dd>
 <dd> compile fileTree(dir: 'libs', include: ['*.jar']) </dd>
 <dd> compile 'com.android.support:appcompat-v7:21.0.3' </dd>
 <dd> <b>compile 'eu.alfred.personalassistant.sharedlibrary:PersonalAssistantShared-debug@aar'</b> </dd>
 <dd> } </dd>

Build the project. 

## Using PAC In Your App

Now, the Personal Assistent Commons is in your project. To use it correctly and enable it for accessing PA modules / being accessed. Your MainActivity can extend from „AppActivity“. 
 
What AppActivity does:  
* It already integrates the every Personal Assistent Module. 
* In addition, it registers the „CircleButton“, the PA also uses. 
* Registers corresponding recievers (for start / stop listening). 

So, your on create looks like: 

<p align="center">
<img src ="howto1.JPG"/>
<dd IMPORTANT: Dont implement your own TouchListener, otherwise it will not work. />
</p>

As you can see, you are referencing to a „CircleButton“. It is an overlay to your app GUI, so that you also will be able to continue speaking, although you are not in the Personal Assistent App itself. Define the Button also in your activity_main.xml-file: 

<p align="center">
<img src ="howto2.JPG"/>
</p>
