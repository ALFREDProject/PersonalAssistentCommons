# PersonalAssistentCommons

---

## 1. Install the Personal Assistent

[Personal Assistent](https://github.com/ALFREDProject/PersonalAssistentApp/)

## Personal Assistent Commons (PAC) - what is this? 

This libary enables the developer to access functionalities from 

* CADE (Context-Aware Dialogue Engine) 
* KIS (Knowledge and Information Storage) 
* GM (Game Manager) 
* HM (Health Monitor) 
* PM (Personalization Manager) 
 
… which are all part of the Personal Assistent. As a result, with the help of PAC your 3rd party app may use all this functionality.  

## Execution and Usage

This project has the following path:

    \personalassistantcommons\PersonalAssistantShared\build\outputs\aar 
    
You can find PersonalAssistentShared-debug.aar there. Copy it an paste the file into yourproject\app\libs 
Run Android Studio. There, put into your build.gradle (module): 

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.android.support:appcompat-v7:21.0.3'
    compile 'eu.alfred.personalassistant.sharedlibrary:PersonalAssistantShared-debug@aar'  <b>sample</b>
}


An app that consumes the services of the ALFRED ecosystem needs to include the personalassistantshared-debug.aar which is part of the binary package you get with this deliverable. Alternatively, it can be found in the Jenkins CI already mentioned for the “PersonalAssistantCommons”. This Commons-library provides templates the Personal Assistant itself and especially every third party app uses. Already in previous versions, very little code was required to actually use and connect to the Personal Assistant Service. 
The PersonalAssistant class needs a context provided in the constructor. The only event available is the setOnPersonalAssistantConnectionListener, which offers the two methods OnConnected and OnDisconnected.
OnConnected is called after the library has established the connection to the Personal Assistant Service. In the OnConnected method, it is possible to initialize several APIs like the GameManager or CADE. The OnDisconnected method is used likewise for cleaning up the used instances.
This is the overall initialization workflow for the Personal Assistant Service. 
Finally, the Init method has to be called. It will do the binding to the service itself.
This procedure can also be seen in Listing 1:

Listing 1: Old way of connecting to the Personal Assistant Service
 

In this case, we will react on the OnConnected event, and will create a new instance of the Context-Aware Dialogue Engine (CADE). This object can then be used throughout the application lifetime.
In general, every wrapper library will need an injected dependency, an instance of the Messenger class, which gets created by the PersonalAssistant object.
The version in D.3.5.1 was improved a second time. The way of connecting is still valid, but is adopted by a base activity called AppActivity. It is part of commons library and takes the Personal Assistant registration inclusive all wrappers as seen above. So, one has no longer to use this snippet in his own in-app-code. The app only has to extend from AppActivity.
In addition, a user should be able to interact with the PA, although another third party app may be open. Therefore, another, smaller microphone button should be provided for every app having a GUI. AppActivity also does this, including registering broadcast receivers for button press actions.
So, a developer has to do nothing more than extending this class and setting the predefined listener to the microphone button in onCreate(), as seen in Listing 2: New way of connecting to the Personal Assistant Service. 

Listing 2: New way of connecting to the Personal Assistant Service
 

2.5.2	Using the implementations with CADE
Having the Personal Assistant bound to an app, a developer may want to use some components, which are provided by the assistant, for example CADE.
If the integration of CADE has been considered for an app, some additional tasks for the integration of the overall voice command infrastructure have to be performed.
First, every developer needs to provide a Dialogue Domain Description (DDD), as described in D4.1.2. This description will then be installed into the CADE Session Manager and CADE Backend, which is running on an ALFRED server instance.
Additionally, the developer will need to integrate an additional IPC channel, which is based on Intents. By extending AppActivity, this is already done. The Personal Assistant Service will send a special Intent to the calling application. Important: The appname described in the DDD has to be the same as the actual Android application’s name. Note the case sensitivity. This intent has the plaintext command as payload (also the same in DDD and app) and some additional parameters belonging to the command to call. Figure 5 depicts a complete run of the speech interaction.
 
Figure 5: Sequence Diagram of the CADE Interaction for Third Party Apps

Thanks to the Personal Assistant Commons library, the app is able to handle that intent accordingly. The library provides a mocked Cade class with the method needed. The receiving app simply needs to implement an interface of the external component (CADE). 
 
Listing 3: Handling incoming CADE command
 
CADE has four methods, which could be called in the IPC channel of the Personal Assistant (perform actions and queries, validate speech commands and recognize queried entitites). The NewIntent() method of AppActivity ensures, that the needed perform-method in the third party app will be called instantly.
For instance, an action named "ShowCalendarAction" was called. To encapsulate the code properly, the developer may search for the called action in a switch-environment and launch an action class of their own, carrying the context and mocked cade-instance as parameters.


Listing 4: Executing CADE Action
 

Having done some work in her action-class, the developer needs to send a command back to the Personal Assistant, that she has taken care of the response from the Personal Assistant Service. With the help of the mocked cade class from shared-libary, the call of cade.sendActionResult(true); informs the PA that the action was finished successfully. 

The latter again informs CADE server about the success. This behaviour of CADE could be transferred to other external components needed, so the developer has access to all resources.
