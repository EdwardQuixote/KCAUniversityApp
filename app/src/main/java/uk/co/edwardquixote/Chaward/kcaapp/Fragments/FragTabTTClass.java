package uk.co.edwardquixote.Chaward.kcaapp.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;

import uk.co.edwardquixote.Chaward.kcaapp.Adapters.AdapterExListViewTTClass;
import uk.co.edwardquixote.Chaward.kcaapp.DataBeans.BeanExListViewTTClass;
import uk.co.edwardquixote.Chaward.kcaapp.R;

/**
 * Created by Edward Quixote on 14/07/2015.
 */
public class FragTabTTClass extends Fragment {

    private View vFragClass;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        vFragClass = inflater.inflate(R.layout.frag_timetable_class, container, false);

        initializeVariablesAndUIObjects();

        return vFragClass;
    }


    /**
     * This method initializes Variables used in this Class,
     * and UI Objects.
     *
     * Called in onCreateView();
     */
    private void initializeVariablesAndUIObjects() {

        ArrayList<BeanExListViewTTClass> arylstDays = new BeanExListViewTTClass(null).codeToPrepareAndLoadData();
        AdapterExListViewTTClass clsAdapter = new AdapterExListViewTTClass(getActivity(), arylstDays);

        ExpandableListView exlstClasses = (ExpandableListView) vFragClass.findViewById(R.id.exlstTTClass);
        exlstClasses.setAdapter(clsAdapter);
        exlstClasses.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                System.out.println("onGroupClickListener Called");  //  TODO: For Testing ONLY

                return false;
            }
        });

        System.out.println("ExpandableListView set up, with Adapter and onGroupClickListener()");   //  TODO: For Testing ONLY

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
