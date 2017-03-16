package ro.horiacalin.istud.PresentationLayer.Controller;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import ro.horiacalin.istud.BusinessLayer.Pojo.EventNotif;
import ro.horiacalin.istud.Constants;
import ro.horiacalin.istud.R;

public class NotificareActivity extends AppCompatActivity {
    private TextView titluNotif, mesajNotif,cursNotif,dataNotif,oraNotif,locatieNotif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificare);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Detalii Notificare");

        titluNotif= (TextView) findViewById(R.id.titluNotificare);
        mesajNotif= (TextView) findViewById(R.id.mesajNotificare);
        cursNotif= (TextView) findViewById(R.id.cursSetat);
        dataNotif= (TextView) findViewById(R.id.dataSetat);
        oraNotif= (TextView) findViewById(R.id.oraSetat);
        locatieNotif= (TextView) findViewById(R.id.locatieSetat);


        if (getIntent() != null) {
            if (getIntent().hasExtra(Constants.NOTIF_KEY)) {
                try{
                    EventNotif ev= (EventNotif) getIntent().getSerializableExtra(Constants.NOTIF_KEY);
                    titluNotif.setText(ev.getTitle());
                    mesajNotif.setText(ev.getMessage());
                    cursNotif.setText(ev.getCourse());
                    dataNotif.setText(ev.getDate());
                    oraNotif.setText(ev.getTime());
                    locatieNotif.setText(ev.getRoom());
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }


}
