package com.example.studybuddybaseversion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class AddSubjects extends AppCompatActivity {
    EditText SubjectName1, SubjectName2, SubjectName3, SubjectName4, SubjectName5;
    EditText RequiredAttendance1, RequiredAttendance2, RequiredAttendance3, RequiredAttendance4, RequiredAttendance5;
    Button Save;

    String day;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subjects);
        SubjectName1 = (EditText) findViewById(R.id.subject_name1);
        SubjectName2 = (EditText) findViewById(R.id.subject_name2);
        SubjectName3 = (EditText) findViewById(R.id.subject_name3);
        SubjectName4 = (EditText) findViewById(R.id.subject_name4);
        SubjectName5 = (EditText) findViewById(R.id.subject_name5);
        RequiredAttendance1 = (EditText) findViewById(R.id.required_attendance1);
        RequiredAttendance2 = (EditText) findViewById(R.id.required_attendance2);
        RequiredAttendance3 = (EditText)findViewById(R.id.required_attendance3);
        RequiredAttendance4 = (EditText)findViewById(R.id.required_attendance4);
        RequiredAttendance5 = (EditText)findViewById(R.id.required_attendance5);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        day = (String) bundle.get("day");
        Save = (Button) findViewById(R.id.add_subjects);


        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveToSharedPref();
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

//    private void RetrieveStoredValues() {
//        sharedPreferences = getSharedPreferences("StudyBuddy", MODE_PRIVATE);
//
//        switch (day){
//            case "monday":
//                if (sharedPreferences.contains("Mon S_N_1")){
//                String storedValue1 = sharedPreferences.getString("Mon S_N_1", "");
//                SubjectName1.setText(storedValue1);
//                }
//                break;
//            case "tuesday":
//                if (sharedPreferences.contains("Tue S_N_1")){
//                    String storedValue1 = sharedPreferences.getString("Tue S_N_1", "");
//                    SubjectName1.setText(storedValue1);
//                }
//                break;
//        }
//    }

    private void SaveToSharedPref() {
        sharedPreferences = getSharedPreferences("StudyBuddy", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

//        RetrieveStoredValues();

        String subName1 =  SubjectName1.getText().toString();
        String subName2 =  SubjectName2.getText().toString();
        String subName3 =  SubjectName3.getText().toString();
        String subName4 =  SubjectName4.getText().toString();
        String subName5 =  SubjectName5.getText().toString();
        String reqAttendancePercentage1 =  RequiredAttendance1.getText().toString();
        String reqAttendancePercentage2 =  RequiredAttendance2.getText().toString();
        String reqAttendancePercentage3 =  RequiredAttendance3.getText().toString();
        String reqAttendancePercentage4 =  RequiredAttendance4.getText().toString();
        String reqAttendancePercentage5 =  RequiredAttendance5.getText().toString();

        switch (day){
            case "monday":
                editor.putString("Mon S_N_1", subName1);
                editor.putString("Mon S_N_2", subName2);
                editor.putString("Mon S_N_3", subName3);
                editor.putString("Mon S_N_4", subName4);
                editor.putString("Mon S_N_5", subName5);
                editor.putString("Mon R_A_1", reqAttendancePercentage1);
                editor.putString("Mon R_A_2", reqAttendancePercentage2);
                editor.putString("Mon R_A_3", reqAttendancePercentage3);
                editor.putString("Mon R_A_4", reqAttendancePercentage4);
                editor.putString("Mon R_A_5", reqAttendancePercentage5);
                editor.apply();
                break;
            case "tuesday":
                editor.putString("Tue S_N_1", subName1);
                editor.putString("Tue S_N_2", subName2);
                editor.putString("Tue S_N_3", subName3);
                editor.putString("Tue S_N_4", subName4);
                editor.putString("Tue S_N_5", subName5);
                editor.putString("Tue R_A_1", reqAttendancePercentage1);
                editor.putString("Tue R_A_2", reqAttendancePercentage2);
                editor.putString("Tue R_A_3", reqAttendancePercentage3);
                editor.putString("Tue R_A_4", reqAttendancePercentage4);
                editor.putString("Tue R_A_5", reqAttendancePercentage5);
                editor.apply();
                break;
            case "wednesday":
                editor.putString("Wed S_N_1", subName1);
                editor.putString("Wed S_N_2", subName2);
                editor.putString("Wed S_N_3", subName3);
                editor.putString("Wed S_N_4", subName4);
                editor.putString("Wed S_N_5", subName5);
                editor.putString("Wed R_A_1", reqAttendancePercentage1);
                editor.putString("Wed R_A_2", reqAttendancePercentage2);
                editor.putString("Wed R_A_3", reqAttendancePercentage3);
                editor.putString("Wed R_A_4", reqAttendancePercentage4);
                editor.putString("Wed R_A_5", reqAttendancePercentage5);
                editor.apply();
                break;
            case "thursday":
                editor.putString("Thu S_N_1", subName1);
                editor.putString("Thu S_N_2", subName2);
                editor.putString("Thu S_N_3", subName3);
                editor.putString("Thu S_N_4", subName4);
                editor.putString("Thu S_N_5", subName5);
                editor.putString("Thu R_A_1", reqAttendancePercentage1);
                editor.putString("Thu R_A_2", reqAttendancePercentage2);
                editor.putString("Thu R_A_3", reqAttendancePercentage3);
                editor.putString("Thu R_A_4", reqAttendancePercentage4);
                editor.putString("Thu R_A_5", reqAttendancePercentage5);
                editor.apply();
                break;
            case "friday":
                editor.putString("Fri S_N_1", subName1);
                editor.putString("Fri S_N_2", subName2);
                editor.putString("Fri S_N_3", subName3);
                editor.putString("Fri S_N_4", subName4);
                editor.putString("Fri S_N_5", subName5);
                editor.putString("Fri R_A_1", reqAttendancePercentage1);
                editor.putString("Fri R_A_2", reqAttendancePercentage2);
                editor.putString("Fri R_A_3", reqAttendancePercentage3);
                editor.putString("Fri R_A_4", reqAttendancePercentage4);
                editor.putString("Fri R_A_5", reqAttendancePercentage5);
                editor.apply();
                break;
            case "saturday":
                editor.putString("Sat S_N_1", subName1);
                editor.putString("Sat S_N_2", subName2);
                editor.putString("Sat S_N_3", subName3);
                editor.putString("Sat S_N_4", subName4);
                editor.putString("Sat S_N_5", subName5);
                editor.putString("Sat R_A_1", reqAttendancePercentage1);
                editor.putString("Sat R_A_2", reqAttendancePercentage2);
                editor.putString("Sat R_A_3", reqAttendancePercentage3);
                editor.putString("Sat R_A_4", reqAttendancePercentage4);
                editor.putString("Sat R_A_5", reqAttendancePercentage5);
                editor.apply();
                break;
        }
        Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
    }
}