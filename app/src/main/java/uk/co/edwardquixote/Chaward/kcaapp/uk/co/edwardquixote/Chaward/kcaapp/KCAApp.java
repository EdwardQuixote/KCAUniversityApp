package uk.co.edwardquixote.Chaward.kcaapp.uk.co.edwardquixote.Chaward.kcaapp;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Application Class for this Application.
 *
 *
 * Created by Edward Quixote,
 * On 31/03/2016,
 * At 08:24PM.
 */
public class KCAApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(KCAApp.this);
    }
}
