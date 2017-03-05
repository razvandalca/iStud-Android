package ro.horiacalin.istud;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by horiaacalin on 05/03/2017.
 */

public class FragmentNote extends android.support.v4.app.Fragment {

    private static final String ARG_PARAM1 = "param1";
    private TextView textView;
    private String mParam1;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public FragmentNote(){


    }

    public static FragmentNote newInstance(String param1) {
        FragmentNote fragment = new FragmentNote();
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
        View rootView=inflater.inflate(R.layout.fragment_note, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewNote);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);

        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        ArrayList<Materie> materiiLista= new ArrayList<>();
        materiiLista.add(new Materie("ASC"));
        materiiLista.add(new Materie("TEST2"));
        materiiLista.add(new Materie("TEST3"));
        materiiLista.add(new Materie("TEST4"));
        materiiLista.add(new Materie("TEST5"));
        materiiLista.add(new Materie("TEST6"));
        materiiLista.add(new Materie("ASC"));
        materiiLista.add(new Materie("TEST2"));
        materiiLista.add(new Materie("TEST3"));
        materiiLista.add(new Materie("TEST4"));
        materiiLista.add(new Materie("TEST5"));
        materiiLista.add(new Materie("TEST6"));       materiiLista.add(new Materie("ASC"));
        materiiLista.add(new Materie("TEST2"));
        materiiLista.add(new Materie("TEST3"));
        materiiLista.add(new Materie("TEST4"));
        materiiLista.add(new Materie("TEST5"));
        materiiLista.add(new Materie("TEST6"));       materiiLista.add(new Materie("ASC"));
        materiiLista.add(new Materie("TEST2"));
        materiiLista.add(new Materie("TEST3"));
        materiiLista.add(new Materie("TEST4"));
        materiiLista.add(new Materie("TEST5"));
        materiiLista.add(new Materie("TEST6"));
        mAdapter = new RecyclerViewAdapterMaterii(getActivity(),materiiLista);
        recyclerView.setAdapter(mAdapter);
        return rootView;
    }
}
