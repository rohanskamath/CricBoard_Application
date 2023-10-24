package com.example.cricboard_application;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScoreSummaryHostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoreSummaryHostFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /* UI Objects */
    Context context;
    RecyclerView recyclerView;
    CricBoardSharedPreferences sharedPreferences;

    public ScoreSummaryHostFragment() {

    }
    public ScoreSummaryHostFragment(Context context) {
        this.context=context;
        sharedPreferences=new CricBoardSharedPreferences(context);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScoreSummaryHostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScoreSummaryHostFragment newInstance(String param1, String param2) {
        ScoreSummaryHostFragment fragment = new ScoreSummaryHostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_score_summary_host, container, false);
        // Initialize the RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ScorecardSummaryHostAdapter scorecardAdapter = new ScorecardSummaryHostAdapter();
        recyclerView.setAdapter(scorecardAdapter);

        scorecardAdapter.addData(new HostScorecardItem("Rohit","0","0", "0", "0","0.0" ));
        scorecardAdapter.addData(new HostScorecardItem( "Virat", "0", "0", "0", "0","0.0"));
        scorecardAdapter.addData(new HostScorecardItem( " Total", "0", "0", "0", "0","0.0"));
        return view;
    }
}