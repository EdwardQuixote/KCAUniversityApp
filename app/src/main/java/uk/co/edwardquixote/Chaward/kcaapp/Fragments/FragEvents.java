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

    private View vFragEvents;

    private RecyclerView rvEvents;
    private RecyclerView.Adapter adpRVAdapter;
    private RecyclerView.LayoutManager rvlmLayoutManager;

    private String[] saryEventsTitles;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        vFragEvents = inflater.inflate(R.layout.frag_events, container, false);

        initializeVariablesAndUIObjects();

        return vFragEvents;
    }

    /**
     * This is where I initialize class variables and Objects used in the UI.
     *
     * Called in onCreateView();
     */
    private void initializeVariablesAndUIObjects() {

        this.getActivity().setTitle(R.string.title_fragment_events);

        saryEventsTitles = this.getActivity().getResources().getStringArray(R.array.saryEventsItems);

        adpRVAdapter = new AdapterRecyclerViewEvents(this.getActivity(), saryEventsTitles);

        rvlmLayoutManager = new LinearLayoutManager(this.getActivity());

        rvEvents = (RecyclerView) vFragEvents.findViewById(R.id.rvEventsRecyclerView);
        rvEvents.setLayoutManager(rvlmLayoutManager);
        rvEvents.setAdapter(adpRVAdapter);
        rvEvents.setItemAnimator(new DefaultItemAnimator());

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
