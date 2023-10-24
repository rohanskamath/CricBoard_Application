package com.example.cricboard_application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /* UI Objects Created */
    private EditText txtOvers;
    private Spinner spinnerHost, spinnerVisitor;
    private RadioGroup radioGroupToss, radioGroupOpt;
    private RadioButton radioBtnHostToss, radioBtnVisitorToss, radioBtnBat, radioBtnBall;
    private Button btnStart;
    ArrayAdapter teamsCollectionAdapter;
    ArrayList<String> OverallTeams = new ArrayList<>();
    CricBoardSharedPreferences sharedPreferences;
    boolean isInitialHostLoad = true;
    boolean isInitialVisitorLoad = true;
    Context context;


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /* Shared Object creation */
        sharedPreferences = new CricBoardSharedPreferences(getContext());

        /* Setting UI elements with Java */
        spinnerHost = view.findViewById(R.id.spinnerHostTeam);
        spinnerVisitor = view.findViewById(R.id.spinnerVisitorTeam);

        radioGroupToss = view.findViewById(R.id.radioGroupToss);
        radioGroupOpt = view.findViewById(R.id.radioGroupOpt);
        radioBtnHostToss = view.findViewById(R.id.radioBtnHostToss);
        radioBtnVisitorToss = view.findViewById(R.id.radioBtnVistorToss);
        radioBtnBat = view.findViewById(R.id.radioBtnBat);
        radioBtnBall = view.findViewById(R.id.radioBtnBall);

        txtOvers = view.findViewById(R.id.txtOvers);
        btnStart = view.findViewById(R.id.btnStart);

        /* Retrieving From Database and Storing in arraylist */
        OverallTeams = getTeamNamesFromDataSource();

        /* Setting spinner Adapter */
        teamsCollectionAdapter = new ArrayAdapter(getContext(), R.layout.spinner_item_layout, OverallTeams);
        spinnerHost.setAdapter(teamsCollectionAdapter);
        spinnerVisitor.setAdapter(teamsCollectionAdapter);

        spinnerHost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (isInitialHostLoad) {
                    /* Ignore the initial selection */
                    isInitialHostLoad = false;
                    return;
                }
                /* Radio Text to be changed on spinner selection */
                String selectedHostTeam = spinnerHost.getSelectedItem().toString();
                if (selectedHostTeam.equals("---- Select Team ----")) {
                    Toast.makeText(getContext(), "Please select correct Team", Toast.LENGTH_SHORT).show();
                } else if (spinnerHost.getSelectedItem().toString().equals(spinnerVisitor.getSelectedItem().toString())) {
                    Toast.makeText(getContext(), "Please select correct Team", Toast.LENGTH_SHORT).show();
                } else {
                    radioBtnHostToss.setText(selectedHostTeam);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerVisitor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (isInitialVisitorLoad) {
                    /* Ignore the initial selection */
                    isInitialVisitorLoad = false;
                    return;
                }
                /* Radio Text to be changed on spinner selection */
                String selectedVisitorTeam = spinnerVisitor.getSelectedItem().toString();
                if (selectedVisitorTeam.equals("---- Select Team ----")) {
                    Toast.makeText(getContext(), "Please select correct Team", Toast.LENGTH_SHORT).show();
                } else if (spinnerHost.getSelectedItem().toString().equals(spinnerVisitor.getSelectedItem().toString())) {
                    Toast.makeText(getContext(), "Please select correct Team", Toast.LENGTH_SHORT).show();
                } else {
                    radioBtnVisitorToss.setText(selectedVisitorTeam);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //No Action
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isInputValid()) {

                    /* Store data in Shared preference */
                    sharedPreferences.setHostTeamName(spinnerHost.getSelectedItem().toString());
                    sharedPreferences.setVisitorTeamName(spinnerVisitor.getSelectedItem().toString());
                    if (radioBtnHostToss.isChecked()) {
                        sharedPreferences.setTossWonBy(radioBtnHostToss.getText().toString());
                    } else {
                        sharedPreferences.setTossWonBy(radioBtnVisitorToss.getText().toString());
                    }
                    if (radioBtnBat.isChecked()) {
                        sharedPreferences.setOptedTo(radioBtnBat.getText().toString());
                    } else {
                        sharedPreferences.setOptedTo(radioBtnBall.getText().toString());
                    }
                    sharedPreferences.setOvers(Float.parseFloat(txtOvers.getText().toString()));

                    /* Navigate to the next page */
                    Intent openingPlayerIntent = new Intent(getContext(), OpeningPlayerActivity.class);
                    openingPlayerIntent.putExtra("Team Host", spinnerHost.getSelectedItem().toString());
                    openingPlayerIntent.putExtra("Team Visitor", spinnerVisitor.getSelectedItem().toString());
                    startActivity(openingPlayerIntent);
                } else {
                    /* Display an error message or toast to inform the user*/
                    if (spinnerHost.getSelectedItem() == null) {
                        Toast.makeText(getContext(), "Please select a Host Team", Toast.LENGTH_SHORT).show();
                    } else if (spinnerVisitor.getSelectedItem() == null) {
                        Toast.makeText(getContext(), "Please select a Vistor Team", Toast.LENGTH_SHORT).show();
                    } else if (spinnerHost.getSelectedItem().toString().equals(spinnerVisitor.getSelectedItem().toString())) {
                        Toast.makeText(getContext(), "Please select correct Team", Toast.LENGTH_SHORT).show();
                    } else if (!(radioBtnHostToss.isChecked() || radioBtnVisitorToss.isChecked())) {
                        Toast.makeText(getContext(), "Please select any of two toss!!", Toast.LENGTH_SHORT).show();
                    } else if (!(radioBtnBat.isChecked() || radioBtnBall.isChecked())) {
                        Toast.makeText(getContext(), "Please select any of two option!!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "All Fields are required!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    /* Check for Validation before start of match */
    private boolean isInputValid() {

        try {
            if (Integer.parseInt(txtOvers.getText().toString()) > 50 && Integer.parseInt(txtOvers.getText().toString()) < 1) {
                Toast.makeText(getActivity(), "Overs range is between 1-50!!!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
        }

        return !TextUtils.isEmpty(txtOvers.getText())
                && (radioBtnHostToss.isChecked() || radioBtnVisitorToss.isChecked())
                && (radioBtnBat.isChecked() || radioBtnBall.isChecked())
                && !(spinnerHost.getSelectedItem().toString().equals("---- Select Team ----") || spinnerVisitor.getSelectedItem().toString().equals("---- Select Team ----"))
                && Integer.parseInt(txtOvers.getText().toString()) <= 50
                && Integer.parseInt(txtOvers.getText().toString()) > 0;
    }

    /* Retrieving From Database and Storing in arraylist */
    private ArrayList<String> getTeamNamesFromDataSource() {
        DataBaseHandler dataBaseHandler = new DataBaseHandler(getContext());
        return dataBaseHandler.getAllTeamNames();
    }
}