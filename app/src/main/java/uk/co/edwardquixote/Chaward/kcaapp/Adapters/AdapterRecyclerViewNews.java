package uk.co.edwardquixote.Chaward.kcaapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import uk.co.edwardquixote.Chaward.kcaapp.R;

/**
 * Created by Edward Quixote on 23/06/2015.
 */
public class AdapterRecyclerViewNews extends RecyclerView.Adapter<AdapterRecyclerViewNews.ViewHolderClass> {

    private ViewHolderClass  clsViewHolder;

    private View vRVLayout;

    private String[] saryItems;

    public AdapterRecyclerViewNews(String[] objects) {
        this.saryItems = objects;
    }

    @Override
    public AdapterRecyclerViewNews.ViewHolderClass onCreateViewHolder(ViewGroup viewGroup, int i) {

        vRVLayout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowlayout_recyclerview_news, viewGroup, false);

        clsViewHolder = new ViewHolderClass(vRVLayout);

        return clsViewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterRecyclerViewNews.ViewHolderClass viewHolder, int i) {

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
     * Created on 23rd June 2015
     * At 11:48PM
     */
    public static class ViewHolderClass extends RecyclerView.ViewHolder {

        private TextView txtTitle;

        public ViewHolderClass(View itemView) {
            super(itemView);

            txtTitle = (TextView) itemView.findViewById(R.id.txtNewsTitle);
        }

    }

}
