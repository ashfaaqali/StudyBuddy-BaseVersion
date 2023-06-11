package com.example.studybuddybaseversion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ThuScheduleFragment extends Fragment {

    FloatingActionButton AddButton;
    SharedPreferences sharedPreferences;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.thu_schedule_fragment, null);
        AddButton = (FloatingActionButton) view.findViewById(R.id.add_subjects);
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddSubjects.class);
                String day = "thursday";
                intent.putExtra("day", day);
                startActivity(intent);
            }
        });

        DisplaySchedule();

        return view;
    }
    private void DisplaySchedule() {
        TextView SN1, SN2, SN3, SN4, SN5;
        CardView C1, C2, C3, C4, C5;
        sharedPreferences = requireActivity().getApplicationContext().getSharedPreferences("StudyBuddy", Context.MODE_PRIVATE);
        if (sharedPreferences.contains("Thu S_N_1")) {
            String storedValue = sharedPreferences.getString("Thu S_N_1", "");
            if (!storedValue.isEmpty()){
                SN1 = view.findViewById(R.id.thu_s_name1);
                C1 = view.findViewById(R.id.cv1);
                C1.setVisibility(View.VISIBLE);
                SN1.setText("");
                SN1.setText(storedValue);
            }
        }
        if (sharedPreferences.contains("Thu S_N_2")) {
            String storedValue = sharedPreferences.getString("Thu S_N_2", "");
            if (!storedValue.isEmpty()){
                SN2 = view.findViewById(R.id.thu_s_name2);
                C2 = view.findViewById(R.id.cv2);
                C2.setVisibility(View.VISIBLE);
                SN2.setText(storedValue);
            }
        }
        if (sharedPreferences.contains("Thu S_N_3")) {
            String storedValue = sharedPreferences.getString("Thu S_N_3", "");
            if (!storedValue.isEmpty()){
                SN3 = view.findViewById(R.id.thu_s_name3);
                C3 = view.findViewById(R.id.cv3);
                C3.setVisibility(View.VISIBLE);
                SN3.setText(storedValue);
            }
        }
        if (sharedPreferences.contains("Thu S_N_4")) {
            String storedValue = sharedPreferences.getString("Thu S_N_4", "");
            if (!storedValue.isEmpty()){
                SN4 = view.findViewById(R.id.thu_s_name4);
                C4 = view.findViewById(R.id.cv4);
                C4.setVisibility(View.VISIBLE);
                SN4.setText(storedValue);
            }
        }
        if (sharedPreferences.contains("Thu S_N_5")) {
            String storedValue = sharedPreferences.getString("Thu S_N_5", "");
            if (!storedValue.isEmpty()){
                SN5 = view.findViewById(R.id.thu_s_name5);
                C5 = view.findViewById(R.id.cv5);
                C5.setVisibility(View.VISIBLE);
                SN5.setText(storedValue);
            }
        }
    }
}
