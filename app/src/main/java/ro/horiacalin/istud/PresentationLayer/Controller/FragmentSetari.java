package ro.horiacalin.istud.PresentationLayer.Controller;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import ro.horiacalin.istud.BusinessLayer.Managers.ToolsManager;
import ro.horiacalin.istud.BusinessLayer.Pojo.User;
import ro.horiacalin.istud.Constants;
import ro.horiacalin.istud.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by horiaacalin on 12/03/2017.
 */

public class FragmentSetari extends android.support.v4.app.Fragment implements View.OnClickListener {

    private TextView resetareParola,termeniSiConditii,logOut,greetingsTextview;
    private Button contactButton;


    public FragmentSetari(){


    }

    public static FragmentSetari newInstance() {
        FragmentSetari fragment = new FragmentSetari();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_setari, container, false);
        contactButton= (Button) rootView.findViewById(R.id.butonContactEchipa);
        resetareParola= (TextView) rootView.findViewById(R.id.resetareParolaTextView);
        termeniSiConditii= (TextView) rootView.findViewById(R.id.termeniSiConditiiTextView);
        logOut= (TextView) rootView.findViewById(R.id.butonLogOut);
        greetingsTextview= (TextView) rootView.findViewById(R.id.greetingsTextView);
        greetingsTextview.setText(String.format(getActivity().getString(R.string.setari_hello),ToolsManager.getInstance().getUser(getActivity().getApplicationContext()).getUsername()));

        resetareParola.setOnClickListener(this);
        contactButton.setOnClickListener(this);
        logOut.setOnClickListener(this);
        termeniSiConditii.setOnClickListener(this);
        return rootView;
    }

    private void logOut() {
        getActivity().getApplicationContext().getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE).edit().putBoolean(Constants.SHARED_PREF_LOGIN,false).commit();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getActivity().finishAffinity();
        }else{
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

        }
        startActivity(intent);
    }


    public void mailEchipa() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, "office@istudy.ro");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Problema aplicatie iStudy");
        intent.putExtra(Intent.EXTRA_TEXT, " ");
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.butonLogOut:
                logOut();
                break;

            case R.id.butonContactEchipa:
                mailEchipa();
                break;

        }

    }
}
