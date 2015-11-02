package uk.co.edwardquixote.Chaward.kcaapp.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    private View vFragNews;

    private RecyclerView rvNews;
    private RecyclerView.Adapter adpRVAdapter;
    private LinearLayoutManager llmLayoutManager;

    private JSONArray jaryJSONFile;
    private int[] iaryImages;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        vFragNews = inflater.inflate(R.layout.frag_news, container, false);

        initializeVariablesAndUIObjects();

        return vFragNews;
    }

    /**
     * This is where I initialize class variables and Objects used in the UI.
     *
     * Called in onCreateView();
     */
    private void initializeVariablesAndUIObjects() {

        this.getActivity().setTitle(R.string.title_fragment_news);

        iaryImages = getActivity().getResources().getIntArray(R.array.iaryImages);

        adpRVAdapter = new AdapterRecyclerViewNews(codeToGetJSONData(), iaryImages);

        llmLayoutManager = new LinearLayoutManager(this.getActivity());

        rvNews = (RecyclerView) vFragNews.findViewById(R.id.rvNewsRecyclerView);
        rvNews.setLayoutManager(llmLayoutManager);
        rvNews.setAdapter(adpRVAdapter);
        rvNews.setItemAnimator(new DefaultItemAnimator());

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
            jaryJSONFile = new JSONArray(sFileContent);

            return jaryJSONFile;
        } catch (IOException ioex) {
            //  TODO: Handle error here
            return null;
        } catch (JSONException jsoex) {
            //  TODO: Handle Error here
            return null;
        }

    }


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
