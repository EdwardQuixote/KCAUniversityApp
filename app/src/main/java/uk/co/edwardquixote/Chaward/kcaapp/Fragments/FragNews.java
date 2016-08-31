package uk.co.edwardquixote.Chaward.kcaapp.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

import uk.co.edwardquixote.Chaward.kcaapp.Adapters.AdapterRecyclerViewNews;
import uk.co.edwardquixote.Chaward.kcaapp.R;

/**
 * Fragment Class for Fragment News.
 *
 * Created by Edward Quixote
 * On 23/06/2015
 * At 11:04PM
 */
public class FragNews extends Fragment {

    private InterfaceFragNews interFragNews;

    public interface InterfaceFragNews {
        void codeToStartFragNewsView(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            interFragNews = (InterfaceFragNews) activity;
        } catch (ClassCastException ccex) {
            throw new ClassCastException("KCAHomeActivity must implement InterfaceFragNews!");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View vFragNews = inflater.inflate(R.layout.frag_news, container, false);

        initializeVariablesAndUIObjects(vFragNews);

        Log.i(FragNews.this.toString(), "FragNews just called onCreateView() and View has been created!");  //  TODO FOR Testing only

        return vFragNews;
    }

    /**
     * This is where I initialize class variables and Objects used in the UI.
     *
     * Called in onCreateView();
     */
    private void initializeVariablesAndUIObjects(View fragmentLayout) {

        this.getActivity().setTitle(R.string.title_fragment_news);

        int[] iaryImages = getActivity().getResources().getIntArray(R.array.iaryImages);

        AdapterRecyclerViewNews adpRVAdapter = new AdapterRecyclerViewNews(codeToGetJSONData(), iaryImages);

        ListView lstNews = (ListView) fragmentLayout.findViewById(R.id.lstNews);
        lstNews.setAdapter(adpRVAdapter);
        lstNews.setOnItemClickListener(clkLstNews);

    }

    /**
     * Method used to read JSON File with Test Data,
     * Create a JSONArray Object and return it,
     * so it can be passed to the Adapter to extract the Data.
     *
     * Called in initializeVariablesAndUIObjects().adpRVAdapter;
     *
     *  //  TODO: Used for Test Data - Change when Actual Data is involved
     *
     * @return jaryJSONFile (JSONArray)
     */
    private JSONArray codeToGetJSONData() {

        try {

            InputStream isJSONFile = this.getResources().openRawResource(R.raw.json_test_data4_news);
            byte[] baryFileContent = new byte[isJSONFile.available()];
            isJSONFile.read(baryFileContent);
            isJSONFile.close();

            String sFileContent = new String(baryFileContent);
            return (new JSONArray(sFileContent));
        } catch (IOException ioex) {
            //  TODO: Handle error here
            return null;
        } catch (JSONException jsoex) {
            //  TODO: Handle Error here
            return null;
        }

    }


    private ListView.OnItemClickListener clkLstNews = new ListView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            interFragNews.codeToStartFragNewsView(position);
        }
    };


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
