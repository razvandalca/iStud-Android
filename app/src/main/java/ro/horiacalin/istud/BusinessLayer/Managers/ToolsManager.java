package ro.horiacalin.istud.BusinessLayer.Managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.gson.Gson;

import ro.horiacalin.istud.BusinessLayer.Pojo.User;
import ro.horiacalin.istud.Constants;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Razvan'S PC on 05.03.2017.
 */

public class ToolsManager {


    private static ToolsManager INSTANCE;
    private User user;

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
        SharedPreferences prefs = context.getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE);
        prefs.edit().putBoolean(Constants.SHARED_PREF_LOGIN,true).commit();
        saveUser(u,prefs);
        Log.e("USER:", getUser(context).getEmail());
        this.user=u;

    }

    public void hideKeyboard(Context context ,View view){
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void saveUser(User u, SharedPreferences sharedPreferences){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(u);
        editor.putString("User", json);
        editor.commit();
    }

    public User getUser(Context context){
        if(user!=null){
            return user;
        }
        SharedPreferences  mPrefs = context.getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE);;
        Gson gson = new Gson();
        String json = mPrefs.getString("User", "");
        User obj = gson.fromJson(json, User.class);
        return obj;
    }




}
