package uk.co.edwardquixote.Chaward.kcaapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import uk.co.edwardquixote.Chaward.kcaapp.Fragments.FragBookView;
import uk.co.edwardquixote.Chaward.kcaapp.Fragments.FragBooks;
import uk.co.edwardquixote.Chaward.kcaapp.Fragments.FragEventView;
import uk.co.edwardquixote.Chaward.kcaapp.Fragments.FragEvents;
import uk.co.edwardquixote.Chaward.kcaapp.Fragments.FragJournals;
import uk.co.edwardquixote.Chaward.kcaapp.Fragments.FragNews;
import uk.co.edwardquixote.Chaward.kcaapp.Fragments.FragNewsView;
import uk.co.edwardquixote.Chaward.kcaapp.Fragments.FragRevisionNotes;
import uk.co.edwardquixote.Chaward.kcaapp.Fragments.FragRevisionPapers;


public class KCAHomeActivity extends AppCompatActivity implements
        FragBooks.InterfaceFragBooks,
        FragNews.InterfaceFragNews,
        FragEvents.InterfaceFragEvents {

    private FragRevisionNotes clsFragRevNotes;
    private FragRevisionPapers clsFragRevPapers;
    private FragBooks clsFragBooks;
    private FragJournals clsFragJournals;
    private FragNews clsFragNews;
    private FragEvents clsFragEvents;

    private DrawerLayout dlayHome;

    private NavigationView navDrawer;
    private Toolbar tbActionBar;

    private TextView txtDrawerHeader;

    private ActionBarDrawerToggle abdtDrawerToggle;

    private Firebase fbFirebaseReferenceMain;

    private FragmentTransaction ftHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kcahome);

        initializeVariablesAndUIObjects();

        codeToCheckIfStudentIsSignedIn();

        codeToSetUpNavigationDrawer();

        dlayHome.openDrawer(navDrawer);

        ftHome = this.getSupportFragmentManager().beginTransaction();
        ftHome.replace(R.id.relayHomeContainer, clsFragNews);
        ftHome.commit();

        Log.i(KCAHomeActivity.this.toString(), "KCAHomeActivity just called onCreate(). FragNews IS SETUP!");  //  TODO FOR Testing only
    }


    /**
     * This method initializes this Class' Variables And Objects used in the UI.
     *
     * Called in onCreate()
     */
    private void initializeVariablesAndUIObjects() {

        clsFragRevNotes = new FragRevisionNotes();
        clsFragRevPapers = new FragRevisionPapers();
        clsFragBooks = new FragBooks();
        clsFragJournals = new FragJournals();
        clsFragNews = new FragNews();
        clsFragEvents = new FragEvents();

        tbActionBar = (Toolbar) this.findViewById(R.id.tbHomeToolBar);
        this.setSupportActionBar(tbActionBar);

        dlayHome = (DrawerLayout) this.findViewById(R.id.dlayHome);
        navDrawer = (NavigationView) this.findViewById(R.id.navDrawer);
        navDrawer.setNavigationItemSelectedListener(nislNavDrawer);
        navDrawer.getMenu().findItem(R.id.mnuDrawerNews).setChecked(true);

        txtDrawerHeader = (TextView) this.findViewById(R.id.txtDrawerUserName);

    }

    /**
     * This method sets up an ActionBarDrawerToggle for the DrawerLayout.
     *
     * Called in onCreate();
     */
    private void codeToSetUpNavigationDrawer() {

        abdtDrawerToggle = new ActionBarDrawerToggle(this, dlayHome, tbActionBar, R.string.sDrawerOpen, R.string.sDrawerClose) {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);

                supportInvalidateOptionsMenu();

            }

        };

        abdtDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_drawer);

        dlayHome.setDrawerListener(abdtDrawerToggle);

    }

    /**
     * This method checks to see if The Student has an Account already.
     * If yes, it just takes the Student ID and sets it up,
     * on the Drawer.
     * If no, it starts Sign In Activity.
     *
     * Called in onCreateView();
     */
    private void codeToCheckIfStudentIsSignedIn() {

        String sFirebaseReferenceMain = this.getResources().getString(R.string.fbReferenceMain);

        fbFirebaseReferenceMain = new Firebase(sFirebaseReferenceMain);
        AuthData adStudentAuthData = fbFirebaseReferenceMain.getAuth();
        if (adStudentAuthData == null) {
            codeToStartSignInActivity();
        } else {
            codeToRetrieveStudentIDNumber(fbFirebaseReferenceMain, adStudentAuthData);
        }

    }

    /**
     * Method retrieve's user Student ID Number.
     *
     * Called in this.codeToCheckIfStudentIsSignedIn();
     *
     * @param firebaseReferenceMain (Firebase)
     * @param studentAuthData   (AuthData)
     */
    private void codeToRetrieveStudentIDNumber(Firebase firebaseReferenceMain, AuthData studentAuthData) {

        String sStudentFirebaseID = studentAuthData.getUid();
        if (sStudentFirebaseID != null) {
            String sFirebaseRefStudentAccount = this.getResources().getString(R.string.fbReferenceStudentAccount);
            String sFirebaseRefStudentID = this.getResources().getString(R.string.fbReferenceStudentID);

            Firebase fbREFStudentAccount = firebaseReferenceMain.child(sStudentFirebaseID).child(sFirebaseRefStudentAccount);
            Firebase fbREFStudentIDNumber = fbREFStudentAccount.child(sFirebaseRefStudentID);
            fbREFStudentIDNumber.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot != null) {
                        txtDrawerHeader.setText("Reg. No.: " + dataSnapshot.getValue().toString());
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    txtDrawerHeader.setText("Welcome,");
                }
            });
        }
    }


    /**
     * Sets up Fragment Revision Notes on Layout.
     *
     * Called in nislNavDrawer.mnuDrawerRevisionNotes;
     */
    private void codeToSetUpFragmentRevisionNotes() {

        ftHome = this.getSupportFragmentManager().beginTransaction();
        ftHome.replace(R.id.relayHomeContainer, clsFragRevNotes);
        ftHome.addToBackStack(null);
        ftHome.commit();

    }

    /**
     * Sets up Fragment Revision Papers on Layout.
     *
     * Called in nislNavDrawer.mnuDrawerRevisionPapers;
     */
    private void codeToSetUpFragmentRevisionPapers() {

        ftHome = this.getSupportFragmentManager().beginTransaction();
        ftHome.replace(R.id.relayHomeContainer, clsFragRevPapers);
        ftHome.addToBackStack(null);
        ftHome.commit();

    }

    /**
     * Sets up Fragment Books on Layout.
     *
     * Called in nislNavDrawer.mnuDrawerBooks;
     */
    private void codeToSetUpFragmentBooks() {

        ftHome = this.getSupportFragmentManager().beginTransaction();
        ftHome.replace(R.id.relayHomeContainer, clsFragBooks);
        ftHome.addToBackStack(null);
        ftHome.commit();

    }

    /**
     * Sets up Fragment Journals on Layout.
     *
     * Called in nislNavDrawer.mnuDrawerJournals;
     */
    private void codeToSetUpFragmentJournals() {

        ftHome = this.getSupportFragmentManager().beginTransaction();
        ftHome.replace(R.id.relayHomeContainer, clsFragJournals);
        ftHome.addToBackStack(null);
        ftHome.commit();

    }

    /**
     * This method first Checks if Fragment News is in Layout already.
     * Adds Fragment News on Layout.
     *
     * Called in nislNavDrawer.mnuDrawerNews
     */
    private void codeToSetUpFragmentNews() {

        ftHome = this.getSupportFragmentManager().beginTransaction();
        ftHome.replace(R.id.relayHomeContainer, clsFragNews);
        ftHome.addToBackStack(null);
        ftHome.commit();

    }

    /**
     * Adds Fragment Events on Layout.
     *
     * Called in nislNavDrawer.mnuDrawerEvents
     */
    private void codeToSetUpFragmentEvents() {

        ftHome = this.getSupportFragmentManager().beginTransaction();
        ftHome.replace(R.id.relayHomeContainer, clsFragEvents);
        ftHome.addToBackStack(null);
        ftHome.commit();

    }

    /**
     * THis method basically starts the Sign In Activity.
     *
     * Called in nislNavDrawer.mnuDrawerProfile
     */
    private void codeToStartSignInActivity() {

        Intent inStartSignIn = new Intent(KCAHomeActivity.this, KCASignInActivity.class);
        this.finish();
        startActivity(inStartSignIn);

    }

    /**
     * This method starts The TimeTable Activity.
     *
     * Called in nislNavDrawer.mnuDrawerTimeTable
     */
    private void codeToStartTimeTableActivity() {

        Intent inStartTimeTable = new Intent(KCAHomeActivity.this, KCATimetableActivity.class);
        startActivity(inStartTimeTable);

    }


    /**
     * Method to send Feedback.
     * Opens Email with necessary info filled in already.
     *
     * Called in nislNavDrawer.mnuDrawerFeedback
     */
    private void codeToSendFeedback() {

        String[] saryEmailAddress = this.getResources().getStringArray(R.array.saryEmailAddress);

        Intent inSendFeedback = new Intent(Intent.ACTION_SENDTO);
        inSendFeedback.setType("*/*");
        inSendFeedback.setData(Uri.parse("mailto:"));
        inSendFeedback.putExtra(Intent.EXTRA_EMAIL, saryEmailAddress);
        inSendFeedback.putExtra(Intent.EXTRA_SUBJECT, this.getString(R.string.inSendFeedbackSubject));
        inSendFeedback.putExtra(Intent.EXTRA_TEXT, this.getString(R.string.inSendFeedbackBody));
        if (inSendFeedback.resolveActivity(this.getPackageManager()) != null) {
            startActivity(inSendFeedback);
        } else {
            Snackbar.make(dlayHome, R.string.tstNoEmailApp, Snackbar.LENGTH_SHORT).show();
        }

    }


    /**
     * NavigationView.OnNavigationItemSelectedListener for NavigationView on Drawer of KCAHomeActivity.class
     *
     * Implemented in initializeVariablesAndUIObjects();
     */
    private NavigationView.OnNavigationItemSelectedListener nislNavDrawer = new NavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {

            menuItem.setChecked(true);

            int iItemId = menuItem.getItemId();
            switch (iItemId) {
                case R.id.mnuDrawerRevisionNotes:
                    dlayHome.closeDrawer(navDrawer);

                    codeToSetUpFragmentRevisionNotes();
                    return true;

                case R.id.mnuDrawerRevisionPapers:
                    dlayHome.closeDrawer(navDrawer);

                    codeToSetUpFragmentRevisionPapers();
                    return true;

                case R.id.mnuDrawerBooks:
                    dlayHome.closeDrawer(navDrawer);

                    codeToSetUpFragmentBooks();
                    return true;

                case R.id.mnuDrawerJournals:
                    dlayHome.closeDrawer(navDrawer);

                    codeToSetUpFragmentJournals();
                    return true;

                case R.id.mnuDrawerEvents:
                    dlayHome.closeDrawer(navDrawer);

                    codeToSetUpFragmentEvents();
                    return true;

                case R.id.mnuDrawerNews:
                    dlayHome.closeDrawers();

                    codeToSetUpFragmentNews();
                    return true;

                case R.id.mnuDrawerTimeTable:
                    dlayHome.closeDrawer(navDrawer);

                    codeToStartTimeTableActivity();
                    return true;

                case R.id.mnuDrawerSettings:
                    //  TODO: Code for Settings here
                    return true;

                case R.id.mnuDrawerSignOut:
                    if (fbFirebaseReferenceMain.getAuth() != null) {
                        fbFirebaseReferenceMain.unauth();

                        finish();
                    }
                    return true;

                case R.id.mnuDrawerFeedback:
                    dlayHome.closeDrawer(navDrawer);

                    codeToSendFeedback();
                    return true;
            }

            return true;
        }

    };


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        abdtDrawerToggle.onConfigurationChanged(newConfig);

    }

    @Override
    public void onBackPressed() {

        if (dlayHome.isDrawerOpen(GravityCompat.START)) {
            dlayHome.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        abdtDrawerToggle.syncState();

    }

    @Override
    protected void onStart() {
        super.onStart();

        tbActionBar.setTitle(R.string.app_name);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kcahome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void codeToStartFragBookView(int position) {

        //  TODO: Continue from here. Know how int position applies.

        ftHome = this.getSupportFragmentManager().beginTransaction();
        ftHome.replace(R.id.relayHomeContainer, new FragBookView());
        ftHome.addToBackStack(null);
        ftHome.commit();

    }

    @Override
    public void codeToStartFragNewsView(int position) {

        ftHome = this.getSupportFragmentManager().beginTransaction();
        ftHome.replace(R.id.relayHomeContainer, new FragNewsView());
        ftHome.addToBackStack(null);
        ftHome.commit();
    }

    @Override
    public void codeToStartFragEventView(int position) {

        ftHome = this.getSupportFragmentManager().beginTransaction();
        ftHome.replace(R.id.relayHomeContainer, new FragEventView());
        ftHome.addToBackStack(null);
        ftHome.commit();
    }
}
