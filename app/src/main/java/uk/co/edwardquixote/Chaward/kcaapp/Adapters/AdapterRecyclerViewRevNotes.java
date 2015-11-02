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
 * Adapter Class for RecyclerView on Fragment Revision Notes.
 * Class Extends RecyclerView.Adapter<ViewHolder> Class.
 *
 * Created by Edward Quixote
 * On 07/07/2015
 * At 12:30PM.
 */
public class AdapterRecyclerViewRevNotes extends RecyclerView.Adapter<AdapterRecyclerViewRevNotes.ViewHolderClass> {

    private ViewHolderClass clsViewHolder;

    private View vRVLayout;

    private JSONArray jaryDataArray;
    private JSONObject jobJSONNotesData;

    public AdapterRecyclerViewRevNotes(JSONArray jsonArray) {
        this.jaryDataArray = jsonArray;
    }

    @Override
    public AdapterRecyclerViewRevNotes.ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {

        vRVLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout_recyclerview_revnotes, parent, false);

        clsViewHolder = new ViewHolderClass(vRVLayout);

        return clsViewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterRecyclerViewRevNotes.ViewHolderClass holder, int position) {

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
                jobJSONNotesData = new JSONObject(jaryDataArray.getString(position));
                JSONObject jobJSONNotesItem = jobJSONNotesData.getJSONObject("Notes");

                String sNoteTitle = jobJSONNotesItem.getString("Notes_Title");
                String sNoteSubtitle = jobJSONNotesItem.getString("Notes_Subtitle");
                String sNotesUnit = jobJSONNotesItem.getString("Notes_Unit");
                String sNoteAuthor = jobJSONNotesItem.getString("Notes_Author");

                clsViewHolder.txtTitleEdVol.setText(sNoteTitle);
                clsViewHolder.txtSubtitle.setText(sNoteSubtitle);
                clsViewHolder.txtUnit.setText(sNotesUnit);
                clsViewHolder.txtAuthor.setText(sNoteAuthor);
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
        TextView txtUnit;

        public ViewHolderClass(View itemView) {
            super(itemView);

            txtTitleEdVol = (TextView) itemView.findViewById(R.id.txtRevNotesTitle);
            txtSubtitle = (TextView) itemView.findViewById(R.id.txtRevNotesSubtitle);
            txtUnit = (TextView) itemView.findViewById(R.id.txtRevNotesUnit);
            txtAuthor = (TextView) itemView.findViewById(R.id.txtRevNotesAuthor);

        }
    }

}
