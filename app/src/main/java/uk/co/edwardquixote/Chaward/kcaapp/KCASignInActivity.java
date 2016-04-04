package uk.co.edwardquixote.Chaward.kcaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import uk.co.edwardquixote.Chaward.kcaapp.Dialogs.Dialogs;


public class KCASignInActivity extends AppCompatActivity {

    private Dialogs clsDialogs;

    private RelativeLayout relaySignInLayout;

    private TextInputLayout tilStudentEmail;
    private TextInputLayout tilStudentPassword;

    private EditText edStudentEmail;
    private EditText edStudentPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kcasign_in);

        initializeVariablesAndUIObjects();

    }


    /**
     * Here I initialize Variables of this Class and UI Objects
     *
     * Called in onCreate();
     */
    private void initializeVariablesAndUIObjects() {

        clsDialogs = new Dialogs(KCASignInActivity.this);

        relaySignInLayout = (RelativeLayout) this.findViewById(R.id.relaySignIn);

        Toolbar tbActionBar = (Toolbar) this.findViewById(R.id.tbSIToolBar);
        this.setSupportActionBar(tbActionBar);

        TextView txtSignUp = (TextView) this.findViewById(R.id.txtSISignUp);
        txtSignUp.setOnClickListener(clkTxtSignUp);

        tilStudentEmail = (TextInputLayout) this.findViewById(R.id.tilSIEmailAddress);
        tilStudentPassword = (TextInputLayout) this.findViewById(R.id.tilSIPassword);
        tilStudentEmail.setErrorEnabled(true);
        tilStudentPassword.setErrorEnabled(true);

        edStudentEmail = (EditText) this.findViewById(R.id.edSIEmailAddress);
        edStudentPassword = (EditText) this.findViewById(R.id.edSIPassword);

    }


    /**
     * This Method verifies that inputed details,
     * match the details previously stored.
     *
     * Called in onOptionsItemSelected.mnuSISignIn
     */
    private void codeToValidateStudentDetailsAndSignIn() {

        String sID = edStudentEmail.getText().toString();
        String sPassword = edStudentPassword.getText().toString();
        if (sID.equalsIgnoreCase("")) {
            tilStudentEmail.setError("Please provide your Student Email!");

            edStudentEmail.requestFocus();
        } else if (sPassword.equalsIgnoreCase("")) {
            tilStudentPassword.setError("Please provide your Password!");

            edStudentPassword.requestFocus();
        } else {
            tilStudentEmail.setError("");
            tilStudentPassword.setError("");

            codeToSignInStudent(sID, sPassword);
        }

    }

    private void codeToSignInStudent(String studentID, String studentPassword) {

        clsDialogs.codeToGenerateProgressDialogs("Signing you in. . .");

        String sFirebaseReferenceMain = this.getResources().getString(R.string.fbReferenceMain);

        Firebase fbReferenceMain = new Firebase(sFirebaseReferenceMain);
        fbReferenceMain.authWithPassword(studentID, studentPassword, new Firebase.AuthResultHandler() {

            @Override
            public void onAuthenticated(AuthData authData) {
                clsDialogs.codeToDismissDialogs();

                Snackbar.make(relaySignInLayout, "Sign In Successful.", Snackbar.LENGTH_LONG).show();

                codeToStartHomeActivity();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                clsDialogs.codeToDismissDialogs();

                Snackbar.make(relaySignInLayout, "Sign In Unsuccessful. Error: " + firebaseError.getMessage(), Snackbar.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Starts Home Activity.
     *
     * Called in codeToValidateStudentDetailsAndSignIn();
     */
    private void codeToStartHomeActivity() {

        Intent inStartHome = new Intent(KCASignInActivity.this, KCAHomeActivity.class);
        this.finish();
        startActivity(inStartHome);

    }

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
            finish();
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
