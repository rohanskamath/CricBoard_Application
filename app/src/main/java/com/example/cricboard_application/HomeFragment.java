package com.example.cricboard_application;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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
    private EditText txtHost, txtVisitor, txtOvers;
    private RadioGroup radioGroupToss, radioGroupOpt;
    private RadioButton radioBtnHostToss, radioBtnVistorToss, radioBtnBat, radioBtnBall;
    private Button btnStart;


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

        /* Setting UI elements with Java */
        txtHost = view.findViewById(R.id.txtHost);
        txtVisitor = view.findViewById(R.id.txtVistor);
        txtOvers = view.findViewById(R.id.txtOvers);

        radioGroupToss = view.findViewById(R.id.radioGroupToss);
        radioGroupOpt = view.findViewById(R.id.radioGroupOpt);
        radioBtnHostToss = view.findViewById(R.id.radioBtnHostToss);
        radioBtnVistorToss = view.findViewById(R.id.radioBtnVistorToss);
        radioBtnBat = view.findViewById(R.id.radioBtnBat);
        radioBtnBall = view.findViewById(R.id.radioBtnBall);

        btnStart = view.findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isInputValid()) {

                    /* Navigate to the next page */
                    Intent openingPlayerIntent = new Intent(getContext(), OpeningPlayerActivity.class);
                    startActivity(openingPlayerIntent);
                } else {
                    /* Display an error message or toast to inform the user*/
                    if (TextUtils.isEmpty(txtHost.getText())) {
                        txtHost.setError("Host Field is required!!!");
                    } else if (TextUtils.isEmpty(txtVisitor.getText())) {
                        txtVisitor.setError("Vistor Field is required!!!");
                    } else if (!(radioBtnHostToss.isChecked() || radioBtnVistorToss.isChecked())) {
                        Toast.makeText(getContext(), "Please select any of two toss!!", Toast.LENGTH_SHORT).show();
                    } else if (!(radioBtnBat.isChecked() || radioBtnBall.isChecked())) {
                        Toast.makeText(getContext(), "Please select any of two option!!", Toast.LENGTH_SHORT).show();
                    }
                    else {
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

        return !TextUtils.isEmpty(txtHost.getText())
                && !TextUtils.isEmpty(txtVisitor.getText())
                && !TextUtils.isEmpty(txtOvers.getText())
                && (radioBtnHostToss.isChecked() || radioBtnVistorToss.isChecked())
                && (radioBtnBat.isChecked() || radioBtnBall.isChecked())
                && Integer.parseInt(txtOvers.getText().toString()) <= 50
                && Integer.parseInt(txtOvers.getText().toString()) > 0;
    }
}