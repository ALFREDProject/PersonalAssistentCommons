package eu.alfred.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Messenger;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;

import eu.alfred.api.PersonalAssistant;
import eu.alfred.api.PersonalAssistantConnection;
import eu.alfred.api.event.webservice.EventManager;
import eu.alfred.api.gamemanager.GameManager;
import eu.alfred.api.market.MarketPlace;
import eu.alfred.api.personalization.webservice.PersonalizationManager;
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
    public EventManager eventManager;
    public SAFDataFacade safFacade;
    public CloudStorage cloudStorage;
    public PersonalizationManager personalizationManager;

    public CircleButton circleButton;

    private BroadcastReceiver readyToSpeakReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            circleButton.MakeRed();
        }
    };

    private BroadcastReceiver endSpeakReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            circleButton.MakeBlue();
        }
    };

    private BroadcastReceiver eventReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int methodToCall = intent.getIntExtra("method", 0);
            String command = intent.getStringExtra("command");
            HashMap args = (HashMap) intent.getSerializableExtra("args");

            switch (methodToCall) {
                case CadeConstants.IS_ACTION:
                    performAction(command, args);
                    break;
                case CadeConstants.IS_WHQUERY:
                    performWhQuery(command, args);
                    break;
                case CadeConstants.IS_VALIDITY:
                    performValidity(command, args);
                    break;
                case CadeConstants.IS_ENTITYRECOGNIZER:
                    performEntityRecognizer(command, args);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.voice_btn_layout);
        circleButton = (CircleButton) findViewById(R.id.voiceControlBtn);
        personalAssistant = new PersonalAssistant(getApplicationContext());

        personalAssistant.setOnPersonalAssistantConnectionListener(new PersonalAssistantConnection() {
            @Override
            public void OnConnected() {
                Messenger msg = personalAssistant.getMessenger();
                cade = new Cade(msg);
                gameManager = new GameManager(msg);
                marketPlace = new MarketPlace(msg);
                eventManager = new EventManager(msg);
                safFacade = new SAFDataFacade(msg);
                cloudStorage = new CloudStorage(msg);
                personalizationManager = new PersonalizationManager(msg);
                onNewIntent(getIntent());
            }

            @Override
            public void OnDisconnected() {
                // Do some cleanup stuff
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

    public void ButtonGotPressed(){
        circleButton.ButtonGotPressed();

        if(cade!=null) {
            if (circleButton.IsButtonRed()){
                cade.StartListening(getPackageName());
            } else {
                cade.StopListening(getPackageName());
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(readyToSpeakReceiver);
        unregisterReceiver(endSpeakReceiver);
        unregisterReceiver(eventReceiver);
    }

    protected void onResume() {
        super.onResume();
        IntentFilter readyToSpeakFilter = new IntentFilter();
        readyToSpeakFilter.addAction(getPackageName() + "." + CadeSpeechStatus.READY_TO_SPEAK);
        registerReceiver(readyToSpeakReceiver, readyToSpeakFilter);

        IntentFilter endSpeakFilter = new IntentFilter();
        endSpeakFilter.addAction(getPackageName() + "." + CadeSpeechStatus.END_OF_SPEECH);
        registerReceiver(endSpeakReceiver, endSpeakFilter);

        IntentFilter eventFilter = new IntentFilter();
        eventFilter.addAction(getPackageName() + "." + "EVENT");
        registerReceiver(eventReceiver, eventFilter);
    }

    @Override
    protected void onStop(){
        super.onStop();
        if (circleButton.isActive())
            ButtonGotPressed();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        personalAssistant.getContext().unbindService(personalAssistant.getServiceConnection());
    }


        public class CircleTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                ButtonGotPressed();
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                circleButton.ButtonGotReleased();
                return true;
            }
            return false;
        }
    }
}
