package ro.horiacalin.istud.PresentationLayer.Controller;


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
import ro.horiacalin.istud.BusinessLayer.Pojo.EventNotif;
import ro.horiacalin.istud.BusinessLayer.Pojo.Materie;
import ro.horiacalin.istud.PresentationLayer.Adapters.RecyclerViewAdapterEventsNotifications;
import ro.horiacalin.istud.PresentationLayer.Adapters.RecyclerViewAdapterMaterii;
import ro.horiacalin.istud.R;

public class FragmentNotificari extends android.support.v4.app.Fragment {

    private TextView instructionLabel;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterEventsNotifications mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<EventNotif> eventNotifList= new ArrayList<>();



    public FragmentNotificari() {


    }

    public static FragmentNotificari newInstance() {
        FragmentNotificari fragment = new FragmentNotificari();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notificari, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewNotificari);
        instructionLabel = (TextView) rootView.findViewById(R.id.swypeToRefreshLabel);
        if(ToolsManager.getInstance().getNotifEvents(getActivity().getApplicationContext())!=null){
            eventNotifList.clear();
            eventNotifList.addAll(ToolsManager.getInstance().getNotifEvents(getActivity().getApplicationContext()));
        }

        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new RecyclerViewAdapterEventsNotifications(getActivity(),eventNotifList );

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        if(mAdapter.getItemCount()<1){
            instructionLabel.setVisibility(View.VISIBLE);
        }
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.color_ntb_icon_inactive, R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                instructionLabel.setVisibility(View.GONE);
                mAdapter.setmDataset(ToolsManager.getInstance().getNotifEvents(getActivity().getApplicationContext()));
                swipeRefreshLayout.setRefreshing(false);
                if(mAdapter.getItemCount()<1){
                    instructionLabel.setVisibility(View.VISIBLE);
                }


            }
        });






        return rootView;
    }





}
