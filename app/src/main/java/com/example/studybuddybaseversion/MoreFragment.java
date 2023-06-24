package com.example.studybuddybaseversion;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MoreFragment extends Fragment {
    TextView Name;
    SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.more_fragment, null);
        Name = v.findViewById(R.id.name_textview);
        sharedPreferences = requireActivity().getSharedPreferences("StudyBuddy", MODE_PRIVATE);
        if (sharedPreferences.contains("Username")){
            String name = "Hi, "+sharedPreferences.getString("Username","");
            Name.setText(name);
        }

        return v;
    }
}
