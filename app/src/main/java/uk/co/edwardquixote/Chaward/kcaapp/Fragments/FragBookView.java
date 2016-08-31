package uk.co.edwardquixote.Chaward.kcaapp.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import uk.co.edwardquixote.Chaward.kcaapp.R;

/**
 * Created by Edward Quixote on 04/04/2016.
 */
public class FragBookView extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View vFragBookView = inflater.inflate(R.layout.frag_book_view, container, false);

        initializeVariablesAndUIObjects(vFragBookView);

        Log.w(FragBookView.this.toString(), "Fragment FragBookView onCreateView() called! and Stuff already Initialized");//  TODO: FOR TESTING ONLY.

        return vFragBookView;
    }


    /**
     * Method to initialize Variables and Objects
     * used in the UI.
     *
     * Called in this.onCreateView();
     *
     * @param fragmentLayout (View)
     */
    private void initializeVariablesAndUIObjects(View fragmentLayout) {

        String sWebFilePath = "file:///android_asset/web/software_at_a_glance.html";   //  TODO: Testing Page. Change with Actual

        WebView webContentView = (WebView) fragmentLayout.findViewById(R.id.webBookView);
        webContentView.loadUrl(sWebFilePath);
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
