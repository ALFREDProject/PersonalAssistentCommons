package eu.alfred.ui;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Messenger;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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
import eu.alfred.api.personalization.webservice.PersonalizationManager;
import eu.alfred.api.proxies.interfaces.ICadeCommand;
import eu.alfred.api.sensors.SAFDataFacade;
import eu.alfred.api.speech.Cade;
import eu.alfred.api.speech.CadeConstants;
import eu.alfred.api.speech.CadeSpeechStatus;
import eu.alfred.api.storage.CloudStorage;
import eu.alfred.helper.DialogUtils;
import eu.alfred.helper.PermissionUtils;
import eu.alfred.helper.Prefs;
import eu.alfred.personalassistant.sharedlibrary.R;

/**
 * Created by Gary on 04.02.2016.
 */
public abstract class AppActivityV23 extends FragmentActivity implements ICadeCommand {

    public PersonalAssistant personalAssistant;
    public Cade cade;
    public GameManager gameManager;
    public MarketPlace marketPlace;
    public SAFDataFacade safFacade;
    public CloudStorage cloudStorage;
    public PersonalizationManager personalizationManager;
    public GlobalSettings globalSettings;
    SharedPreferences prefs;

    public CircleButton circleButton;

    private final String[] permissionsRequired = new String[]{
            Manifest.permission.VIBRATE,
            Manifest.permission.INTERNET,
            Manifest.permission.GET_ACCOUNTS,
            Manifest.permission.WAKE_LOCK,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.SEND_SMS};
    private boolean askingPermissions;

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
            circleButton.setIsActive(false);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.voice_btn_layout);
        circleButton = (CircleButton) findViewById(R.id.voiceControlBtn);

        Prefs.init(getApplicationContext());
        askingPermissions = false;

        personalAssistant = new PersonalAssistant(getApplicationContext());

        personalAssistant.setOnPersonalAssistantConnectionListener(new PersonalAssistantConnection() {
            @Override
            public void OnConnected() {
                final Messenger msg = personalAssistant.getMessenger();
                globalSettings = new GlobalSettings(msg, AppActivityV23.this);
                globalSettings.getGlobalSettings(new GlobalSettingsResponse() {
                    @Override
                    public void OnSuccess(HashMap<String, Object> response) {
                        prefs = getSharedPreferences("global_settings", Context.MODE_PRIVATE);
                        if (response != null) {
                            SharedPreferences.Editor prefEditor = prefs.edit();
                            prefEditor.putString("pref_mircophone_color", (String) response.get("pref_mircophone_color"));
                            prefEditor.putBoolean("pref_useHardwareButton", (Boolean) response.get("pref_useHardwareButton"));
                            prefEditor.putString("pref_PhysicalHardwareButton", (String) response.get("pref_PhysicalHardwareButton"));
                            prefEditor.putString("pref_language",(String) response.get("pref_language"));
                            prefEditor.putString("pref_CADEUrl",(String) response.get("pref_CADEUrl"));
                            prefEditor.commit();
                            circleButton.setColor(prefs.getString("pref_mircophone_color","blue"), AppActivityV23.this);
                        }
                        cade = new Cade(msg);
                        gameManager = new GameManager(msg);
                        marketPlace = new MarketPlace(msg, getApplicationContext());
                        safFacade = new SAFDataFacade(msg);
                        cloudStorage = new CloudStorage(msg);
                        personalizationManager = new PersonalizationManager(msg);
                        onNewIntent(getIntent());
                    }

                    @Override
                    public void OnError(Exception exception) {
                        Log.e(exception.toString(), exception.getMessage());
                    }
                });
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
    }

    protected void onResume() {
        super.onResume();
        IntentFilter readyToSpeakFilter = new IntentFilter();
        readyToSpeakFilter.addAction(getPackageName() + "." + CadeSpeechStatus.READY_TO_SPEAK);
        registerReceiver(readyToSpeakReceiver, readyToSpeakFilter);

        IntentFilter endSpeakFilter = new IntentFilter();
        endSpeakFilter.addAction(getPackageName() + "." + CadeSpeechStatus.END_OF_SPEECH);
        registerReceiver(endSpeakReceiver, endSpeakFilter);

        if (!askingPermissions && android.os.Build.VERSION.SDK_INT >= 23) {
            checkPermissions();
        }
    }

    private void checkPermissions() {
        askingPermissions = true;
        PermissionUtils.requestPermissions(this, permissionsRequired, PermissionUtils.DEFAULT_PERMISSION_REQUEST_CODE);
    }

    private void askCheckPermissionsOrClose() {
        DialogUtils.showConfirmDialog(this,
                "Permissions needed!",
                "It is needed to accept all the permissions in order to run the application correctly.",
                "Check Permissions",
                "Close App",
                false,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkPermissions();
                    }
                },
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }

    private void askOpenPermissionSettingsOrClose() {
        DialogUtils.showConfirmDialog(this,
                "Permissions needed!",
                "It is needed to accept all the permissions in order to run the application correctly.",
                "Open Settings",
                "Close App",
                false,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openPermissionSettings();
                    }
                },
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }

    private void openPermissionSettings() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, PermissionUtils.DEFAULT_PERMISSION_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PermissionUtils.DEFAULT_PERMISSION_REQUEST_CODE) {
            checkPermissions();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (requestCode == PermissionUtils.DEFAULT_PERMISSION_REQUEST_CODE) {
            int status = 0; // 0 ok, 1 denied, 2 never ask again
            int i = 0;
            for (int granted : grantResults) {
                if (granted != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                        status = 1;
                    } else {
                        status = 2;
                        break;
                    }
                }
                i++;
            }
            if (status == 0) {
                askingPermissions = false;
            } else if (status == 1) {
                askCheckPermissionsOrClose();
            } else {
                askOpenPermissionSettingsOrClose();
            }
        }
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
