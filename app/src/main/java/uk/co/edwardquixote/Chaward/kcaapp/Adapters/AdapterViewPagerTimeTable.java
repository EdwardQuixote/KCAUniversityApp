package uk.co.edwardquixote.Chaward.kcaapp.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import uk.co.edwardquixote.Chaward.kcaapp.Fragments.FragTabTTClass;
import uk.co.edwardquixote.Chaward.kcaapp.Fragments.FragTabTTExamsTests;
import uk.co.edwardquixote.Chaward.kcaapp.R;

/**
 * Created by Edward Quixote on 14/07/2015.
 */
public class AdapterViewPagerTimeTable extends FragmentStatePagerAdapter {

    private static final int iCOUNT_OF_TABS = 2;

    private Context coxContext;

    public AdapterViewPagerTimeTable(FragmentManager fm, Context context) {
        super(fm);

        this.coxContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return (new FragTabTTClass());

            case 1:
                return (new FragTabTTExamsTests());
        }

        return null;
    }

    @Override
    public int getCount() {
        return iCOUNT_OF_TABS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);

        CharSequence csTabTitle = null;

        switch (position) {
            case 0: //  Class TimeTable Tab
                csTabTitle = coxContext.getResources().getString(R.string.tabClass);
                return csTabTitle;

            case 1: //  Exams and Tasks TimeTable Tab
                csTabTitle = coxContext.getResources().getString(R.string.tabExamsTests);
                return csTabTitle;
        }

        return csTabTitle;
    }

}
