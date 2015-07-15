package uk.co.edwardquixote.Chaward.kcaapp.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;

import uk.co.edwardquixote.Chaward.kcaapp.R;

/**
 * Created by Edward Quixote on 14/07/2015.
 */
public class FragTabTTExamsTests extends Fragment {

    private View vFragExams;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        vFragExams = inflater.inflate(R.layout.frag_timetable_examstests, container, false);

        initializeVariablesAndUIObjects();

        return vFragExams;
    }


    /**
     * This method initializes The Variables used in this Class,
     * And UI Objects.
     *
     * Called in onCreateView();
     */
    private void initializeVariablesAndUIObjects() {

        Calendar calCalender = Calendar.getInstance();
        calCalender.add(Calendar.YEAR, 1);
        Date dateToday = new Date();

        CalendarPickerView cpvExamsCalendar = (CalendarPickerView) vFragExams.findViewById(R.id.cpvTTExamsCalender);
        cpvExamsCalendar.init(dateToday, calCalender.getTime()).withSelectedDate(dateToday);

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
