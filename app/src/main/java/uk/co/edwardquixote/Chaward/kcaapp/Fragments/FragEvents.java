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

import uk.co.edwardquixote.Chaward.kcaapp.Adapters.AdapterRecyclerViewEvents;
import uk.co.edwardquixote.Chaward.kcaapp.R;

/**
 * Fragment Class for Fragment Events.
 *
 * Created by Edward Quixote
 * On 24/06/2015
 * At 08:19AM.
 */
public class FragEvents extends Fragment {

    private InterfaceFragEvents interFragEvents;

    public interface InterfaceFragEvents {
        void codeToStartFragEventView(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            interFragEvents = (InterfaceFragEvents) activity;
        } catch (ClassCastException ccex) {
            throw new ClassCastException("KCAHomeActivity must implement FragEvents.InterfaceFragEvents!");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View vFragEvents = inflater.inflate(R.layout.frag_events, container, false);

        initializeVariablesAndUIObjects(vFragEvents);

        return vFragEvents;
    }

    /**
     * This is where I initialize class variables and Objects used in the UI.
     *
     * Called in onCreateView();
     */
    private void initializeVariablesAndUIObjects(View fragmentLayout) {

        this.getActivity().setTitle(R.string.title_fragment_events);

        int[] iaryImages = getActivity().getResources().getIntArray(R.array.iaryImages);

        AdapterRecyclerViewEvents adpRVAdapter = new AdapterRecyclerViewEvents(this.getActivity(), codeToGetJSONData(), iaryImages);

        ListView lstEvents = (ListView) fragmentLayout.findViewById(R.id.lstEvents);
        lstEvents.setAdapter(adpRVAdapter);
        lstEvents.setOnItemClickListener(clkLstEvents);
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

            InputStream isJSONFile = this.getResources().openRawResource(R.raw.json_test_data3_events);
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


    private ListView.OnItemClickListener clkLstEvents = new ListView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            interFragEvents.codeToStartFragEventView(position);
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
}
