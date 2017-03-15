package ro.horiacalin.istud.PresentationLayer.Adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.List;

import ro.horiacalin.istud.BusinessLayer.Pojo.EventNotif;
import ro.horiacalin.istud.BusinessLayer.Pojo.Materie;
import ro.horiacalin.istud.BusinessLayer.Pojo.Scheduale;
import ro.horiacalin.istud.Constants;
import ro.horiacalin.istud.PresentationLayer.Controller.MaterieActivity;
import ro.horiacalin.istud.R;


public class RecyclerViewAdapterEventsNotifications extends RecyclerView.Adapter<RecyclerViewAdapterEventsNotifications.ViewHolder> {
    private List<EventNotif> mDataset;
    private Context context;



    public void add(int position, EventNotif item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(Materie item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    public RecyclerViewAdapterEventsNotifications(Context context , List<EventNotif> myDataset) {
        this.mDataset = myDataset;
        this.context=context;
    }

    @Override
    public RecyclerViewAdapterEventsNotifications.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notificari_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final EventNotif eventNotif= (EventNotif) mDataset.get(position);
        holder.notifTitluTextView.setText(eventNotif.getTitle());
        holder.mesaotifTextViewjN.setText(eventNotif.getMessage());





    }

    public void setmDataset(List<EventNotif> mDataset) {
        this.mDataset = mDataset;
        notifyDataSetChanged();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView notifTitluTextView, mesaotifTextViewjN, dataNotif;
        public View root;

        public ViewHolder(View v) {
            super(v);
            notifTitluTextView = (TextView) v.findViewById(R.id.notifTitlu);
            mesaotifTextViewjN = (TextView) v.findViewById(R.id.notifMesaj);
            root = v;
        }
    }


}