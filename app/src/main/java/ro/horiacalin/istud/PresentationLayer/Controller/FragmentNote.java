package ro.horiacalin.istud.PresentationLayer.Controller;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ro.horiacalin.istud.BusinessLayer.Interfaces.CallbackDefaultNetwork;
import ro.horiacalin.istud.BusinessLayer.Managers.ApiManager;
import ro.horiacalin.istud.BusinessLayer.Managers.ToolsManager;
import ro.horiacalin.istud.BusinessLayer.Pojo.Materie;
import ro.horiacalin.istud.PresentationLayer.Adapters.RecyclerViewAdapterMaterii;
import ro.horiacalin.istud.R;

/**
 * Created by horiaacalin on 05/03/2017.
 */

public class FragmentNote extends android.support.v4.app.Fragment {

    private static final String ARG_PARAM1 = "param1";
    private TextView instructionLabel;
    private String mParam1;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterMaterii mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBar progressBar;
    private List<Materie> materiiLista = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;


    public FragmentNote() {


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
        View rootView = inflater.inflate(R.layout.fragment_note, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewNote);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progres_materii);
        instructionLabel = (TextView) rootView.findViewById(R.id.swypeToRefreshLabel);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refreshLayout);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);

        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapterMaterii(getActivity(), materiiLista);
        recyclerView.setAdapter(mAdapter);


        progressBar.setVisibility(View.VISIBLE);

        getCourses();
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.color_ntb_icon_inactive,R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                instructionLabel.setVisibility(View.GONE);
                getCourses();


            }
        });
        return rootView;
    }

    private void getCourses() {
        ApiManager.getInstance().getCourses(ToolsManager.getInstance().getUser(getActivity().getApplicationContext()).getId(), getActivity().getApplicationContext(), new CallbackDefaultNetwork() {
            @Override
            public void success(Object object) {
                progressBar.setVisibility(View.GONE);
                instructionLabel.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
                materiiLista = (List<Materie>) object;
                try{
                    if(materiiLista.size()==0){
                        instructionLabel.setVisibility(View.VISIBLE);
                    }
                }catch (Exception e){
                    instructionLabel.setVisibility(View.VISIBLE);

                }


                mAdapter.setmDataset(materiiLista);
            }

            @Override
            public void fail(String message) {
                progressBar.setVisibility(View.GONE);
                instructionLabel.setVisibility(View.VISIBLE);

            }
        });
    }

}
