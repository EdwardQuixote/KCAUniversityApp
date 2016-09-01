package uk.co.edwardquixote.Chaward.kcaapp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import uk.co.edwardquixote.Chaward.kcaapp.R;

/**
 * Created by Edward Quixote on 12/04/2016.
 */
public class FragEventView extends Fragment {

    private final static String sJS_INTERFACE = "uk.co.edwardquixote.Chaward.kcaapp.Fragments.JSINTERFACE_NAME_3";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View vFragEventsView = inflater.inflate(R.layout.frag_event_view, container, false);

        initializeVariablesAndUIObjects(vFragEventsView);

        return vFragEventsView;
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

        String sWebFilePath = "https://www.eventbrite.com";   //  TODO: Testing Page. Change with Actual

        WebView webContentView = (WebView) fragmentLayout.findViewById(R.id.webEventsView);
        webContentView.loadUrl(sWebFilePath);
        WebSettings wesWebSettings = webContentView.getSettings();
        wesWebSettings.setJavaScriptEnabled(true);
        webContentView.addJavascriptInterface(new EventsWebView2JSInterface(getActivity(), getActivity(), webContentView), sJS_INTERFACE);
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    private class EventsWebView2JSInterface {

        private Context coxContext;
        private Activity actActivity;

        private WebView webWebView;

        public EventsWebView2JSInterface(Context context, Activity activity, WebView webView) {
            this.coxContext = context;
            this.actActivity = activity;

            this.webWebView = webView;
        }

        @JavascriptInterface
        public void showToast(String message) {

            this.actActivity.runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    if (!webWebView.getUrl().contains("http://www.eventbrite.com")) {
                        //  If Site doesn't belong to Google, return without doing anything else.
                        //  This improves Security since JS can mess up with your Android Code.
                        return;
                    }
                }
            });

            message = "This is a JavaScript Interface running on Android.";
            Toast.makeText(coxContext, message, Toast.LENGTH_SHORT).show();

        }

    }
}
