package ro.horiacalin.istud.PresentationLayer.Controller;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import ro.horiacalin.istud.BusinessLayer.Interfaces.CallbackDefaultNetwork;
import ro.horiacalin.istud.BusinessLayer.Managers.ApiManager;
import ro.horiacalin.istud.BusinessLayer.Managers.ToolsManager;
import ro.horiacalin.istud.BusinessLayer.Pojo.Grade;
import ro.horiacalin.istud.BusinessLayer.Pojo.Materie;
import ro.horiacalin.istud.Constants;
import ro.horiacalin.istud.R;

/**
 * Created by horiaacalin on 05/03/2017.
 */

public class MaterieActivity extends AppCompatActivity {

    private Materie materie;


    private TextView numeProfesor;
    private TextView birouProf;
    private TextView emailProf;


    private TextView numarCredite;
    private TextView punctajCurs;
    private TextView punctajLab;


    private TextView numeMaterie;


    private ProgressDialog progressDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.materielayout);


        numeProfesor = (TextView) findViewById(R.id.numeProfesorSetat);
        birouProf = (TextView) findViewById(R.id.birouProfesorSetat);
        emailProf = (TextView) findViewById(R.id.contactProfesorSetat);


        numarCredite = (TextView) findViewById(R.id.numarCrediteSetat);
        punctajCurs = (TextView) findViewById(R.id.punctajCursSetat);
        punctajLab = (TextView) findViewById(R.id.punctajLaboratorSetat);

        numeMaterie = (TextView) findViewById(R.id.numeMaterie);


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.materie_detalii_progress_dialog_title));
        progressDialog.setMessage(getString(R.string.materie_detalii_progress_dialog_message));
        progressDialog.show();

        if (getIntent() != null) {
            if (getIntent().hasExtra(Constants.MATERIE_KEY)) {
                materie = (Materie) getIntent().getSerializableExtra(Constants.MATERIE_KEY);
                if (materie != null) {
                    ApiManager.getInstance().getCourseDetails(ToolsManager.getInstance().getUser(getApplicationContext()).getId(),materie.getId(),getApplicationContext(), new CallbackDefaultNetwork() {
                        @Override
                        public void success(Object object) {
                            progressDialog.dismiss();
                            materie= (Materie) object;
                            populateAvticity();
                        }

                        @Override
                        public void fail(String message) {
                            progressDialog.dismiss();
                            new AlertDialog.Builder(MaterieActivity.this).setTitle(R.string.generic_alert_dialog_title)
                                    .setMessage(message)
                                    .setPositiveButton(R.string.alert_dialog_positive_button, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            finish();
                                        }
                                    }).show();
                        }
                    });
                }

            }
        }

    }

    private void populateAvticity() {
        if(materie.getCourse_name()!=null) {
            numeMaterie.setText(materie.getCourse_name());
        }
        if(materie.getProf_name()!=null) {
            numeProfesor.setText(materie.getProf_name());
        }
        if(materie.getOffice()!=null) {
            birouProf.setText(materie.getOffice());
        }
        if(materie.getEmail()!=null) {
            emailProf.setText(materie.getEmail());
        }
        if(materie.getCreditNo()!=0){
            numarCredite.setText(Integer.toString(materie.getCreditNo()));

        }

        if(materie.getGrades()!=null && materie.getGrades().size()>0) {
            for (Grade g : materie.getGrades()) {
                switch (g.getActivity()) {
                    case Constants.GRADE_TYPE_COURSE:

                        punctajCurs.setText(Double.toString(g.getValue()));
                        break;
                    case Constants.GRADE_TYPE_LAB:
                        punctajLab.setText(Double.toString(g.getValue()));
                        break;
                    case Constants.GRADE_TYPE_PROJECT:
                        //// TODO: 10.03.2017 ADD IN XML THE VIEW
                        break;
                    case Constants.GRADE_TYPE_SEMINAR:
                        //// TODO: 10.03.2017 ADD IN XML THE VIEW

                        break;

                }
            }
        }
    }


    public void composeEmail( View v) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, materie.getEmail());
        intent.putExtra(Intent.EXTRA_SUBJECT, materie.getCourse_name()+" "+ ToolsManager.getInstance().getUser(getApplicationContext()).getName());
        intent.putExtra(Intent.EXTRA_TEXT, "");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}
