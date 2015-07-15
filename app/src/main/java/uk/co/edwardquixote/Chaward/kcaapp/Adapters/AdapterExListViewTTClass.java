package uk.co.edwardquixote.Chaward.kcaapp.Adapters;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import uk.co.edwardquixote.Chaward.kcaapp.DataBeans.BeanExListViewTTClass;
import uk.co.edwardquixote.Chaward.kcaapp.R;

/**
 * Created by Edward Quixote on 14/07/2015.
 */
public class AdapterExListViewTTClass extends BaseExpandableListAdapter {

    private Context coxContext;

    private LayoutInflater linInflater;

    private ArrayList<BeanExListViewTTClass> arylstGroups;

    public AdapterExListViewTTClass(Context context, ArrayList<BeanExListViewTTClass> groups) {
        this.coxContext = context;

        this.arylstGroups = groups;

        this.linInflater = (LayoutInflater) coxContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return arylstGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return arylstGroups.get(groupPosition).arylstChildren.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return arylstGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return arylstGroups.get(groupPosition).arylstChildren.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        convertView = linInflater.inflate(R.layout.grouplayout_exlistview_tt_class, parent, false);

        BeanExListViewTTClass clsBean = (BeanExListViewTTClass) getGroup(groupPosition);

        TextView txtTextView = (TextView) convertView.findViewById(R.id.txtTTClassDay);
        txtTextView.setText(clsBean.sGroupName);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        convertView = linInflater.inflate(R.layout.childlayout_exlistview_tt_class, parent, false);

        RecyclerView.Adapter adpRVTTClass = new AdapterRecyclerViewTTClass();
        RecyclerView.LayoutManager rvlmTTClass = new LinearLayoutManager(convertView.getContext());
        RecyclerView rvTTClass = (RecyclerView) convertView.findViewById(R.id.rvTTClass);
        rvTTClass.setLayoutManager(rvlmTTClass);
        rvTTClass.setAdapter(adpRVTTClass);
        rvTTClass.setItemAnimator(new DefaultItemAnimator());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
