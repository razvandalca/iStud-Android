package ro.horiacalin.istud;


import java.util.ArrayList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;


public class RecyclerViewAdapterMaterii extends RecyclerView.Adapter<RecyclerViewAdapterMaterii.ViewHolder> {
    private ArrayList<Materie> mDataset;
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
    public RecyclerViewAdapterMaterii(Context context , ArrayList<Materie> myDataset) {
        this.mDataset = myDataset;
        this.context=context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapterMaterii.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_materii_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.materieTitluTextView.setText(mDataset.get(position).getName());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView materieTitluTextView;

        public ViewHolder(View v) {
            super(v);
            materieTitluTextView = (TextView) v.findViewById(R.id.materieTitlu);
        }
    }

}