package ro.horiacalin.istud.PresentationLayer.Controller;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import ro.horiacalin.istud.BusinessLayer.Interfaces.CallbackDefaultNetwork;
import ro.horiacalin.istud.BusinessLayer.Managers.ApiManager;
import ro.horiacalin.istud.BusinessLayer.Managers.ToolsManager;
import ro.horiacalin.istud.R;

public class ResetareParolaActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "ResetareParolaActivity";
    private EditText parolaCurenta, parolaNoua, parolaConfirma;
    private Button resetPassButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_resetare_parola);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Resetare Parola");

        parolaCurenta = (EditText) findViewById(R.id.parolaCurentaEditText);
        parolaNoua = (EditText) findViewById(R.id.parolaNouaEditText);
        parolaConfirma = (EditText) findViewById(R.id.parolaConfirmaEditText);
        resetPassButton = (Button) findViewById(R.id.schimbaPArolaButton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        parolaConfirma.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    onResetPassClicked();
                }
                return false;
            }
        });

        resetPassButton.setOnClickListener(this);


    }

    private boolean isPasswordOK() {
        boolean isOk = true;
        if (parolaNoua.getText().toString().matches(".*\\d+.*")) {
            if (parolaNoua.getText().toString().length() < 6) {
                isOk = false;
                parolaNoua.setError(getString(R.string.err_pass_to_short));
            }else if(parolaNoua.getText().toString().contentEquals(parolaCurenta.getText().toString())){
                isOk = false;
                parolaNoua.setError(getString(R.string.pass_err_parola_noua_aceeas_parola_curenta));
            }
        } else {
            isOk = false;
            parolaNoua.setError(getString(R.string.err_pass_no_numbers));
        }

        return isOk;
    }

    private boolean areFieldsCompleted() {
        boolean isOk = true;
        if (parolaCurenta.getText().toString().trim().length() == 0) {
            parolaCurenta.setError(getString(R.string.err_camp_obligatoriu));
            isOk = false;
        }
        if (parolaNoua.getText().toString().trim().length() == 0) {
            parolaNoua.setError(getString(R.string.err_camp_obligatoriu));
            isOk = false;

        }
        if (parolaConfirma.getText().toString().trim().length() == 0) {
            parolaConfirma.setError(getString(R.string.err_camp_obligatoriu));
            isOk = false;
        }

        return isOk;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.schimbaPArolaButton:
                onResetPassClicked();
                break;
        }


    }

    private void onResetPassClicked() {
        if (areFieldsCompleted()) {
            if (isPasswordOK()) {
                if (verifyConfirmPass()) {
                    ToolsManager.getInstance().hideKeyboard(this, resetPassButton);
                    progressBar.setVisibility(View.VISIBLE);
                    try {
                        ApiManager.getInstance().resetPassword(ToolsManager.getInstance().getUser(getApplicationContext())
                                , parolaCurenta.getText().toString().trim()
                                , parolaConfirma.getText().toString().trim()
                                , getApplicationContext(),
                                new CallbackDefaultNetwork() {
                                    @Override
                                    public void success(Object object) {
                                        String message = (String) object;
                                        showDialog("Succes!", message, null);

                                    }

                                    @Override
                                    public void fail(String message) {
                                        showDialog("Eroare!", message, parolaCurenta);
                                    }
                                });
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Log.e(TAG, "onCreate: ");

                }

            }
        }
    }

    private void showDialog(String title, String message, final TextView resetView) {
        progressBar.setVisibility(View.GONE);
        new AlertDialog.Builder(ResetareParolaActivity.this).setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.alert_dialog_positive_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (resetView != null) {
                            resetView.setText("");
                            resetView.requestFocus();
                            ToolsManager.getInstance().hideKeyboard(ResetareParolaActivity.this, resetView);
                        } else {
                            finish();
                        }

                    }
                }).show();
    }

    private boolean verifyConfirmPass() {
        boolean isOk = true;
        if (!parolaConfirma.getText().toString().contentEquals(parolaNoua.getText().toString())) {
            isOk = false;
            parolaConfirma.setError("Parola nu se potriveste cu parola noua!");
        }
        return isOk;
    }
}
