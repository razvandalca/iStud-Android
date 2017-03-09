package ro.horiacalin.istud.PresentationLayer.Controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import ro.horiacalin.istud.Constants;
import ro.horiacalin.istud.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                verifyLogin();
            }
        }, Constants.SPLASH_TIME_OUT);


    }




    private void verifyLogin() {

        SharedPreferences prefs = getApplicationContext().getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE);
        if(prefs.getBoolean(Constants.SHARED_PREF_LOGIN,false)){
            Intent newsActivityIntent=new Intent(SplashScreenActivity.this, EcranPrincipalActivity.class);
            newsActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(newsActivityIntent);

        }else{
            Intent newsActivityIntent=new Intent(SplashScreenActivity.this, LoginActivity.class);
            newsActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(newsActivityIntent);
        }
    }

}
