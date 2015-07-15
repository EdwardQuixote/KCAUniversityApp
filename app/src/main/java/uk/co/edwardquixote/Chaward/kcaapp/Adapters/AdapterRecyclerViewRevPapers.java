package uk.co.edwardquixote.Chaward.kcaapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uk.co.edwardquixote.Chaward.kcaapp.R;

/**
 * Created by Edward Quixote on 09/07/2015.
 */
public class AdapterRecyclerViewRevPapers extends RecyclerView.Adapter<AdapterRecyclerViewRevPapers.ViewHolderClass> {

    private ViewHolderClass clsViewHolder;

    private View vRVLayout;

    private JSONArray jaryDataArray;
    private JSONObject jobJSONBookData;

    public AdapterRecyclerViewRevPapers(JSONArray jsonArray ) {
        this.jaryDataArray = jsonArray;
    }

    @Override
    public AdapterRecyclerViewRevPapers.ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {

        vRVLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout_recyclerview_revpapers, parent, false);

        clsViewHolder = new ViewHolderClass(vRVLayout);

        return clsViewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterRecyclerViewRevPapers.ViewHolderClass holder, int position) {

        codeToParseJSONData(position);

    }


    /**
     * This method reads JSON Data from a file currently,
     * Then Parses it and Displays it on respective TextViews.
     *
     * //   TODO: Change this once i get server.
     *
     * Called in onBindViewHolder();
     */
    private void codeToParseJSONData(int position) {

        try {
            for (int i = 0; i < jaryDataArray.length(); i++) {
                jobJSONBookData = new JSONObject(jaryDataArray.getString(position));
                JSONObject jobJSONBookItem = jobJSONBookData.getJSONObject("Book");

                String sBookTitle = jobJSONBookItem.getString("Book_Title");
                String sBookEdition = jobJSONBookItem.getString("Book_Edition");
                String sBookSubtitle = jobJSONBookItem.getString("Book_Subtitle");
                String sBookAuthor = jobJSONBookItem.getString("Book_Author");
                String sBookDate = jobJSONBookItem.getString("Book_Date");

                clsViewHolder.txtTitleEdVol.setText(sBookTitle + " Ed.: " + sBookEdition);
                clsViewHolder.txtSubtitle.setText(sBookSubtitle);
                clsViewHolder.txtAuthor.setText(sBookAuthor);
                clsViewHolder.txtDate.setText(sBookDate);
            }
        } catch (JSONException jsoex) {
            //  TODO: Handle JSON Error here
            jsoex.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {
        return jaryDataArray.length();
    }

    public static class ViewHolderClass extends RecyclerView.ViewHolder {

        TextView txtTitleEdVol;
        TextView txtSubtitle;
        TextView txtAuthor;
        TextView txtDate;

        public ViewHolderClass(View itemView) {
            super(itemView);

            txtTitleEdVol = (TextView) itemView.findViewById(R.id.txtRevPapersTitleEditionVolume);
            txtSubtitle = (TextView) itemView.findViewById(R.id.txtRevPapersSubtitle);
            txtAuthor = (TextView) itemView.findViewById(R.id.txtRevPapersAuthor);
            txtDate = (TextView) itemView.findViewById(R.id.txtRevPapersDatePublished);

        }
    }

}
