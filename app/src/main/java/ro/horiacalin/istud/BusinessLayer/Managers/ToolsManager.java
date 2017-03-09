package ro.horiacalin.istud.BusinessLayer.Managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import ro.horiacalin.istud.BusinessLayer.Pojo.User;
import ro.horiacalin.istud.Constants;

/**
 * Created by Razvan'S PC on 05.03.2017.
 */

public class ToolsManager {


    private static ToolsManager INSTANCE;

    private ToolsManager() {
    }

    public static ToolsManager getInstance() {
        if (INSTANCE==null){
            INSTANCE=new ToolsManager();
            return INSTANCE;
        }
        return INSTANCE;
    }

    public void loginSuccesfull(User u, Context context){
        SharedPreferences prefs = context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE);
        prefs.edit().putBoolean(Constants.SHARED_PREF_LOGIN,true).commit();
        // TODO: 09.03.2017 @Razvan/Horia
        //also save the User in DB
    }

    public void hideKeyboard(Context context ,View view){
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}
