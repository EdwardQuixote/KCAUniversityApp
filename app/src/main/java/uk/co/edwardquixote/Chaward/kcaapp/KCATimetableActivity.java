package uk.co.edwardquixote.Chaward.kcaapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import uk.co.edwardquixote.Chaward.kcaapp.Adapters.AdapterViewPagerTimeTable;


public class KCATimetableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kcatimetable);

        initializeVariablesAndUIObjects();

    }


    /**
     * This method initialized Variables used in this Class,
     * and Objects used in the UI.
     *
     * Called in onCreate();
     */
    private void initializeVariablesAndUIObjects() {

        Toolbar tbTimeTable = (Toolbar) this.findViewById(R.id.tbTimeTable);
        this.setSupportActionBar(tbTimeTable);

        ViewPager vprTimeTable = (ViewPager) this.findViewById(R.id.vprTimeTable);
        vprTimeTable.setAdapter(new AdapterViewPagerTimeTable(this.getSupportFragmentManager(), this));

        TabLayout tablayTabs = (TabLayout) this.findViewById(R.id.tablayTimeTable);
        tablayTabs.setupWithViewPager(vprTimeTable);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kcatimetable, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
