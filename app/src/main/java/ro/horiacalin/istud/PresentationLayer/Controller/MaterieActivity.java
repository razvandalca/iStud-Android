package ro.horiacalin.istud.PresentationLayer.Controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import ro.horiacalin.istud.BusinessLayer.Pojo.Materie;
import ro.horiacalin.istud.Constants;
import ro.horiacalin.istud.R;

/**
 * Created by horiaacalin on 05/03/2017.
 */

public class MaterieActivity extends AppCompatActivity {

    private Materie materie;
    private String nume;
    private String numeTitular;
    private int nrCredite;
    private String contactProfesor;

    private TextView numeProfesor;
    private TextView numarCredite;
    private TextView numeMaterie;
    private TextView contactProfesorView;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.materielayout);
        numeProfesor = (TextView) findViewById(R.id.numeProfesorSetat);
        numarCredite = (TextView) findViewById(R.id.numarCrediteSetat);
        numeMaterie = (TextView) findViewById(R.id.numeMaterieSetat);
        contactProfesorView = (TextView) findViewById(R.id.adresaContactSetat);

        if (getIntent()!=null){
            materie = (Materie) getIntent().getSerializableExtra(Constants.MATERIE_KEY);
            nume = materie.getName();
            nrCredite = materie.getNumarCredite();
            numeTitular = materie.getNumeTitular();
            numeMaterie.setText(nume);
            numeProfesor.setText(numeTitular);

//            numarCredite.setText(nrCredite);
        }

    }

    public void trimiteEmailProfesor(View view){
        String [] addresses = new String[4];
        addresses[0] = "madalina.ghetu@gmail.com";
        addresses[1] = "calinhoriaalexandru@gmail.com";
        addresses[2] = "dalca.razvan@gmail.com";
        addresses[3] = "david.dumitru62@gmail.com";
        composeEmail(addresses, "Mail trimis din iStudy", "Futu-ti dumnezeii ma-tii");
    }

    public void composeEmail(String[] addresses, String subject, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
