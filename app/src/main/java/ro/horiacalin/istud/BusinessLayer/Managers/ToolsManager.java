package ro.horiacalin.istud.BusinessLayer.Managers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ro.horiacalin.istud.BusinessLayer.Pojo.EventNotif;
import ro.horiacalin.istud.BusinessLayer.Pojo.User;
import ro.horiacalin.istud.Constants;
import ro.horiacalin.istud.PresentationLayer.Controller.FragmentSetari;
import ro.horiacalin.istud.PresentationLayer.Controller.LoginActivity;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Razvan'S PC on 05.03.2017.
 */

public class ToolsManager {


    private static ToolsManager INSTANCE;
    private User user;
    public SharedPreferences prefs;
    public List<EventNotif> eventNotifList;

    private ToolsManager() {
    }

    public static ToolsManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ToolsManager();
            return INSTANCE;
        }
        return INSTANCE;
    }

    public void loginSuccesfull(User u, Context context) {
        prefs = context.getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE);
        prefs.edit().putBoolean(Constants.SHARED_PREF_LOGIN, true).commit();
        saveUser(u, prefs);
        Log.e("USER:", getUser(context).getEmail());
        this.user = u;

    }

    public void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
    }

    public void saveUser(User u, SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(u);
        editor.putString(Constants.USER_KEY, json);
        editor.commit();
    }

    public void saveNotifEvents(EventNotif notif,Context c) {
        SharedPreferences preferences=c.getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        eventNotifList.add(notif);
        String json = gson.toJson(eventNotifList);
        editor.putString(Constants.NOTIF_EVENTS, json);
        editor.commit();
    }

    public User getUser(Context context) {
        if (user != null) {
            return user;
        }
        SharedPreferences mPrefs = context.getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString(Constants.USER_KEY, "");
        User obj = gson.fromJson(json, User.class);
        return obj;
    }


    public List<EventNotif> getNotifEvents(Context context) {
        if (eventNotifList != null) {
            return eventNotifList;
        }
        SharedPreferences mPrefs = context.getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString(Constants.NOTIF_EVENTS, "");
        Type type = new TypeToken<List<EventNotif>>() {
        }.getType();
        List<EventNotif> obj = gson.fromJson(json, type);
        eventNotifList=new ArrayList<>();
        if(obj!=null) {
            eventNotifList=obj;
        }
        return eventNotifList;
    }


}
