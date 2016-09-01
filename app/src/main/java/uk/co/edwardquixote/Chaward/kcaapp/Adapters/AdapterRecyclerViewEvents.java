package uk.co.edwardquixote.Chaward.kcaapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uk.co.edwardquixote.Chaward.kcaapp.R;

/**
 * Created by Edward Quixote on 24/06/2015.
 */
public class AdapterRecyclerViewEvents extends BaseAdapter {

    private ViewHolderClass  clsViewHolder;

    private Context coxContext;

    private JSONArray jaryDataArray;

    private int[] iaryImages;

    public AdapterRecyclerViewEvents(Context context, JSONArray jsonArray, int[] images) {
        this.coxContext = context;

        this.jaryDataArray = jsonArray;
        this.iaryImages = images;
    }

    @Override
    public int getCount() {
        return jaryDataArray.length();
    }

    @Override
    public Object getItem(int position) {

        Object object = null;
        try {
            object = jaryDataArray.get(position);
        } catch (JSONException jsex) {
            jsex.printStackTrace();
        }

        return object;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(coxContext).inflate(R.layout.rowlayout_recyclerview_events, parent, false);

            clsViewHolder = new ViewHolderClass(convertView);

            convertView.setTag(clsViewHolder);
        } else {
            clsViewHolder = (ViewHolderClass) convertView.getTag();
        }

        codeToParseJSONData(position);

        return convertView;
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
                JSONObject jobJSONEventData = new JSONObject(jaryDataArray.getString(position));
                JSONObject jobJSONEventItem = jobJSONEventData.getJSONObject("Event");

                String sEventTitle = jobJSONEventItem.getString("Event_Title");
                String sEventOrganizer = jobJSONEventItem.getString("Event_Organizer");
                String sEventVenue = jobJSONEventItem.getString("Event_Venue");
                String sEventDate= jobJSONEventItem.getString("Event_Date");

                for (int a = 0; a < iaryImages.length; a++) {
                    clsViewHolder.imgvCoverPhoto.setImageResource(iaryImages[a]);
                }

                clsViewHolder.txtTitle.setText(sEventTitle);
                clsViewHolder.txtOrganizer.setText(sEventOrganizer);
                clsViewHolder.txtVenue.setText(sEventVenue);
                clsViewHolder.txtDate.setText(sEventDate);
            }
        } catch (JSONException jsoex) {
            //  TODO: Handle JSON Error here
            jsoex.printStackTrace();
        }

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
    public static class ViewHolderClass {

        ImageView imgvCoverPhoto;

        TextView txtTitle;
        TextView txtOrganizer;
        TextView txtVenue;
        TextView txtDate;

        public ViewHolderClass(View itemView) {

            imgvCoverPhoto = (ImageView) itemView.findViewById(R.id.imgvEventsCoverPhoto);

            txtTitle = (TextView) itemView.findViewById(R.id.txtEventsTitle);
            txtOrganizer = (TextView) itemView.findViewById(R.id.txtEventsOrganizer);
            txtVenue = (TextView) itemView.findViewById(R.id.txtEventsVenue);
            txtDate = (TextView) itemView.findViewById(R.id.txtEventsDate);

        }
    }

}
