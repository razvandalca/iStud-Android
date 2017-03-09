package ro.horiacalin.istud.PresentationLayer.Controller;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import ro.horiacalin.istud.BusinessLayer.Pojo.Materie;
import ro.horiacalin.istud.PresentationLayer.Adapters.RecyclerViewAdapterMaterii;
import ro.horiacalin.istud.R;

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
    private ProgressBar progressBar;
    private List<Materie> materiiLista;


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
        progressBar = (ProgressBar) rootView.findViewById(R.id.progres_materii);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);

        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapterMaterii(getActivity(),materiiLista);
        recyclerView.setAdapter(mAdapter);


//        ApiManager.getInstance().getCourses(ToolsManager.getInstance().);
        return rootView;
    }

}
