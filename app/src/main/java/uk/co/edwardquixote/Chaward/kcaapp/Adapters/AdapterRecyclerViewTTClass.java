package uk.co.edwardquixote.Chaward.kcaapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import org.json.JSONArray;

import uk.co.edwardquixote.Chaward.kcaapp.R;

/**
 * Created by Edward Quixote on 14/07/2015.
 */
public class AdapterRecyclerViewTTClass extends RecyclerView.Adapter<AdapterRecyclerViewTTClass.ViewHolderClass> {

    private AdapterRecyclerViewTTClass.ViewHolderClass clsViewHolder;

    private View vRVLayout;

    private JSONArray jaryJSONData;

    public AdapterRecyclerViewTTClass() {}

    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {

        vRVLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout_recyclerview_tt_class, parent, false);

        clsViewHolder = new ViewHolderClass(vRVLayout);

        return clsViewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolderClass holder, int position) {
        //  TODO: Set Data here
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    /**
     * ViewHolder Class to hold Views in my layout.
     * This class extends the RecyclerView.ViewHolder class,
     * which helps is managing and recycling views.
     *
     * @author Edward Quixote
     * Created on 14th July 2015
     * At 11:13PM
     */
    public static class ViewHolderClass extends RecyclerView.ViewHolder {

        private TextView txtUnitName;
        private TextView txtLecturer;
        private TextView txtTime;
        private TextView txtRoom;

        private CheckBox cboMakeUp;

        public ViewHolderClass(View itemView) {
            super(itemView);

            txtUnitName = (TextView) itemView.findViewById(R.id.txtTTClassUnitName);
            txtLecturer = (TextView) itemView.findViewById(R.id.txtTTClassLecturer);
            txtTime = (TextView) itemView.findViewById(R.id.txtTTClassTime);
            txtRoom = (TextView) itemView.findViewById(R.id.txtTTClassRoom);

            cboMakeUp = (CheckBox) itemView.findViewById(R.id.cboTTClassMakeUp);

        }

    }

}
