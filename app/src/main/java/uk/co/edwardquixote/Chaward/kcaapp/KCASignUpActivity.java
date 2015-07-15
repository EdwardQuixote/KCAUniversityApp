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
import android.widget.EditText;


public class KCASignUpActivity extends AppCompatActivity {

    private Toolbar tbActionBar;

    private TextInputLayout tilStudentID;
    private TextInputLayout tilStudentPassword;
    private TextInputLayout tilStudentEmail;

    private EditText edStudentID;
    private EditText edStudentPassword;
    private EditText edStudentEmail;

    private SharedPreferences sprefStudentAccount;
    private SharedPreferences.Editor spredEditor;

    private String sStudentAccount_KEY;
    private String sStudentID_KEY;
    private String sStudentPassword_KEY;
    private String sStudentEmail_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kcasign_up);

        initializeVariablesAndUIObjects();

    }


    /**
     * Here I initialize Variables of this class and UI Objects.
     *
     * Called in onCreate();
     */
    private void initializeVariablesAndUIObjects() {

        sStudentAccount_KEY = this.getResources().getString(R.string.sprefStudentAccount);
        sStudentID_KEY = this.getResources().getString(R.string.sprefStudentID);
        sStudentPassword_KEY = this.getResources().getString(R.string.sprefStudentPassword);
        sStudentEmail_KEY = this.getResources().getString(R.string.sprefStudentEmail);

        sprefStudentAccount = this.getSharedPreferences(sStudentAccount_KEY, Context.MODE_PRIVATE);

        tbActionBar = (Toolbar) this.findViewById(R.id.tbSUToolBar);
        this.setSupportActionBar(tbActionBar);

        tilStudentID = (TextInputLayout) this.findViewById(R.id.tilSUUserName);
        tilStudentPassword = (TextInputLayout) this.findViewById(R.id.tilSUPassword);
        tilStudentEmail = (TextInputLayout) this.findViewById(R.id.tilStudentEmail);
        tilStudentID.setErrorEnabled(true);
        tilStudentPassword.setErrorEnabled(true);
        tilStudentEmail.setErrorEnabled(true);

        edStudentID = (EditText) this.findViewById(R.id.edSUUserName);
        edStudentPassword = (EditText) this.findViewById(R.id.edSUPassword);
        edStudentEmail = (EditText) this.findViewById(R.id.edSUStudentEmail);
        edStudentID.addTextChangedListener(twtEdStudentID);
        edStudentPassword.addTextChangedListener(twtEdStudentPassword);

    }

    /**
     * This method checks if Data inputed,
     * matches the data already stored.
     * If yes, it proceeds to save and start Home.
     * If no, it shows dialog.
     *
     * Called in onOptionsItemSelected.mnuSUSignUp
     */
    private void codeToValidateStudentCredentials() {

        String sID = edStudentID.getText().toString();
        String sPassword = edStudentPassword.getText().toString();
        String sEmail = edStudentEmail.getText().toString();
        if (sID.equalsIgnoreCase("")) {
            AlertDialog.Builder adbldDialog = new AlertDialog.Builder(this);
            adbldDialog.setTitle("Sign Up");
            adbldDialog.setMessage(R.string.sInvalidID);
            adbldDialog.setIcon(android.R.drawable.ic_dialog_alert);

            AlertDialog adgDialog = adbldDialog.create();
            adgDialog.show();
        } else if (sPassword.equalsIgnoreCase("")) {
            AlertDialog.Builder adbldDialog = new AlertDialog.Builder(this);
            adbldDialog.setTitle("Sign Up");
            adbldDialog.setMessage(R.string.sInvalidPassword);
            adbldDialog.setIcon(android.R.drawable.ic_dialog_alert);

            AlertDialog adgDialog = adbldDialog.create();
            adgDialog.show();
        } else {
            codeToSaveDetails(sID, sPassword, sEmail);
            codeToStartHomeActivity();
            this.finish();
        }

    }

    /**
     * This method simply loads data into the SharedPreference File,
     * and Saves it.
     *
     * Called in codeToValidateStudentCredentials();
     *
     * @param studentID (String)
     * @param studentPassword (String)
     * @param studentEmail (String)
     */
    private void codeToSaveDetails(String studentID, String studentPassword, String studentEmail) {

        spredEditor = sprefStudentAccount.edit();
        spredEditor.putString(sStudentID_KEY, studentID);
        spredEditor.putString(sStudentPassword_KEY, studentPassword);
        spredEditor.putString(sStudentEmail_KEY, studentEmail);
        spredEditor.commit();

    }

    /**
     * Starts Home Activity.
     *
     * Called in codeToValidateStudentDetailsAndSignIn();
     */
    private void codeToStartHomeActivity() {

        Intent inStartHome = new Intent(KCASignUpActivity.this, KCAHomeActivity.class);
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
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

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
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {}

    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kcasign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.mnuSUSignUp:
                codeToValidateStudentCredentials();
                return true;

            case R.id.mnuSUCancel:
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
