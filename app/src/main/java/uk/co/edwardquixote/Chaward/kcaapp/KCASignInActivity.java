package uk.co.edwardquixote.Chaward.kcaapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class KCASignInActivity extends AppCompatActivity {

    private Toolbar tbActionBar;

    private TextView txtSignUp;

    private TextInputLayout tilStudentID;
    private TextInputLayout tilStudentPassword;

    private EditText edStudentID;
    private EditText edStudentPassword;

    private SharedPreferences sprefStudentAccount;

    private String sStudentAccount_KEY;
    private String sStudentID_KEY;
    private String sStudentPassword_KEY;
    private String sStudentID;
    private String sStudentPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kcasign_in);

        initializeVariablesAndUIObjects();

        codeToGetStudentCredentials();

    }


    /**
     * Here I initialize Variables of this Class and UI Objects
     *
     * Called in onCreate();
     */
    private void initializeVariablesAndUIObjects() {

        sStudentAccount_KEY = this.getResources().getString(R.string.sprefStudentAccount);
        sStudentID_KEY = this.getResources().getString(R.string.sprefStudentID);
        sStudentPassword_KEY = this.getResources().getString(R.string.sprefStudentPassword);

        tbActionBar = (Toolbar) this.findViewById(R.id.tbSIToolBar);
        this.setSupportActionBar(tbActionBar);

        txtSignUp = (TextView) this.findViewById(R.id.txtSISignUp);
        txtSignUp.setOnClickListener(clkTxtSignUp);

        tilStudentID = (TextInputLayout) this.findViewById(R.id.tilSIUserName);
        tilStudentPassword = (TextInputLayout) this.findViewById(R.id.tilSIPassword);
        tilStudentID.setErrorEnabled(true);
        tilStudentPassword.setErrorEnabled(true);

        edStudentID = (EditText) this.findViewById(R.id.edSIUserName);
        edStudentPassword = (EditText) this.findViewById(R.id.edSIPassword);
        edStudentID.addTextChangedListener(twtEdStudentID);
        edStudentPassword.addTextChangedListener(twtEdStudentPassword);

    }

    /**
     * This method retrieves the Saved Data,
     * when this activity launches.
     *
     * Called in onCreate();
     */
    private void codeToGetStudentCredentials() {

        sprefStudentAccount = this.getSharedPreferences(sStudentAccount_KEY, Context.MODE_PRIVATE);
        sStudentID = sprefStudentAccount.getString(sStudentID_KEY, null);
        sStudentPassword = sprefStudentAccount.getString(sStudentPassword_KEY, null);

    }


    /**
     * This Method verifies that inputed details,
     * match the details previously stored.
     *
     * Called in onOptionsItemSelected.mnuSISignIn
     */
    private void codeToValidateStudentDetailsAndSignIn() {

        String sID = edStudentID.getText().toString();
        String sPassword = edStudentPassword.getText().toString();
        if (!sID.equalsIgnoreCase(sStudentID)) {
            AlertDialog.Builder adbldDialog = new AlertDialog.Builder(this);
            adbldDialog.setTitle("Sign In");
            adbldDialog.setMessage(R.string.sInvalidID);
            adbldDialog.setIcon(android.R.drawable.ic_dialog_alert);

            AlertDialog adgDialog = adbldDialog.create();
            adgDialog.show();
        } else if (!sPassword.equalsIgnoreCase(sStudentPassword)) {
            AlertDialog.Builder adbldDialog = new AlertDialog.Builder(this);
            adbldDialog.setTitle("Sign In");
            adbldDialog.setMessage(R.string.sInvalidPassword);
            adbldDialog.setIcon(android.R.drawable.ic_dialog_alert);

            AlertDialog adgDialog = adbldDialog.create();
            adgDialog.show();
        } else {
            codeToStartHomeActivity();
            this.finish();
        }

    }

    /**
     * Starts Home Activity.
     *
     * Called in codeToValidateStudentDetailsAndSignIn();
     */
    private void codeToStartHomeActivity() {

        Intent inStartHome = new Intent(KCASignInActivity.this, KCAHomeActivity.class);
        startActivity(inStartHome);

    }


    /**
     * TextWatcher meant for EditText Student ID.
     * As the user types,
     * it compares the value typed to the one stored.
     * If they don't match, it shows an Error.
     *
     * Implemented in initializeVariablesAndUIObjects();
     */
    private TextWatcher twtEdStudentID = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (!s.toString().equalsIgnoreCase(sStudentID)) {
                tilStudentID.setError(getResources().getString(R.string.sInvalidID));
            }

        }

        @Override
        public void afterTextChanged(Editable s) {}

    };

    /**
     * TextWatcher meant for EditText Student Password.
     * As the user types,
     * it compares the value typed to the one stored.
     * If they don't match, it shows an Error.
     *
     * Implemented in initializeVariablesAndUIObjects();
     */
    private TextWatcher twtEdStudentPassword = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (!s.toString().equalsIgnoreCase(sStudentPassword)) {
                tilStudentPassword.setError(getResources().getString(R.string.sInvalidPassword));
            }

        }

        @Override
        public void afterTextChanged(Editable s) {}

    };

    /**
     * This OnCLickListener is for Sign Up TextView.
     * Basically starts Sign Up Activity.
     *
     * Implemented in initializeVariablesAndUIObjects();
     */
    private View.OnClickListener clkTxtSignUp = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Intent inStartSignUp = new Intent(KCASignInActivity.this, KCASignUpActivity.class);
            startActivity(inStartSignUp);

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kcasign_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.mnuSISignIn:
                codeToValidateStudentDetailsAndSignIn();
                return true;

            case R.id.mnuSICancel:
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
