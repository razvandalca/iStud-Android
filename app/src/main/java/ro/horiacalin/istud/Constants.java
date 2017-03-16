package ro.horiacalin.istud;

/**
 * Created by Razvan'S PC on 05.03.2017.
 */

public class Constants  {
    public static String API_BASE_URL="https://istud.itguys.ro";
    public static final int SPLASH_TIME_OUT = 500;
    public static final int DIALOG_TIME_OUT = 1500;
    public static final int requestID_NOtIF = 112;
    public static final String SHARED_PREF="shared_pref_user";
    public static final String SHARED_PREF_LOGIN="shared_pref_login";
    public static String MATERIE_KEY="materie_key";
    public static String NOTIF_KEY="notif_key";
    public static String USER_KEY="user_key";
    public static String NOTIF_EVENTS="notif_events";



    public static String END_SEMN_2="26-05-2017";
    public static String START_SEM_2="20.02.2017";



    public static final int USER_TYPE_STUDENT = 0;
    public static final int USER_TYPE_PROF = 1;
    public static final int USER_TYPE_ADMIN = -1;

    public static final int COURSE_FREQ_WEEKLY = 0;
    public static final int COURSE_FREQ_ODD = 1;
    public static final int COURSE_FREQ_EVEN = 2;
    public static final int COURSE_FREQ_MONTHLY = 4;

    public static final int SCH_TYPE_COURES = 1;
    public static final int SCH_TYPE_LAB = 2;
    public static final int SCH_TYPE_SEMINAR = 3;
    public static final int SCH_TYPE_PROJ = 4;




    public static final int GRADE_TYPE_COURSE = 0;
    public static final int GRADE_TYPE_LAB = 1;
    public static final int GRADE_TYPE_SEMINAR = 2;
    public static final int GRADE_TYPE_PROJECT = 3;





    public static final  int MILLISECONDS_IN_ONE_WEEK = 86400 * 7 * 1000;
    public static final  int MILLISECONDS_IN_TWO_WEEK = 86400 * 14 * 1000;
    public static final  int MILLISECONDS_IN_FOUR_WEEK = 86400 * 28 * 1000;

}
