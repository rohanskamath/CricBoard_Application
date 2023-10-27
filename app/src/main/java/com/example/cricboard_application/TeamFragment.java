package com.example.cricboard_application;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamFragment extends Fragment implements TeamsInterfaceRV {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private ArrayList<Teams> teamsArrayList = new ArrayList<>();

    /* UI Objects created */
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
        return inflater.inflate(R.layout.fragment_team, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /* Setting UI elements with Java */
        teamRecyclerView = view.findViewById(R.id.teamRecyclerview);
        teamFloatingBtn = view.findViewById(R.id.floatBtn);

        /* Creating Database object */
        DataBaseHandler dataBaseHandler = new DataBaseHandler(getContext());

        /* Retrieving From Database and Storing in arraylist */
        teamsArrayList = getTeamsFromDataSource();

        /* Setting up custom adapter to recyclerview */
        TeamAdapter teamAdapter = new TeamAdapter(getContext(), teamsArrayList, getFragmentManager(), this);
        teamRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        teamRecyclerView.setHasFixedSize(true);
        teamRecyclerView.setAdapter(teamAdapter);
        teamAdapter.notifyDataSetChanged();

        /* On Click of Floating Button loading Dialog box for adding team */
        teamFloatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //createTeamPopUp();
                CreateTeamDialog createTeamDialog = new CreateTeamDialog(teamAdapter, teamsArrayList, dataBaseHandler);
                createTeamDialog.show(getActivity().getSupportFragmentManager(), "Create Team Dialog Box");
            }
        });
    }

    /* Retrieving From Database and Storing in arraylist */
    private ArrayList<Teams> getTeamsFromDataSource() {
        DataBaseHandler dataBaseHandler = new DataBaseHandler(getContext());
        return dataBaseHandler.getAllTeams();
    }

    /* On Single item-click of Teams recyclerview */
    @Override
    public void onItemClick(int position) {
        Intent playerIntent = new Intent(getContext(), PlayerActivity.class);
        playerIntent.putExtra("Team Name", teamsArrayList.get(position).getTeamName());
        playerIntent.putExtra("Team ID", teamsArrayList.get(position).getTeam_id());
        startActivity(playerIntent);
    }
}