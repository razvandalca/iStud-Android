package ro.horiacalin.istud.PresentationLayer.Adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ro.horiacalin.istud.BusinessLayer.Pojo.Materie;
import ro.horiacalin.istud.Constants;
import ro.horiacalin.istud.PresentationLayer.Controller.MaterieActivity;
import ro.horiacalin.istud.R;


public class RecyclerViewAdapterMaterii extends RecyclerView.Adapter<RecyclerViewAdapterMaterii.ViewHolder> {
    private List<Materie> mDataset;
    private Context context;



    public void add(int position, Materie item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(Materie item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapterMaterii(Context context , List<Materie> myDataset) {
        this.mDataset = myDataset;
        this.context=context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapterMaterii.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_materii_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.materieTitluTextView.setText(mDataset.get(position).getName());
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MaterieActivity.class);
                intent.putExtra(Constants.MATERIE_KEY,mDataset.get(position));
                context.startActivity(intent);
            }
        });


    }

    public void setmDataset(List<Materie> mDataset) {
        this.mDataset = mDataset;
        notifyDataSetChanged();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView materieTitluTextView;
        public View root;

        public ViewHolder(View v) {
            super(v);
            materieTitluTextView = (TextView) v.findViewById(R.id.materieTitlu);
            root = v;
        }
    }


}