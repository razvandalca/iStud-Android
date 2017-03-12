package ro.horiacalin.istud.PresentationLayer.Controller;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import ro.horiacalin.istud.R;

/**
 * Created by horiaacalin on 12/03/2017.
 */

public class FragmentSetari extends android.support.v4.app.Fragment{

    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    Button   mButton;
    EditText mEdit;

    public FragmentSetari(){


    }

    public static FragmentSetari newInstance(String param1) {
        FragmentSetari fragment = new FragmentSetari();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_setari, container, false);


        mButton = (Button)rootView.findViewById(R.id.button);
        mEdit   = (EditText)rootView.findViewById(R.id.numeUser);

        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Log.v("Nume user", mEdit.getText().toString());
                    }
                });
        return rootView;
    }
}
