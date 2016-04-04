package uk.co.edwardquixote.Chaward.kcaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ScrollView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

import uk.co.edwardquixote.Chaward.kcaapp.Dialogs.Dialogs;


public class KCASignUpActivity extends AppCompatActivity {

    private Dialogs clsDialogs;

    private ScrollView scvSignUpLayout;

    private TextInputLayout tilStudentID;
    private TextInputLayout tilStudentPassword;

    private EditText edStudentID;
    private EditText edStudentPassword;
    private EditText edStudentEmail;

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

        clsDialogs = new Dialogs(KCASignUpActivity.this);

        scvSignUpLayout = (ScrollView) this.findViewById(R.id.scvSignUp);

        Toolbar tbActionBar = (Toolbar) this.findViewById(R.id.tbSUToolBar);
        this.setSupportActionBar(tbActionBar);

        tilStudentID = (TextInputLayout) this.findViewById(R.id.tilSUUserName);
        tilStudentPassword = (TextInputLayout) this.findViewById(R.id.tilSUPassword);
        tilStudentID.setErrorEnabled(true);
        tilStudentPassword.setErrorEnabled(true);

        edStudentID = (EditText) this.findViewById(R.id.edSUUserName);
        edStudentPassword = (EditText) this.findViewById(R.id.edSUPassword);
        edStudentEmail = (EditText) this.findViewById(R.id.edSUStudentEmail);

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
            tilStudentID.setError(this.getString(R.string.sInvalidID));

            edStudentID.requestFocus();
        } else if (sPassword.equalsIgnoreCase("")) {
            tilStudentPassword.setError(this.getString(R.string.sInvalidPassword));

            edStudentPassword.requestFocus();
        } else {
            tilStudentID.setError("");
            tilStudentPassword.setError("");

            codeToSignUpStudent(sID, sPassword, sEmail);
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
    private void codeToSignUpStudent(final String studentID, final String studentPassword, final String studentEmail) {

        clsDialogs.codeToGenerateProgressDialogs("Loading. . .");

        String sFirebaseReferenceMain = this.getResources().getString(R.string.fbReferenceMain);

        final Firebase fbReferenceMain = new Firebase(sFirebaseReferenceMain);
        fbReferenceMain.createUser(studentEmail, studentPassword, new Firebase.ValueResultHandler<Map<String, Object>>() {

            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                Snackbar.make(scvSignUpLayout, "Sign Up Successful.", Snackbar.LENGTH_LONG).show();

                codeToSignInStudent(fbReferenceMain, studentID, studentPassword, studentEmail);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                clsDialogs.codeToDismissDialogs();

                Snackbar.make(scvSignUpLayout, "Sign Up Unsuccessful. Error: " + firebaseError.getMessage(), Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void codeToSignInStudent(final Firebase firebaseReferenceMain, final String studentID, String studentPassword, final String studentEmail) {

        firebaseReferenceMain.authWithPassword(studentEmail, studentPassword, new Firebase.AuthResultHandler() {

            @Override
            public void onAuthenticated(AuthData authData) {
                Snackbar.make(scvSignUpLayout, "Sign In Successful.", Snackbar.LENGTH_LONG).show();

                codeToSaveStudentData(firebaseReferenceMain, authData, studentID, studentEmail);
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                clsDialogs.codeToDismissDialogs();

                Snackbar.make(scvSignUpLayout, "Sign In Unsuccessful. Error: " + firebaseError.getMessage(), Snackbar.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Code to save Student Data into cloud after Registration and Authentication.
     *
     * @param firebaseReferenceMain (Firebase)
     * @param studentID (String)
     * @param studentEmail  (String)
     */
    private void codeToSaveStudentData(Firebase firebaseReferenceMain, AuthData authData, String studentID, String studentEmail) {

        String sFirebaseRefStudentAccount = this.getResources().getString(R.string.fbReferenceStudentAccount);
        String sFirebaseStudentId = authData.getUid();

        Firebase fbREFStudentAccount = firebaseReferenceMain.child(sFirebaseStudentId).child(sFirebaseRefStudentAccount);
        fbREFStudentAccount.child(this.getString(R.string.fbREFStudentID)).setValue(studentID, fbclSaveCompleteListener);
        fbREFStudentAccount.child(this.getString(R.string.fbREFStudentEmail)).setValue(studentEmail, fbclSaveCompleteListener);
    }

    /**
     * Starts Home Activity.
     *
     * Called in codeToValidateStudentDetailsAndSignIn();
     */
    private void codeToStartHomeActivity() {

        Intent inStartHome = new Intent(KCASignUpActivity.this, KCAHomeActivity.class);
        this.finish();
        startActivity(inStartHome);

    }


    /**
     * Firebase.CompletionListener for the details saved on Sign Up.
     * Reports when an object has been saved and its write s completed successfully.
     *
     * Implemented in this.codeToSaveStudentData();
     */
    private Firebase.CompletionListener fbclSaveCompleteListener = new Firebase.CompletionListener() {

        @Override
        public void onComplete(FirebaseError firebaseError, Firebase firebase) {
            if (firebaseError != null) {
                clsDialogs.codeToDismissDialogs();

                return;
            } else {
                clsDialogs.codeToDismissDialogs();

                codeToStartHomeActivity();
            }
        }
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
