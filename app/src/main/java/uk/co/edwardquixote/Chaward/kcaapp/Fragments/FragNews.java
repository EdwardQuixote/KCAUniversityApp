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

    private String[] saryNewsTitles;

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

        saryNewsTitles = this.getActivity().getResources().getStringArray(R.array.saryNewsItems);

        adpRVAdapter = new AdapterRecyclerViewNews(saryNewsTitles);

        llmLayoutManager = new LinearLayoutManager(this.getActivity());

        rvNews = (RecyclerView) vFragNews.findViewById(R.id.rvNewsRecyclerView);
        rvNews.setLayoutManager(llmLayoutManager);
        rvNews.setAdapter(adpRVAdapter);
        rvNews.setItemAnimator(new DefaultItemAnimator());

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
