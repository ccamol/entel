package cl.entel.appswlsdesa.utils;

/**
 * Created by alex on 8/4/14.
 */
public class Constants {

//    public static final String DOMAIN = "testing.mzzo.cl:7010";
//    public static final String DOMAIN = "appswlsdesa.entel.cl";
//    public static final String DOMAIN = "appswlstest.entel.cl";
//    public static final String DOMAIN = "appswlspre.entel.cl";
    public static final String DOMAIN = "appswls.entel.cl";

    public static final String VERSION_CODE = "v1.18";

    public static final String API_PATH = "mientel";
    public static final String PROTOCOL = "http";
    public static final int STATUS_SUCCESS = 0;
    public static final int STATUS_REGISTER = 2;
    public static final int STATUS_UNKNOWN_ERROR = 99;
    public static final int PROXY_ERROR_CODE = -5;
    public static final String ENTEL_PREFS = "entel_prefs";
    public static final String USER_KEY = "user";
    public static final String FONT = "font/trebuc.ttf";
    public static final String BOLD_FONT = "font/trebucbd.ttf";
    public static final String DIN_FONT = "font/DINNextLTPro-Regular.otf";

    //public static final String CREDENTIAL_USER = "webuser";
    //public static final String CREDENTIAL_PSW = "entel123";

    public static final String RECOVER_URL = "passwd.action?app=1";
    public static final String REGISTER_URL = "registro.action?app=1";
    public static final String RECARGAS_URL = "recarga=WebPay";
    public static final String BOLSAS_CONFIRMAR_URL = "/"+ Constants.API_PATH +"/bolsas/confirmar";
    public static final String USER_COOKIE = "esaCookie";
    public static final String SESSION_COOKIE = "JSESSIONID";
    public static final String CALL_HISTORY_BACK = "entel://historyBack";

    //Survey
    public static final String DOMAIN_SURVEY = "https://entel.qualtrics.com";
    public static final String URL_SURVEY = DOMAIN_SURVEY + "/jfe/form/SV_42UROmAY8PNv9it?Mercado=&Movil";
    public static final int RULE_1 = 5;
    public static final int SURVEY_DAYS = 90;
    public static final int SURVEY_TIMER_SECONDS = 30000;
    public static final String NUM_EXECUTIONS_PREF = "NUM_EXECUTIONS_PREF";
    public static final String RULE_1_PREF = "RULE_1_PREF";
    public static final String SURVEY_LAST_UPDATE_PREF = "SURVEY_LAST_UPDATE_PREF";
    public static final String SURVEY_ALERTBOX_NOTIF = "SURVEY_ALERTBOX_NOTIF";


    // USER AGENT
    public static final String USER_AGENT_SOURCE_APP_MOBILE = "_mientelApp";

    //Onboarding
    public static final String IS_FIRST_LAUNCH = "IS_FIRST_LAUNCH";
    public static final String LAST_VERSION_CODE = "LAST_VERSION_CODE";

    public static String getUrl(String page) {
        return String.format("%1$s://%2$s/%3$s/%4$s", PROTOCOL, DOMAIN, API_PATH, page);
    }
}
