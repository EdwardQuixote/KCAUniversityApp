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
    private JSONObject jobJSONPapersData;

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
                jobJSONPapersData = new JSONObject(jaryDataArray.getString(position));
                JSONObject jobJSONPapersItem = jobJSONPapersData.getJSONObject("Papers");

                String sPapersTitle = jobJSONPapersItem.getString("Papers_Title");
                String sPapersType = jobJSONPapersItem.getString("Papers_Type");
                String sPapersAuthor = jobJSONPapersItem.getString("Papers_Author");
                String sPapersDate = jobJSONPapersItem.getString("Papers_Date");

                clsViewHolder.txtTitle.setText(sPapersTitle);
                clsViewHolder.txtType.setText(sPapersType);
                clsViewHolder.txtAuthor.setText(sPapersAuthor);
                clsViewHolder.txtDate.setText(sPapersDate);
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

        TextView txtTitle;
        TextView txtType;
        TextView txtAuthor;
        TextView txtDate;

        public ViewHolderClass(View itemView) {
            super(itemView);

            txtTitle = (TextView) itemView.findViewById(R.id.txtRevPapersTitle);
            txtType = (TextView) itemView.findViewById(R.id.txtRevPapersType);
            txtAuthor = (TextView) itemView.findViewById(R.id.txtRevPapersAuthor);
            txtDate = (TextView) itemView.findViewById(R.id.txtRevPapersDatePublished);

        }
    }

}
