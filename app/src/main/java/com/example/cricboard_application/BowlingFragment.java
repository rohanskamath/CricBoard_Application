package com.example.cricboard_application;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BowlingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BowlingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    PlayerNames playerNames;

    TextView tvPMatches, tvPInings, tvPOvers, tvPMaidens, tvPWickets, tvPRuns, tvERate, tvPWides, tvPNoBalls, tvPDots, tvPFourWickets, tvPFiveWicket;


    public BowlingFragment() {
        // Required empty public constructor
    }
    public BowlingFragment(PlayerNames playerNames) {
        this.playerNames=playerNames;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BowlingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BowlingFragment newInstance(String param1, String param2) {
        BowlingFragment fragment = new BowlingFragment();
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
        // Inflate the layout for this fragment
        View bowlingView=inflater.inflate(R.layout.fragment_bowling, container, false);
        tvPMatches = bowlingView.findViewById(R.id.tvPMatches);
        tvPInings = bowlingView.findViewById(R.id.tvPInings);
        tvPOvers = bowlingView.findViewById(R.id.tvPOvers);
        tvPMaidens = bowlingView.findViewById(R.id.tvPMaidens);
        tvPWickets = bowlingView.findViewById(R.id.tvPWickets);
        tvPRuns = bowlingView.findViewById(R.id.tvPRuns);
        tvERate = bowlingView.findViewById(R.id.tvERate);
        tvPWides = bowlingView.findViewById(R.id.tvPWides);
        tvPNoBalls = bowlingView.findViewById(R.id.tvPNoBalls);
        tvPDots = bowlingView.findViewById(R.id.tvPDots);
        tvPFourWickets = bowlingView.findViewById(R.id.tvPFourWickets);
        tvPFiveWicket = bowlingView.findViewById(R.id.tvPFiveWicket);

        CricBoardSharedPreferences sharedPreferences=new CricBoardSharedPreferences(getContext());

        tvPMatches.setText(String.valueOf(sharedPreferences.getNoMatches()));
        tvPInings.setText(String.valueOf(sharedPreferences.getNoMatches()*2));
        tvPOvers.setText(String.valueOf(playerNames.getPlayerOvers()).substring(0,3));
        tvPMaidens.setText("0");
        tvPWickets.setText(String.valueOf(playerNames.getPlayerWickets()));
        tvPRuns.setText(String.valueOf(playerNames.getPlayerRuns()));
        tvERate.setText(String.valueOf(playerNames.getPlayerRuns()/23));
        tvPWides.setText(String.valueOf(0));
        tvPNoBalls.setText(String.valueOf(6));
        tvPDots.setText(String.valueOf(0));
        tvPFourWickets.setText(String.valueOf(0));
        tvPFiveWicket.setText(String.valueOf(playerNames.getPlayerFiveWickets()));



        return bowlingView;
    }
}