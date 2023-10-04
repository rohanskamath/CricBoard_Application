package com.example.cricboard_application;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment implements HistoryInterfaceRV{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<History> historyHostArrayList,historyAwayArrayList;
    private String[] hostTeamNames,awayTeamNames,date,time;
    private int[] totalHostRuns,totalAwayRuns;
    private int[] hostWickets,awayWickets;
    private float[] hostOvers,awayOvers;

    RecyclerView historyRecyclerview;

    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
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
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        historyRecyclerview=view.findViewById(R.id.historyRecyclerview);

        historyDataInitialize();

        historyRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        historyRecyclerview.setHasFixedSize(true);
        HistoryAdapter historyAdapter=new HistoryAdapter(getContext(),historyHostArrayList,historyAwayArrayList,getFragmentManager(), this);
        historyRecyclerview.setAdapter(historyAdapter);
    }

    private void historyDataInitialize() {
        historyHostArrayList=new ArrayList<>();
        historyAwayArrayList=new ArrayList<>();
        date=new String[]{"04/10/2023","05/9/2023"};
        time=new String[]{"12:35 PM","02:30 PM"};
        hostTeamNames = new String[]{"India","Sri Lanka"};
        awayTeamNames=new String[]{"Australia","England"};
        totalHostRuns=new int[]{120,100};
        totalAwayRuns=new int[]{90,106};
        hostWickets=new int[]{3,8};
        awayWickets=new int[]{10,3};
        hostOvers=new float[]{20.0F, 20.0F};
        awayOvers=new float[]{15.4F,20.0F};

        for(int i=0;i<hostTeamNames.length;i++)
        {
            History hostHistory=new History(date[i],time[i],hostTeamNames[i],totalHostRuns[i],hostWickets[i],hostOvers[i]);
            historyHostArrayList.add(hostHistory);
        }
        for(int i=0;i<awayTeamNames.length;i++)
        {
            History awayHistory=new History(date[i],time[i],awayTeamNames[i],totalAwayRuns[i],awayWickets[i],awayOvers[i]);
            historyAwayArrayList.add(awayHistory);
        }
    }

    @Override
    public void onItemClick(int position) {

    }
}