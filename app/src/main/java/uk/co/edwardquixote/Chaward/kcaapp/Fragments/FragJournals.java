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

import uk.co.edwardquixote.Chaward.kcaapp.Adapters.AdapterRecyclerViewJournals;
import uk.co.edwardquixote.Chaward.kcaapp.R;

/**
 * Created by Edward Quixote on 09/07/2015.
 */
public class FragJournals extends Fragment {

    private View vFragJournals;

    private RecyclerView rvJournals;
    private RecyclerView.LayoutManager rvlmLayoutManager;
    private RecyclerView.Adapter adpRVAdapter;

    private JSONArray jaryJSONFile;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        vFragJournals = inflater.inflate(R.layout.frag_journals, container, false);

        initializeVariablesAndUIObjects();

        return vFragJournals;
    }


    /**
     * This is where I initialize class variables and Objects used in the UI.
     *
     * Called in onCreateView();
     */
    private void initializeVariablesAndUIObjects() {

        adpRVAdapter = new AdapterRecyclerViewJournals(codeToGetJSONData());

        rvlmLayoutManager = new LinearLayoutManager(this.getActivity());

        rvJournals = (RecyclerView) vFragJournals.findViewById(R.id.rvJournalsRecyclerView);
        rvJournals.setLayoutManager(rvlmLayoutManager);
        rvJournals.setAdapter(adpRVAdapter);
        rvJournals.setItemAnimator(new DefaultItemAnimator());

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

            InputStream isJSONFile = this.getResources().openRawResource(R.raw.json_test_data1);
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
