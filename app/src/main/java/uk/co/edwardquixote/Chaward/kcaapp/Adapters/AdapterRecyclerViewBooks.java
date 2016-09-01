package uk.co.edwardquixote.Chaward.kcaapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uk.co.edwardquixote.Chaward.kcaapp.R;

/**
 * Created by Edward Quixote on 09/07/2015.
 */
public class AdapterRecyclerViewBooks extends BaseAdapter {

    private ViewHolderClass clsViewHolder;

    private JSONArray jaryDataArray;

    public AdapterRecyclerViewBooks(JSONArray jsonArray) {
        this.jaryDataArray = jsonArray;
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout_recyclerview_books, parent, false);

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
     * Called in this.getView();
     */
    private void codeToParseJSONData(int position) {

        JSONObject jobJSONBookData;
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

    public static class ViewHolderClass {

        TextView txtTitleEdVol;
        TextView txtSubtitle;
        TextView txtAuthor;
        TextView txtDate;

        public ViewHolderClass(View itemView) {

            txtTitleEdVol = (TextView) itemView.findViewById(R.id.txtBooksTitleEditionVolume);
            txtSubtitle = (TextView) itemView.findViewById(R.id.txtBooksSubtitle);
            txtAuthor = (TextView) itemView.findViewById(R.id.txtBooksAuthor);
            txtDate = (TextView) itemView.findViewById(R.id.txtBooksDatePublished);

        }
    }

}
