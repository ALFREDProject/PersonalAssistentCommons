package eu.alfred.api.speech;

/**
 * Created by gilbe on 23.09.2015.
 */
public class CadeConstants {

    public static final int IS_ACTION = 12001;
    public static final int IS_WHQUERY = 12002;
    public static final int IS_VALIDITY = 12003;
    public static final int IS_ENTITYRECOGNIZER = 12004;

    public static final int RESULT_ACTION = 12005;
    public static final int RESULT_WHQUERY = 12006;
    public static final int RESULT_VALIDITY = 12007;
    public static final int RESULT_ENTITYRECOGNIZER = 12008;

    public static final String ACTION_STATE = "ACTION_STATE";
    public static final String WHQUERY_LIST = "WHQUERY_LIST";
    public static final String VALIDITY_STATE = "VALIDITY_STATE";
    public static final String ENTITYRECOGNIZER_LIST = "ENTITYRECOGNIZER_LIST";

    public static final int START_LISTENING = 10001;
    public static final int START_LISTENING_RESPONSE = 10002;
    public static final int STOP_LISTENING = 10003;
    public static final int STOP_LISTENING_RESPONSE = 10004;
    public static final int SET_CADE_BACKEND_URL = 10005;
    public static final int SET_CADE_BACKEND_URL_RESPONSE = 10006;
    public static final int GET_CADE_BACKEND_URL = 10007;
    public static final int GET_CADE_BACKEND_URL_RESPONSE = 10008;
}