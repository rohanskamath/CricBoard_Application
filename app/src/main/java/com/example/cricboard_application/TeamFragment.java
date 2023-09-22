package com.example.cricboard_application;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Teams> teamsArrayList;
    private String[] teamNames;
    private int[] matches;
    private int[] won;
    private int[] lost;

    RecyclerView teamRecyclerView;

    FloatingActionButton teamFloatingBtn;

    public TeamFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TeamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeamFragment newInstance(String param1, String param2) {
        TeamFragment fragment = new TeamFragment();
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
        return inflater.inflate(R.layout.fragment_team, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataInitialize();
        teamRecyclerView=view.findViewById(R.id.teamRecyclerview);
        teamRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        teamRecyclerView.setHasFixedSize(true);
        TeamAdapter teamAdapter=new TeamAdapter(getContext(),teamsArrayList);
        teamRecyclerView.setAdapter(teamAdapter);
        teamAdapter.notifyDataSetChanged();

        teamFloatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTeamPopUp();
            }
        });

    }

    private void createTeamPopUp() {

    }


    private void dataInitialize() {
        teamsArrayList = new ArrayList<>();

        teamNames = new String[]{
                getString(R.string.Team_1),
                getString(R.string.Team_2),
                getString(R.string.Team_3),
                getString(R.string.Team_4),
                getString(R.string.Team_5),
                getString(R.string.Team_6),
                getString(R.string.Team_7),
                getString(R.string.Team_8)
        };
        
        matches=new int[]{10,0,4,5,6,1,10,4};
        
        won=new int[]{10,0,3,4,2,1,0,1};

        lost=new int[]{0,0,1,2,3,0,10,3};

        for(int i=0;i<teamNames.length;i++)
        {
            Teams teams=new Teams(teamNames[i],matches[i],won[i],lost[i]);
            teamsArrayList.add(teams);
        }
    }
}