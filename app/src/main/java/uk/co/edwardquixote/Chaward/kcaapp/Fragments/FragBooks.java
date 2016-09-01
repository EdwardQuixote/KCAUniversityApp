package uk.co.edwardquixote.Chaward.kcaapp.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

import uk.co.edwardquixote.Chaward.kcaapp.Adapters.AdapterRecyclerViewBooks;
import uk.co.edwardquixote.Chaward.kcaapp.R;

/**
 * Created by Edward Quixote on 09/07/2015.
 */
public class FragBooks extends Fragment {

    private InterfaceFragBooks interFragBooks;

    public interface InterfaceFragBooks {
        void codeToStartFragBookView(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            interFragBooks = (InterfaceFragBooks) activity;
        } catch (ClassCastException ccex) {
            throw new ClassCastException("KCAHomeActivity must implement InterfaceFragBooks!");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View vFragBooks = inflater.inflate(R.layout.frag_books, container, false);

        initializeVariablesAndUIObjects(vFragBooks);

        return vFragBooks;
    }


    /**
     * This is where I initialize class variables and Objects used in the UI.
     *
     * Called in onCreateView();
     */
    private void initializeVariablesAndUIObjects(View fragmentLayout) {

        this.getActivity().setTitle(R.string.title_fragment_books);

        AdapterRecyclerViewBooks clsRVAdapter = new AdapterRecyclerViewBooks(codeToGetJSONData());

        ListView lstBooks = (ListView) fragmentLayout.findViewById(R.id.lstBooks);
        lstBooks.setAdapter(clsRVAdapter);
        lstBooks.setOnItemClickListener(clkLstBooks);

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

        JSONArray jaryJSONFile;
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


    private ListView.OnItemClickListener clkLstBooks = new ListView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            interFragBooks.codeToStartFragBookView(position);
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
