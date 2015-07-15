package uk.co.edwardquixote.Chaward.kcaapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import uk.co.edwardquixote.Chaward.kcaapp.R;

/**
 * Created by Edward Quixote on 24/06/2015.
 */
public class AdapterRecyclerViewEvents extends RecyclerView.Adapter<AdapterRecyclerViewEvents.ViewHolderClass> {

    private ViewHolderClass  clsViewHolder;

    private Context coxContext;

    private View vRVLayout;

    private String[] saryItems;

    public AdapterRecyclerViewEvents(Context context, String[] objects) {
        this.coxContext = context;

        this.saryItems = objects;
    }

    @Override
    public AdapterRecyclerViewEvents.ViewHolderClass onCreateViewHolder(ViewGroup viewGroup, int i) {

        vRVLayout = LayoutInflater.from(coxContext).inflate(R.layout.rowlayout_recyclerview_events, viewGroup, false);

        clsViewHolder = new ViewHolderClass(vRVLayout);

        return clsViewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterRecyclerViewEvents.ViewHolderClass viewHolderClass, int i) {

        clsViewHolder.txtTitle.setText(saryItems[i]);   //  TODO: For Testing ONLY

    }

    @Override
    public int getItemCount() {
        return saryItems.length;
    }

    /**
     * ViewHolder Class to hold Views in my layout.
     * This class extends the RecyclerView.ViewHolder class,
     * which helps is managing and recycling views.
     *
     * @author Edward Quixote
     * Created on 24rd June 2015
     * At 08:49AM
     */
    public static class ViewHolderClass extends RecyclerView.ViewHolder {

        private TextView txtTitle;

        public ViewHolderClass(View itemView) {
            super(itemView);

            txtTitle = (TextView) itemView.findViewById(R.id.txtEventsTitle);

        }

    }

}
