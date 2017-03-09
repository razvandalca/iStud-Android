package ro.horiacalin.istud;

/**
 * Created by Razvan'S PC on 05.03.2017.
 */

public class Constants  {
    public static String API_BASE_URL="https://istud.itguys.ro";
    public static final int SPLASH_TIME_OUT = 500;
    public static final String SHARED_PREF="shared_pref_user";
    public static final String SHARED_PREF_LOGIN="shared_pref_login";
    public static String MATERIE_KEY="materie_key";



    public static final int USER_TYPE_STUDENT = 0;
    public static final int USER_TYPE_PROF = 1;
    public static final int USER_TYPE_ADMIN = -1;

    public static final int COURSE_FREQ_WEEKLY = 0;
    public static final int COURSE_FREQ_ODD = 1;
    public static final int COURSE_FREQ_EVEN = 2;
    public static final int COURSE_FREQ_MONTHLY = 4;


    public static final int GRADE_TYPE_COURSE = 0;
    public static final int GRADE_TYPE_LAB = 1;
    public static final int GRADE_TYPE_SEMINAR = 2;
    public static final int GRADE_TYPE_PROJECT = 3;

}
