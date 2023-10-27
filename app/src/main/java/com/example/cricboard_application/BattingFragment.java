package com.example.cricboard_application;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BattingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BattingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView tvPMatches, tvPInings, tvPRuns, tvPNotOuts, tvPBestScore, tvPSR, tvDucks, tvPFours, tvPSix, tvPThirties, tvPFifties, tvPHundreds;

    PlayerNames playerNames;
    public BattingFragment() {
        // Required empty public constructor
    }
    public BattingFragment( PlayerNames playerNames) {
        this.playerNames=playerNames;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BattingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BattingFragment newInstance(String param1, String param2) {
        BattingFragment fragment = new BattingFragment();
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
        View battingView=inflater.inflate(R.layout.fragment_batting, container, false);
        tvPMatches = battingView.findViewById(R.id.tvPMatches);
        tvPInings = battingView.findViewById(R.id.tvPInings);
        tvPRuns = battingView.findViewById(R.id.tvPRuns);
        tvPNotOuts = battingView.findViewById(R.id.tvPNotOuts);
        tvPBestScore = battingView.findViewById(R.id.tvPBestScore);
        tvPSR = battingView.findViewById(R.id.tvPSR);
        tvDucks = battingView.findViewById(R.id.tvDucks);
        tvPFours = battingView.findViewById(R.id.tvPFours);
        tvPSix = battingView.findViewById(R.id.tvPSix);
        tvPThirties = battingView.findViewById(R.id.tvPThirties);
        tvPFifties = battingView.findViewById(R.id.tvPFifties);
        tvPHundreds = battingView.findViewById(R.id.tvPHundreds);

        CricBoardSharedPreferences sharedPreferences=new CricBoardSharedPreferences(getContext());

        tvPMatches.setText(String.valueOf(sharedPreferences.getNoMatches()));
        tvPInings.setText(String.valueOf(sharedPreferences.getNoMatches()*2));
        tvPRuns.setText(String.valueOf(playerNames.getPlayerRuns()));
        tvPNotOuts.setText(String.valueOf(playerNames.getPlayerNotOuts()));
        tvPBestScore.setText(String.valueOf(playerNames.getPlayerBestScore()));
        tvPSR.setText(String.valueOf(playerNames.getPlayerRuns()/2.4).substring(0,3));
        tvDucks.setText(String.valueOf(0));
        tvPFours.setText(String.valueOf(playerNames.getPlayerFours()));
        tvPSix.setText(String.valueOf(playerNames.getPlayerSixes()));
        tvPThirties.setText(String.valueOf(playerNames.getPlayerThirties()));
        tvPFifties.setText(String.valueOf(playerNames.getPlayerFifties()));
        tvPHundreds.setText(String.valueOf(playerNames.getPlayerHundreds()));

        return battingView;
    }
}