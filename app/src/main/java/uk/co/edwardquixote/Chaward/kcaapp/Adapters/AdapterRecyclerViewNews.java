package uk.co.edwardquixote.Chaward.kcaapp.Adapters;

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
 * Created by Edward Quixote on 23/06/2015.
 */
public class AdapterRecyclerViewNews extends BaseAdapter {

    private ViewHolderClass clsViewHolder;

    private JSONArray jaryDataArray;

    private int[] iaryImages;

    public AdapterRecyclerViewNews(JSONArray jsonArray, int[] images) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout_recyclerview_news, parent, false);

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
                JSONObject jobJSONNewsData = new JSONObject(jaryDataArray.getString(position));
                JSONObject jobJSONNewsItem = jobJSONNewsData.getJSONObject("News");

                String sNewsTitle = jobJSONNewsItem.getString("News_Title");
                String sNewsAuthor = jobJSONNewsItem.getString("News_Author");
                String sNewsBrief = jobJSONNewsItem.getString("News_Brief");
                String sNewsDate = jobJSONNewsItem.getString("News_Date");

                for (int a = 0; a < iaryImages.length; a++) {
                    clsViewHolder.imgvCover.setImageResource(iaryImages[a]);
                }

                clsViewHolder.txtTitle.setText(sNewsTitle);
                clsViewHolder.txtAuthor.setText(sNewsAuthor);
                clsViewHolder.txtBrief.setText(sNewsBrief);
                clsViewHolder.txtDate.setText(sNewsDate);
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
     * Created on 23rd June 2015
     * At 11:48PM
     */
    public static class ViewHolderClass {

        ImageView imgvCover;

        TextView txtTitle;
        TextView txtAuthor;
        TextView txtBrief;
        TextView txtDate;

        public ViewHolderClass(View itemView) {

            imgvCover = (ImageView) itemView.findViewById(R.id.imgvNewsCoverPhoto);

            txtTitle = (TextView) itemView.findViewById(R.id.txtNewsTitle);
            txtAuthor = (TextView) itemView.findViewById(R.id.txtNewsAuthor);
            txtBrief = (TextView) itemView.findViewById(R.id.txtNewsBrief);
            txtDate = (TextView) itemView.findViewById(R.id.txtNewsDate);

        }
    }

}
