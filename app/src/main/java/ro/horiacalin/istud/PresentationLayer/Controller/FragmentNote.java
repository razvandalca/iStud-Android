package ro.horiacalin.istud.PresentationLayer.Controller;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
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

public class FragmentNote extends android.support.v4.app.Fragment implements SearchView.OnQueryTextListener {

    private static final String ARG_PARAM1 = "param1";
    private TextView instructionLabel;
    private String mParam1;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterMaterii mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBar progressBar;
    private List<Materie> materiiListaOriginal = new ArrayList<>();
    private List<Materie> materiiListaShow = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private SearchView searchView;
    private ImageView closeSearchButton;


    public FragmentNote() {


    }

    public static FragmentNote newInstance() {
        FragmentNote fragment = new FragmentNote();
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
        searchView = (SearchView) rootView.findViewById(R.id.searchView);


        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new RecyclerViewAdapterMaterii(getActivity(), materiiListaOriginal);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        searchView.setOnQueryTextListener(this);
        searchView.setQuery("", false);
        searchView.clearFocus();
        progressBar.setVisibility(View.VISIBLE);

        getCourses();


        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.color_ntb_icon_inactive, R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                instructionLabel.setVisibility(View.GONE);
                getCourses();


            }
        });




        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                materiiListaShow.clear();
                materiiListaShow.addAll(materiiListaOriginal);
                mAdapter.setmDataset(materiiListaShow);
                searchView.getFocusedChild().clearFocus();
                searchView.clearFocus();
                searchView.setQuery("", true);
                return false;
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
                materiiListaOriginal = (List<Materie>) object;
                materiiListaShow.clear();
                materiiListaShow.addAll(materiiListaOriginal);
                try {
                    if (materiiListaShow.size() == 0) {
                        instructionLabel.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    instructionLabel.setVisibility(View.VISIBLE);

                }


                mAdapter.setmDataset(materiiListaShow);
                searchView.setQuery("", false);
                searchView.clearFocus();
            }

            @Override
            public void fail(String message) {
                progressBar.setVisibility(View.GONE);
                instructionLabel.setVisibility(View.VISIBLE);

            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        performSearch(query);
        return false;
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        performSearch(newText);
        return false;
    }


    private void performSearch(String query) {
        if (query.equals("")) {
            materiiListaShow.clear();
            materiiListaShow.addAll(materiiListaOriginal);
            mAdapter.setmDataset(materiiListaShow);
            instructionLabel.setVisibility(View.GONE);


        } else {
            materiiListaShow.clear();
            for (Materie m : materiiListaOriginal) {
                if (m.getName().contains(query.toUpperCase())) {
                    materiiListaShow.add(m);
                }

            }
            if (materiiListaShow.size() < 1) {
                instructionLabel.setVisibility(View.VISIBLE);
            } else {
                instructionLabel.setVisibility(View.GONE);
            }
            mAdapter.setmDataset(materiiListaShow);
        }
    }

}
