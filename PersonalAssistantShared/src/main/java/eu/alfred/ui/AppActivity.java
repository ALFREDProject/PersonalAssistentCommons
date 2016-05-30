package eu.alfred.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Messenger;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;

import eu.alfred.api.PersonalAssistant;
import eu.alfred.api.PersonalAssistantConnection;
import eu.alfred.api.gamemanager.GameManager;
import eu.alfred.api.globalSettings.GlobalSettings;
import eu.alfred.api.globalSettings.responses.GlobalSettingsResponse;
import eu.alfred.api.market.MarketPlace;
import eu.alfred.api.personalization.model.eventrecommendation.GlobalsettingsKeys;
import eu.alfred.api.personalization.webservice.PersonalizationManager;
import eu.alfred.api.personalization.webservice.eventrecommendation.EventrecommendationManager;
import eu.alfred.api.proxies.interfaces.ICadeCommand;
import eu.alfred.api.sensors.SAFDataFacade;
import eu.alfred.api.speech.Cade;
import eu.alfred.api.speech.CadeConstants;
import eu.alfred.api.speech.CadeSpeechStatus;
import eu.alfred.api.storage.CloudStorage;
import eu.alfred.personalassistant.sharedlibrary.R;

/**
 * Created by Gary on 04.02.2016.
 */
public abstract class AppActivity extends FragmentActivity implements ICadeCommand {

    public PersonalAssistant personalAssistant;
    public Cade cade;
    public GameManager gameManager;
    public MarketPlace marketPlace;
    public SAFDataFacade safFacade;
    public EventrecommendationManager eventrecommendationManager;
    public CloudStorage cloudStorage;
    public PersonalizationManager personalizationManager;

    public GlobalSettings globalSettings;
    public SharedPreferences prefs;

    public CircleButton circleButton;
    public BackToPAButton backToPAButton;

    private BroadcastReceiver readyToSpeakReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            circleButton.ButtonGotPressed();
        }
    };

    private BroadcastReceiver endSpeakReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(circleButton.isActive()) circleButton.ButtonGotPressed();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.voice_btn_layout);
        circleButton = (CircleButton) findViewById(R.id.voiceControlBtn);
        backToPAButton = (BackToPAButton) findViewById(R.id.backControlBtn);
        personalAssistant = new PersonalAssistant(getApplicationContext());

        personalAssistant.setOnPersonalAssistantConnectionListener(new PersonalAssistantConnection() {
            @Override
            public void OnConnected() {
                final Messenger msg = AppActivity.this.personalAssistant.getMessenger();
                globalSettings = new GlobalSettings(msg, AppActivity.this);
                globalSettings.getGlobalSettings(new GlobalSettingsResponse() {
                    public void OnSuccess(HashMap<String, Object> response) {
                        prefs = getSharedPreferences("global_settings", MODE_PRIVATE);
                        if(response != null) {
                            SharedPreferences.Editor prefEditor = AppActivity.this.prefs.edit();
                            prefEditor.putString("pref_mircophone_color", (String)response.get("pref_mircophone_color"));
                            prefEditor.putBoolean("pref_useHardwareButton", ((Boolean)response.get("pref_useHardwareButton")).booleanValue());
                            prefEditor.putString("pref_PhysicalHardwareButton", (String)response.get("pref_PhysicalHardwareButton"));
                            prefEditor.putString("pref_language", (String)response.get("pref_language"));
                            prefEditor.putString("pref_CADEUrl", (String)response.get("pref_CADEUrl"));
                            prefEditor.putString(""+ GlobalsettingsKeys.userEventsAccepted, (String)response.get(""+GlobalsettingsKeys.userEventsAccepted));
                            prefEditor.putString(""+ GlobalsettingsKeys.userId, (String)response.get(""+GlobalsettingsKeys.userId));
                            prefEditor.commit();
                            circleButton.setColor(AppActivity.this.prefs.getString("pref_mircophone_color", "blue"), AppActivity.this);
                            backToPAButton.setColor(AppActivity.this.prefs.getString("pref_mircophone_color", "blue"), AppActivity.this);
                        }

                        cade = new Cade(msg);
                        gameManager = new GameManager(msg);
                        marketPlace = new MarketPlace(msg, AppActivity.this.getApplicationContext());
                        safFacade = new SAFDataFacade(msg);
                        cloudStorage = new CloudStorage(msg);
                        personalizationManager = new PersonalizationManager(msg);
                        eventrecommendationManager = new EventrecommendationManager(msg);
                        onNewIntent(AppActivity.this.getIntent());
                    }
                    public void OnError(Exception exception) {
                        Log.e(exception.toString(), exception.getMessage());
                    }
                });
            }


            @Override
            public void OnDisconnected() {
                AppActivity.this.finish();
            }
        });

        personalAssistant.Init();
    }

    @Override
    public void onNewIntent(Intent intent) {
        Log.i("onNewIntent", getPackageName());
        super.onNewIntent(intent);
        setIntent(intent);

        int methodToCall = intent.getIntExtra("method",0);
        String command = intent.getStringExtra("command");
        HashMap args = (HashMap) intent.getSerializableExtra("args");

        if (getIntent().getBooleanExtra("EXIT", false))
        {
            finish();
        }

        switch (methodToCall) {
            case CadeConstants.IS_ACTION:
                performAction(command,args);
                break;
            case CadeConstants.IS_WHQUERY:
                performWhQuery(command,args);
                break;
            case CadeConstants.IS_VALIDITY:
                performValidity(command,args);
                break;
            case CadeConstants.IS_ENTITYRECOGNIZER:
                performEntityRecognizer(command,args);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(readyToSpeakReceiver);
        unregisterReceiver(endSpeakReceiver);
    }

    protected void onResume() {
        super.onResume();
        IntentFilter readyToSpeakFilter = new IntentFilter();
        readyToSpeakFilter.addAction(getPackageName() + "." + CadeSpeechStatus.READY_TO_SPEAK);
        registerReceiver(readyToSpeakReceiver, readyToSpeakFilter);

        IntentFilter endSpeakFilter = new IntentFilter();
        endSpeakFilter.addAction(getPackageName() + "." + CadeSpeechStatus.END_OF_SPEECH);
        registerReceiver(endSpeakReceiver, endSpeakFilter);
    }

    @Override
    protected void onStop(){
        super.onStop();
        if (circleButton.isActive())
            circleButton.ButtonGotPressed();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        personalAssistant.getContext().unbindService(personalAssistant.getServiceConnection());
    }


    public class MicrophoneTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if(cade!=null) {
                    if (!circleButton.isActive()) {
                        cade.StartListening(getPackageName());
                    } else {
                        cade.StopListening(getPackageName());
                    }
                }
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {

                return true;
            }
            return false;
        }
    }

    public class BackTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                PackageManager pm = getPackageManager();
                Intent intent = pm.getLaunchIntentForPackage("eu.alfred.personalassistant");
                startActivity(intent);
                return true;
            }
            return false;
        }
    }
}
