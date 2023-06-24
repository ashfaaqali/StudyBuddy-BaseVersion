package com.example.studybuddybaseversion;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    TextView TotalClasses1, TotalClasses2, TotalClasses3, TotalClasses4, TotalClasses5;
    TextView PresentClasses1, PresentClasses2, PresentClasses3, PresentClasses4, PresentClasses5;
    TextView AbsentClasses1, AbsentClasses2, AbsentClasses3, AbsentClasses4, AbsentClasses5;
    TextView CancelledClasses1, CancelledClasses2, CancelledClasses3, CancelledClasses4, CancelledClasses5;
    TextView CurrentPercentage1, CurrentPercentage2, CurrentPercentage3, CurrentPercentage4, CurrentPercentage5;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment, null);
        TotalClasses1 = v.findViewById(R.id.total_classes_1); TotalClasses2 = v.findViewById(R.id.total_classes_2);
        TotalClasses3 = v.findViewById(R.id.total_classes_3); TotalClasses4 = v.findViewById(R.id.total_classes_4);
        TotalClasses5 = v.findViewById(R.id.total_classes_5);

        PresentClasses1 = v.findViewById(R.id.present_classes_1); PresentClasses2 = v.findViewById(R.id.present_classes_2);
        PresentClasses3 = v.findViewById(R.id.present_classes_3);PresentClasses4 = v.findViewById(R.id.present_classes_4);
        PresentClasses5 = v.findViewById(R.id.present_classes_5);

        AbsentClasses1 = v.findViewById(R.id.absent_classes_1); AbsentClasses2 = v.findViewById(R.id.absent_classes_2);
        AbsentClasses3 = v.findViewById(R.id.absent_classes_3); AbsentClasses4 = v.findViewById(R.id.absent_classes_4);
        AbsentClasses5 = v.findViewById(R.id.absent_classes_5);

        CancelledClasses1 = v.findViewById(R.id.cancelled_classes_1); CancelledClasses2 = v.findViewById(R.id.cancelled_classes_2);
        CancelledClasses3 = v.findViewById(R.id.cancelled_classes_3); CancelledClasses4 = v.findViewById(R.id.cancelled_classes_4);
        CancelledClasses5 = v.findViewById(R.id.cancelled_classes_5);

        CurrentPercentage1 = v.findViewById(R.id.current_percentage_1); CurrentPercentage2 = v.findViewById(R.id.current_percentage_2);
        CurrentPercentage3 = v.findViewById(R.id.current_percentage_3); CurrentPercentage4 = v.findViewById(R.id.current_percentage_4);
        CurrentPercentage5 = v.findViewById(R.id.current_percentage_5);

//        TotalClasses();




        return v;
    }

//    private void TotalClasses() {
//        sharedPreferences = requireContext().getSharedPreferences("StudyBuddy", MODE_PRIVATE);
//        editor = sharedPreferences.edit();
//        if (sharedPreferences.contains())
//    }
}
