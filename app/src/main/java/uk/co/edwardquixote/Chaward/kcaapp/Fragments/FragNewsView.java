package uk.co.edwardquixote.Chaward.kcaapp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
public class FragNewsView extends Fragment {

    private final static String sJS_INTERFACE = "uk.co.edwardquixote.Chaward.kcaapp.Fragments.JSINTERFACE_NAME_2";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View vFragNewsView = inflater.inflate(R.layout.frag_news_view, container, false);

        initializeVariablesAndUIObjects(vFragNewsView);

        return vFragNewsView;
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

        //  TODO: Add ProgressDialog Here

        String sWebFilePath = "http://www.kca.ac.ke";   //  TODO: Testing Page. Change with Actual

        Log.d(FragNewsView.this.toString(), "FragNewsView WebView Loading URL: kca.ac.ke!");  //  TODO FOR Testing only

        WebView webContentView = (WebView) fragmentLayout.findViewById(R.id.webNewsView);
        webContentView.loadUrl(sWebFilePath);
        WebSettings wesWebSettings = webContentView.getSettings();
        wesWebSettings.setJavaScriptEnabled(true);
        webContentView.addJavascriptInterface(new NewsWebView2JSInterface(getActivity(), getActivity(), webContentView), sJS_INTERFACE);

        Log.w(FragNewsView.this.toString(), "FragNewsView WebView LOADED URL: kac.ac.ke!");  //  TODO FOR Testing only

        //  TODO: Dismiss ProgressDialog here.
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    private class NewsWebView2JSInterface {

        private Context coxContext;
        private Activity actActivity;

        private WebView webWebView;

        public NewsWebView2JSInterface(Context context, Activity activity, WebView webView) {
            this.coxContext = context;
            this.actActivity = activity;

            this.webWebView = webView;
        }

        @JavascriptInterface
        public void showToast(String message) {

            this.actActivity.runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    if (!webWebView.getUrl().contains("http://www.kca.ac.ke")) {
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
