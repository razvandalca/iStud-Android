package ro.horiacalin.istud.PresentationLayer.Controller;

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

    private TextView numeProfesor;
    private TextView numarCredite;
    private TextView numeMaterie;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.materielayout);
        numeProfesor = (TextView) findViewById(R.id.numeProfesorView);
        numarCredite = (TextView) findViewById(R.id.numarCrediteView);
        numeMaterie = (TextView) findViewById(R.id.numeMaterieView);

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
}
