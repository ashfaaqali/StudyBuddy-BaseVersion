package com.app.studybuddybaseversion;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.app.studybuddybaseversion.ScheduleFragmentModels.NotificationReceiver;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class AddSubjects extends AppCompatActivity {
    EditText SubjectName1, SubjectName2, SubjectName3, SubjectName4, SubjectName5;
    EditText RequiredAttendance1, RequiredAttendance2, RequiredAttendance3, RequiredAttendance4, RequiredAttendance5;
    Button Save;
    TextView SelectClassTime1, SelectClassTime2, SelectClassTime3, SelectClassTime4, SelectClassTime5, EmptyTimeError1;
    String day, selectedTimePM, selectedTimeAM;
    int selectTimeBtn;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String subjectName1, subjectName2, subjectName3, subjectName4, subjectName5, reqAttendance1, reqAttendance2, reqAttendance3,reqAttendance4, reqAttendance5, classTime1, classTime2, classTime3, classTime4, classTime5;

    public  MaterialTimePicker materialTimePicker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subjects);

        SubjectName1 = (EditText) findViewById(R.id.subject_name1); SubjectName2 = (EditText) findViewById(R.id.subject_name2);
        SubjectName3 = (EditText) findViewById(R.id.subject_name3);SubjectName4 = (EditText) findViewById(R.id.subject_name4);
        SubjectName5 = (EditText) findViewById(R.id.subject_name5);

        RequiredAttendance1 = (EditText) findViewById(R.id.required_attendance1); RequiredAttendance2 = (EditText) findViewById(R.id.required_attendance2);
        RequiredAttendance3 = (EditText)findViewById(R.id.required_attendance3);RequiredAttendance4 = (EditText)findViewById(R.id.required_attendance4);
        RequiredAttendance5 = (EditText)findViewById(R.id.required_attendance5);

        Save = (Button) findViewById(R.id.add_subjects);

        SelectClassTime1 = (TextView) findViewById(R.id.select_time1); SelectClassTime2 = (TextView) findViewById(R.id.select_time2);
        SelectClassTime3 = (TextView) findViewById(R.id.select_time3);SelectClassTime4 = (TextView) findViewById(R.id.select_time4);
        SelectClassTime5 = (TextView) findViewById(R.id.select_time5);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        day = (String) bundle.get("day");

        RetrieveStoredValues();

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validation = RunValidations();
                if (validation) {
                    SaveToSharedPref(); SetReminder(); finish();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });

        CreateNotificationChannel();

        SelectClassTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTimeBtn = 1;
                ShowTimePicker();
            }
        });
        SelectClassTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTimeBtn = 2;
                ShowTimePicker();
            }
        });
        SelectClassTime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTimeBtn = 3;
                ShowTimePicker();
            }
        });
        SelectClassTime4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTimeBtn = 4;
                ShowTimePicker();
            }
        });
        SelectClassTime5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTimeBtn = 5;
                ShowTimePicker();
            }
        });
    }

    private boolean RunValidations() {
        String subjectName1 = SubjectName1.getText().toString();
        String reqAttendance1 = RequiredAttendance1.getText().toString();
        if (subjectName1.isEmpty()){
            SubjectName1.requestFocus();
            SubjectName1.setError("Add At least 1 Subject");
            return false;
        }
        else if (reqAttendance1.isEmpty()) {
            RequiredAttendance1.requestFocus();
            RequiredAttendance1.setError("Field Cannot Be Empty");
            return false;
        }
        else {
            return true;
        }
    }

    private void SetReminder() {
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, NotificationReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    private void ShowTimePicker() {
        materialTimePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Class Time")
                .build();

        materialTimePicker.show(getSupportFragmentManager(), "StudyBuddy");
        materialTimePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences("StudyBuddy", MODE_PRIVATE);
                editor = sharedPreferences.edit();
                if (materialTimePicker.getHour() > 12){
                    selectedTimePM = String.format("%02d", (materialTimePicker.getHour()-12))+" : " + String.format("%02d", materialTimePicker.getMinute())+" PM";
                    switch (day){
                        case "monday":
                            if (selectTimeBtn == 1) editor.putString("MonClassTime1", selectedTimePM); editor.apply();
                            if (selectTimeBtn == 2) editor.putString("MonClassTime2", selectedTimePM); editor.apply();
                            if (selectTimeBtn == 3) editor.putString("MonClassTime3", selectedTimePM); editor.apply();
                            if (selectTimeBtn == 4) editor.putString("MonClassTime4", selectedTimePM); editor.apply();
                            if (selectTimeBtn == 5) editor.putString("MonClassTime5", selectedTimePM); editor.apply();
                            break;
                        case "tuesday":
                            if (selectTimeBtn == 1) editor.putString("TueClassTime1", selectedTimePM); editor.apply();
                            if (selectTimeBtn == 2) editor.putString("TueClassTime2", selectedTimePM); editor.apply();
                            if (selectTimeBtn == 3) editor.putString("TueClassTime3", selectedTimePM); editor.apply();
                            if (selectTimeBtn == 4) editor.putString("TueClassTime4", selectedTimePM); editor.apply();
                            if (selectTimeBtn == 5) editor.putString("TueClassTime5", selectedTimePM); editor.apply();
                            break;
                        case "wednesday":
                            if (selectTimeBtn == 1) editor.putString("WedClassTime1", selectedTimePM); editor.apply();
                            if (selectTimeBtn == 2) editor.putString("WedClassTime2", selectedTimePM); editor.apply();
                            if (selectTimeBtn == 3) editor.putString("WedClassTime3", selectedTimePM); editor.apply();
                            if (selectTimeBtn == 4) editor.putString("WedClassTime4", selectedTimePM); editor.apply();
                            if (selectTimeBtn == 5) editor.putString("WedClassTime5", selectedTimePM); editor.apply();
                            break;
                        case "thursday":
                            if (selectTimeBtn == 1) editor.putString("ThuClassTime1", selectedTimePM); editor.apply();
                            if (selectTimeBtn == 2) editor.putString("ThuClassTime2", selectedTimePM); editor.apply();
                            if (selectTimeBtn == 3) editor.putString("ThuClassTime3", selectedTimePM); editor.apply();
                            if (selectTimeBtn == 4) editor.putString("ThuClassTime4", selectedTimePM); editor.apply();
                            if (selectTimeBtn == 5) editor.putString("ThuClassTime5", selectedTimePM); editor.apply();
                            break;
                        case "friday":
                            if (selectTimeBtn == 1) editor.putString("FriClassTime1", selectedTimePM); editor.apply();
                            if (selectTimeBtn == 2) editor.putString("FriClassTime2", selectedTimePM); editor.apply();
                            if (selectTimeBtn == 3) editor.putString("FriClassTime3", selectedTimePM); editor.apply();
                            if (selectTimeBtn == 4) editor.putString("FriClassTime4", selectedTimePM); editor.apply();
                            if (selectTimeBtn == 5) editor.putString("FriClassTime5", selectedTimePM); editor.apply();
                            break;
                        case "saturday":
                            if (selectTimeBtn == 1) editor.putString("SatClassTime1", selectedTimePM); editor.apply();
                            if (selectTimeBtn == 2) editor.putString("SatClassTime2", selectedTimePM); editor.apply();
                            if (selectTimeBtn == 3) editor.putString("SatClassTime3", selectedTimePM); editor.apply();
                            if (selectTimeBtn == 4) editor.putString("SatClassTime4", selectedTimePM); editor.apply();
                            if (selectTimeBtn == 5) editor.putString("SatClassTime5", selectedTimePM); editor.apply();
                            break;
                    }
                } else {
                    selectedTimeAM = String.format("%02d", materialTimePicker.getHour())+" : "  +  String.format("%02d", materialTimePicker.getMinute())+" AM";
                    switch (day){
                        case "monday":
                            if (selectTimeBtn == 1) editor.putString("MonClassTime1", selectedTimeAM); editor.apply();
                            if (selectTimeBtn == 2) editor.putString("MonClassTime2", selectedTimeAM); editor.apply();
                            if (selectTimeBtn == 3) editor.putString("MonClassTime3", selectedTimeAM); editor.apply();
                            if (selectTimeBtn == 4) editor.putString("MonClassTime4", selectedTimeAM); editor.apply();
                            if (selectTimeBtn == 5) editor.putString("MonClassTime5", selectedTimeAM); editor.apply();
                            break;
                        case "tuesday":
                            if (selectTimeBtn == 1) editor.putString("TueClassTime1", selectedTimeAM); editor.apply();
                            if (selectTimeBtn == 2) editor.putString("TueClassTime2", selectedTimeAM); editor.apply();
                            if (selectTimeBtn == 3) editor.putString("TueClassTime3", selectedTimeAM); editor.apply();
                            if (selectTimeBtn == 4) editor.putString("TueClassTime4", selectedTimeAM); editor.apply();
                            if (selectTimeBtn == 5) editor.putString("TueClassTime5", selectedTimeAM); editor.apply();
                            break;
                        case "wednesday":
                            if (selectTimeBtn == 1) editor.putString("WedClassTime1", selectedTimeAM); editor.apply();
                            if (selectTimeBtn == 2) editor.putString("WedClassTime2", selectedTimeAM); editor.apply();
                            if (selectTimeBtn == 3) editor.putString("WedClassTime3", selectedTimeAM); editor.apply();
                            if (selectTimeBtn == 4) editor.putString("WedClassTime4", selectedTimeAM); editor.apply();
                            if (selectTimeBtn == 5) editor.putString("WedClassTime5", selectedTimeAM); editor.apply();
                            break;
                        case "thursday":
                            if (selectTimeBtn == 1) editor.putString("ThuClassTime1", selectedTimeAM); editor.apply();
                            if (selectTimeBtn == 2) editor.putString("ThuClassTime2", selectedTimeAM); editor.apply();
                            if (selectTimeBtn == 3) editor.putString("ThuClassTime3", selectedTimeAM); editor.apply();
                            if (selectTimeBtn == 4) editor.putString("ThuClassTime4", selectedTimeAM); editor.apply();
                            if (selectTimeBtn == 5) editor.putString("ThuClassTime5", selectedTimeAM); editor.apply();
                            break;
                        case "friday":
                            if (selectTimeBtn == 1) editor.putString("FriClassTime1", selectedTimeAM); editor.apply();
                            if (selectTimeBtn == 2) editor.putString("FriClassTime2", selectedTimeAM); editor.apply();
                            if (selectTimeBtn == 3) editor.putString("FriClassTime3", selectedTimeAM); editor.apply();
                            if (selectTimeBtn == 4) editor.putString("FriClassTime4", selectedTimeAM); editor.apply();
                            if (selectTimeBtn == 5) editor.putString("FriClassTime5", selectedTimeAM); editor.apply();
                            break;
                        case "saturday":
                            if (selectTimeBtn == 1) editor.putString("SatClassTime1", selectedTimeAM); editor.apply();
                            if (selectTimeBtn == 2) editor.putString("SatClassTime2", selectedTimeAM); editor.apply();
                            if (selectTimeBtn == 3) editor.putString("SatClassTime3", selectedTimeAM); editor.apply();
                            if (selectTimeBtn == 4) editor.putString("SatClassTime4", selectedTimeAM); editor.apply();
                            if (selectTimeBtn == 5) editor.putString("SatClassTime5", selectedTimeAM); editor.apply();
                            break;
                    }
                }
                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, materialTimePicker.getHour());
                calendar.set(Calendar.MINUTE, materialTimePicker.getMinute());
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
            }
        });
    }

    private void CreateNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "StudyBuddyReminderChannel";
            String description = "Channel For Alarm Manager";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel notificationChannel = new NotificationChannel("StudyBuddy", name, importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void RetrieveStoredValues() {
        sharedPreferences = getApplicationContext().getSharedPreferences("StudyBuddy", Context.MODE_PRIVATE);
        switch (day){
            case "monday":
                //Subject Name
                if (sharedPreferences.contains("Mon S_N_1")){
                    String mS1 = sharedPreferences.getString("Mon S_N_1", "");
                    SubjectName1.setText(""); SubjectName1.setText(mS1);
                    if (mS1.length()>0) SubjectName1.setEnabled(false);
                }
                if (sharedPreferences.contains("Mon S_N_2")){
                    String mS2 = sharedPreferences.getString("Mon S_N_2", "");
                    SubjectName2.setText(""); SubjectName2.setText(mS2);
                    if (mS2.length()>0) SubjectName2.setEnabled(false);
                }
                if (sharedPreferences.contains("Mon S_N_3")){
                    String mS3 = sharedPreferences.getString("Mon S_N_3", "");
                    SubjectName3.setText(""); SubjectName3.setText(mS3);
                    if (mS3.length()>0) SubjectName3.setEnabled(false);
                }
                if (sharedPreferences.contains("Mon S_N_4")){
                    String mS4 = sharedPreferences.getString("Mon S_N_4", "");
                    SubjectName4.setText(""); SubjectName4.setText(mS4);
                    if (mS4.length()>0) SubjectName4.setEnabled(false);
                }
                if (sharedPreferences.contains("Mon S_N_5")){
                    String mS5 = sharedPreferences.getString("Mon S_N_5", "");
                    SubjectName5.setText(""); SubjectName5.setText(mS5);
                    if (mS5.length()>0) SubjectName5.setEnabled(false);
                }
                //Required Attendance
                if (sharedPreferences.contains("Mon R_A_1")){
                    String mRA1 = sharedPreferences.getString("Mon R_A_1", "");
                    RequiredAttendance1.setText(""); RequiredAttendance1.setText(mRA1);
                    if (mRA1.length()>0) RequiredAttendance1.setEnabled(false);
                }
                if (sharedPreferences.contains("Mon R_A_2")){
                    String mRA2 = sharedPreferences.getString("Mon R_A_2", "");
                    RequiredAttendance2.setText(""); RequiredAttendance2.setText(mRA2);
                    if (mRA2.length()>0) RequiredAttendance2.setEnabled(false);
                }
                if (sharedPreferences.contains("Mon R_A_3")){
                    String mRA3 = sharedPreferences.getString("Mon R_A_3", "");
                    RequiredAttendance3.setText(""); RequiredAttendance3.setText(mRA3);
                    if (mRA3.length()>0) RequiredAttendance3.setEnabled(false);
                }
                if (sharedPreferences.contains("Mon R_A_4")){
                    String mRA4 = sharedPreferences.getString("Mon R_A_4", "");
                    RequiredAttendance4.setText(""); RequiredAttendance4.setText(mRA4);
                    if (mRA4.length()>0) RequiredAttendance4.setEnabled(false);
                }
                if (sharedPreferences.contains("Mon R_A_5")){
                    String mRA5 = sharedPreferences.getString("Mon R_A_5", "");
                    RequiredAttendance5.setText(""); RequiredAttendance5.setText(mRA5);
                    if (mRA5.length()>0) RequiredAttendance5.setEnabled(false);
                }
                //Selected Time
                if (sharedPreferences.contains("MonClassTime1")){
                    String mTime1 = sharedPreferences.getString("MonClassTime1", "");
                    SelectClassTime1.setText(""); SelectClassTime1.setText(mTime1);
                    if (mTime1.length()>0) SelectClassTime1.setEnabled(false);
                }
                if (sharedPreferences.contains("MonClassTime2")){
                    String mTime2 = sharedPreferences.getString("MonClassTime2", "");
                    SelectClassTime2.setText(""); SelectClassTime2.setText(mTime2);
                    if (mTime2.length()>0) SelectClassTime2.setEnabled(false);

                }
                if (sharedPreferences.contains("MonClassTime3")){
                    String mTime3 = sharedPreferences.getString("MonClassTime3", "");
                    SelectClassTime3.setText(""); SelectClassTime3.setText(mTime3);
                    if (mTime3.length()>0) SelectClassTime3.setEnabled(false);
                }
                if (sharedPreferences.contains("MonClassTime4")){
                    String mTime4 = sharedPreferences.getString("MonClassTime4", "");
                    SelectClassTime4.setText(""); SelectClassTime4.setText(mTime4);
                    if (mTime4.length()>0) SelectClassTime4.setEnabled(false);
                }
                if (sharedPreferences.contains("MonClassTime5")){
                    String mTime5 = sharedPreferences.getString("MonClassTime5", "");
                    SelectClassTime5.setText(""); SelectClassTime5.setText(mTime5);
                    if (mTime5.length()>0) SelectClassTime5.setEnabled(false);
                }
                break;
            case "tuesday":
                //Subject Name
                if (sharedPreferences.contains("Tue S_N_1")){
                    String tS1 = sharedPreferences.getString("Tue S_N_1", "");
                    SubjectName1.setText(""); SubjectName1.setText(tS1);
                    if (tS1.length()>0) SubjectName1.setEnabled(false);
                }
                if (sharedPreferences.contains("Tue S_N_2")){
                    String tS2 = sharedPreferences.getString("Tue S_N_2", "");
                    SubjectName2.setText(""); SubjectName2.setText(tS2);
                    if (tS2.length()>0) SubjectName2.setEnabled(false);
                }
                if (sharedPreferences.contains("Tue S_N_3")){
                    String tS3 = sharedPreferences.getString("Tue S_N_3", "");
                    SubjectName3.setText(""); SubjectName3.setText(tS3);
                    if (tS3.length()>0) SubjectName3.setEnabled(false);
                }
                if (sharedPreferences.contains("Tue S_N_4")){
                    String tS4 = sharedPreferences.getString("Tue S_N_4", "");
                    SubjectName4.setText(""); SubjectName4.setText(tS4);
                    if (tS4.length()>0) SubjectName4.setEnabled(false);
                }
                if (sharedPreferences.contains("Tue S_N_5")){
                    String tS5 = sharedPreferences.getString("Tue S_N_5", "");
                    SubjectName5.setText(""); SubjectName5.setText(tS5);
                    if (tS5.length()>0) SubjectName5.setEnabled(false);
                }
                //Required Attendance
                if (sharedPreferences.contains("Tue R_A_1")){
                    String tRA1 = sharedPreferences.getString("Tue R_A_1", "");
                    RequiredAttendance1.setText(""); RequiredAttendance1.setText(tRA1);
                    if (tRA1.length()>0) RequiredAttendance1.setEnabled(false);
                }
                if (sharedPreferences.contains("Tue R_A_2")){
                    String tRA2 = sharedPreferences.getString("Tue R_A_2", "");
                    RequiredAttendance2.setText(""); RequiredAttendance2.setText(tRA2);
                    if (tRA2.length()>0) RequiredAttendance2.setEnabled(false);
                }
                if (sharedPreferences.contains("Tue R_A_3")){
                    String tRA3 = sharedPreferences.getString("Tue R_A_3", "");
                    RequiredAttendance3.setText(""); RequiredAttendance3.setText(tRA3);
                    if (tRA3.length()>0) RequiredAttendance3.setEnabled(false);
                }
                if (sharedPreferences.contains("Tue R_A_4")){
                    String tRA4 = sharedPreferences.getString("Tue R_A_4", "");
                    RequiredAttendance4.setText(""); RequiredAttendance4.setText(tRA4);
                    if (tRA4.length()>0) RequiredAttendance4.setEnabled(false);
                }
                if (sharedPreferences.contains("Tue R_A_5")){
                    String tRA5 = sharedPreferences.getString("Tue R_A_5", "");
                    RequiredAttendance5.setText(""); RequiredAttendance5.setText(tRA5);
                    if (tRA5.length()>0) RequiredAttendance5.setEnabled(false);
                }
                //Selected Time
                if (sharedPreferences.contains("TueClassTime1")){
                    String tTime1 = sharedPreferences.getString("TueClassTime1", "");
                    SelectClassTime1.setText(""); SelectClassTime1.setText(tTime1);
                    if (tTime1.length()>0) SelectClassTime1.setEnabled(false);
                }
                if (sharedPreferences.contains("TueClassTime2")){
                    String tTime2 = sharedPreferences.getString("TueClassTime2", "");
                    SelectClassTime2.setText(""); SelectClassTime2.setText(tTime2);
                    if (tTime2.length()>0) SelectClassTime2.setEnabled(false);
                }
                if (sharedPreferences.contains("TueClassTime3")){
                    String tTime3 = sharedPreferences.getString("TueClassTime3", "");
                    SelectClassTime3.setText(""); SelectClassTime3.setText(tTime3);
                    if (tTime3.length()>0) SelectClassTime3.setEnabled(false);
                }
                if (sharedPreferences.contains("TueClassTime4")){
                    String tTime4 = sharedPreferences.getString("TueClassTime4", "");
                    SelectClassTime4.setText(""); SelectClassTime4.setText(tTime4);
                    if (tTime4.length()>0) SelectClassTime4.setEnabled(false);
                }
                if (sharedPreferences.contains("TueClassTime5")){
                    String tTime5 = sharedPreferences.getString("TueClassTime5", "");
                    SelectClassTime5.setText(""); SelectClassTime5.setText(tTime5);
                    if (tTime5.length()>0) SelectClassTime5.setEnabled(false);
                }
                break;
            case "wednesday":
                //Subject Name
                if (sharedPreferences.contains("Wed S_N_1")){
                    String wS1 = sharedPreferences.getString("Wed S_N_1", "");
                    SubjectName1.setText(""); SubjectName1.setText(wS1);
                    if (wS1.length()>0) SelectClassTime1.setEnabled(false);
                }
                if (sharedPreferences.contains("Wed S_N_2")){
                    String wS2 = sharedPreferences.getString("Wed S_N_2", "");
                    SubjectName2.setText(""); SubjectName2.setText(wS2);
                    if (wS2.length()>0) SelectClassTime2.setEnabled(false);
                }
                if (sharedPreferences.contains("Wed S_N_3")){
                    String wS3 = sharedPreferences.getString("Wed S_N_3", "");
                    SubjectName3.setText(""); SubjectName3.setText(wS3);
                    if (wS3.length()>0) SelectClassTime3.setEnabled(false);
                }
                if (sharedPreferences.contains("Wed S_N_4")){
                    String wS4 = sharedPreferences.getString("Wed S_N_4", "");
                    SubjectName4.setText(""); SubjectName4.setText(wS4);
                    if (wS4.length()>0) SelectClassTime4.setEnabled(false);
                }
                if (sharedPreferences.contains("Wed S_N_5")){
                    String wS5 = sharedPreferences.getString("Wed S_N_5", "");
                    SubjectName5.setText(""); SubjectName5.setText(wS5);
                    if (wS5.length()>0) SelectClassTime5.setEnabled(false);                }
                //Required Attendance
                if (sharedPreferences.contains("Wed R_A_1")){
                    String wRA1 = sharedPreferences.getString("Wed R_A_1", "");
                    RequiredAttendance1.setText(""); RequiredAttendance1.setText(wRA1);
                    if (wRA1.length()>0) RequiredAttendance1.setEnabled(false);
                }
                if (sharedPreferences.contains("Wed R_A_2")){
                    String wRA2 = sharedPreferences.getString("Wed R_A_2", "");
                    RequiredAttendance2.setText(""); RequiredAttendance2.setText(wRA2);
                    if (wRA2.length()>0) RequiredAttendance2.setEnabled(false);
                }
                if (sharedPreferences.contains("Wed R_A_3")){
                    String wRA3 = sharedPreferences.getString("Wed R_A_3", "");
                    RequiredAttendance3.setText(""); RequiredAttendance3.setText(wRA3);
                    if (wRA3.length()>0) RequiredAttendance3.setEnabled(false);
                }
                if (sharedPreferences.contains("Wed R_A_4")){
                    String wRA4 = sharedPreferences.getString("Wed R_A_4", "");
                    RequiredAttendance4.setText(""); RequiredAttendance4.setText(wRA4);
                    if (wRA4.length()>0) RequiredAttendance4.setEnabled(false);
                }
                if (sharedPreferences.contains("Wed R_A_5")){
                    String wRA5 = sharedPreferences.getString("Wed R_A_5", "");
                    RequiredAttendance5.setText(""); RequiredAttendance5.setText(wRA5);
                    if (wRA5.length()>0) RequiredAttendance5.setEnabled(false);
                }
                //Selected Time
                if (sharedPreferences.contains("WedClassTime1")){
                    String wTime1 = sharedPreferences.getString("WedClassTime1", "");
                    SelectClassTime1.setText(""); SelectClassTime1.setText(wTime1);
                    if (wTime1.length()>0) SelectClassTime1.setEnabled(false);
                }
                if (sharedPreferences.contains("WedClassTime2")){
                    String wTime2 = sharedPreferences.getString("WedClassTime2", "");
                    SelectClassTime2.setText(""); SelectClassTime2.setText(wTime2);
                    if (wTime2.length()>0) SelectClassTime2.setEnabled(false);
                }
                if (sharedPreferences.contains("WedClassTime3")){
                    String wTime3 = sharedPreferences.getString("WedClassTime3", "");
                    SelectClassTime3.setText(""); SelectClassTime3.setText(wTime3);
                    if (wTime3.length()>0) SelectClassTime3.setEnabled(false);
                }
                if (sharedPreferences.contains("WedClassTime4")){
                    String wTime4 = sharedPreferences.getString("WedClassTime4", "");
                    SelectClassTime4.setText(""); SelectClassTime4.setText(wTime4);
                    if (wTime4.length()>0) SelectClassTime4.setEnabled(false);
                }
                if (sharedPreferences.contains("WedClassTime5")){
                    String wTime5 = sharedPreferences.getString("WedClassTime5", "");
                    SelectClassTime5.setText(""); SelectClassTime5.setText(wTime5);
                    if (wTime5.length()>0) SelectClassTime5.setEnabled(false);
                }
                break;
            case "thursday":
                //Subject Name
                if (sharedPreferences.contains("Thu S_N_1")){
                    String thS1 = sharedPreferences.getString("Thu S_N_1", "");
                    SubjectName1.setText(""); SubjectName1.setText(thS1);
                    if (thS1.length()>0) SubjectName1.setEnabled(false);
                }
                if (sharedPreferences.contains("Thu S_N_2")){
                    String thS2 = sharedPreferences.getString("Thu S_N_2", "");
                    SubjectName2.setText(""); SubjectName2.setText(thS2);
                    if (thS2.length()>0) SubjectName2.setEnabled(false);
                }
                if (sharedPreferences.contains("Thu S_N_3")){
                    String thS3 = sharedPreferences.getString("Thu S_N_3", "");
                    SubjectName3.setText(""); SubjectName3.setText(thS3);
                    if (thS3.length()>0) SubjectName3.setEnabled(false);
                }
                if (sharedPreferences.contains("Thu S_N_4")){
                    String thS4 = sharedPreferences.getString("Thu S_N_4", "");
                    SubjectName4.setText(""); SubjectName4.setText(thS4);
                    if (thS4.length()>0) SubjectName4.setEnabled(false);
                }
                if (sharedPreferences.contains("Thu S_N_5")){
                    String thS5 = sharedPreferences.getString("Thu S_N_5", "");
                    SubjectName5.setText(""); SubjectName5.setText(thS5);
                    if (thS5.length()>0) SubjectName5.setEnabled(false);
                }
                //Required Attendance
                if (sharedPreferences.contains("Thu R_A_1")){
                    String thRA1 = sharedPreferences.getString("Thu R_A_1", "");
                    RequiredAttendance1.setText(""); RequiredAttendance1.setText(thRA1);
                    if (thRA1.length()>0) RequiredAttendance1.setEnabled(false);
                }
                if (sharedPreferences.contains("Thu R_A_2")){
                    String thRA2 = sharedPreferences.getString("Thu R_A_2", "");
                    RequiredAttendance2.setText(""); RequiredAttendance2.setText(thRA2);
                    if (thRA2.length()>0) RequiredAttendance2.setEnabled(false);
                }
                if (sharedPreferences.contains("Thu R_A_3")){
                    String thRA3 = sharedPreferences.getString("Thu R_A_3", "");
                    RequiredAttendance3.setText(""); RequiredAttendance3.setText(thRA3);
                    if (thRA3.length()>0) RequiredAttendance3.setEnabled(false);
                }
                if (sharedPreferences.contains("Thu R_A_4")){
                    String thRA4 = sharedPreferences.getString("Thu R_A_4", "");
                    RequiredAttendance4.setText(""); RequiredAttendance4.setText(thRA4);
                    if (thRA4.length()>0) RequiredAttendance4.setEnabled(false);
                }
                if (sharedPreferences.contains("Tue R_A_5")){
                    String thRA5 = sharedPreferences.getString("Thu R_A_5", "");
                    RequiredAttendance5.setText(""); RequiredAttendance5.setText(thRA5);
                    if (thRA5.length()>0) RequiredAttendance5.setEnabled(false);
                }
                //Selected Time
                if (sharedPreferences.contains("ThuClassTime1")){
                    String thTime1 = sharedPreferences.getString("ThuClassTime1", "");
                    SelectClassTime1.setText(""); SelectClassTime1.setText(thTime1);
                    if (thTime1.length()>0) SelectClassTime1.setEnabled(false);
                }
                if (sharedPreferences.contains("ThuClassTime2")){
                    String thTime2 = sharedPreferences.getString("ThuClassTime2", "");
                    SelectClassTime2.setText(""); SelectClassTime2.setText(thTime2);
                    if (thTime2.length()>0) SelectClassTime2.setEnabled(false);
                }
                if (sharedPreferences.contains("ThuClassTime3")){
                    String thTime3 = sharedPreferences.getString("ThuClassTime3", "");
                    SelectClassTime3.setText(""); SelectClassTime3.setText(thTime3);
                    if (thTime3.length()>0) SelectClassTime3.setEnabled(false);
                }
                if (sharedPreferences.contains("ThuClassTime4")){
                    String thTime4 = sharedPreferences.getString("ThuClassTime4", "");
                    SelectClassTime4.setText(""); SelectClassTime4.setText(thTime4);
                    if (thTime4.length()>0) SelectClassTime4.setEnabled(false);
                }
                if (sharedPreferences.contains("ThuClassTime5")){
                    String thTime5 = sharedPreferences.getString("ThuClassTime5", "");
                    SelectClassTime5.setText(""); SelectClassTime5.setText(thTime5);
                    if (thTime5.length()>0) SelectClassTime5.setEnabled(false);
                }
                break;
            case "friday":
                //Subject Name
                if (sharedPreferences.contains("Fri S_N_1")){
                    String fS1 = sharedPreferences.getString("Fri S_N_1", "");
                    SubjectName1.setText(""); SubjectName1.setText(fS1);
                    if (fS1.length()>0) SubjectName1.setEnabled(false);
                }
                if (sharedPreferences.contains("Fri S_N_2")){
                    String fS2 = sharedPreferences.getString("Fri S_N_2", "");
                    SubjectName2.setText(""); SubjectName2.setText(fS2);
                    if (fS2.length()>0) SubjectName2.setEnabled(false);
                }
                if (sharedPreferences.contains("Fri S_N_3")){
                    String fS3 = sharedPreferences.getString("Fri S_N_3", "");
                    SubjectName3.setText(""); SubjectName3.setText(fS3);
                    if (fS3.length()>0) SubjectName3.setEnabled(false);
                }
                if (sharedPreferences.contains("Fri S_N_4")){
                    String fS4 = sharedPreferences.getString("Fri S_N_4", "");
                    SubjectName4.setText(""); SubjectName4.setText(fS4);
                    if (fS4.length()>0) SubjectName4.setEnabled(false);
                }
                if (sharedPreferences.contains("Fri S_N_5")){
                    String fS5 = sharedPreferences.getString("Fri S_N_5", "");
                    SubjectName5.setText(""); SubjectName5.setText(fS5);
                    if (fS5.length()>0) SubjectName5.setEnabled(false);
                }
                //Required Attendance
                if (sharedPreferences.contains("Fri R_A_1")){
                    String fRA1 = sharedPreferences.getString("Fri R_A_1", "");
                    RequiredAttendance1.setText(""); RequiredAttendance1.setText(fRA1);
                    if (fRA1.length()>0) RequiredAttendance1.setEnabled(false);
                }
                if (sharedPreferences.contains("Fri R_A_2")){
                    String fRA2 = sharedPreferences.getString("Fri R_A_2", "");
                    RequiredAttendance2.setText(""); RequiredAttendance2.setText(fRA2);
                    if (fRA2.length()>0) RequiredAttendance2.setEnabled(false);
                }
                if (sharedPreferences.contains("Fri R_A_3")){
                    String fRA3 = sharedPreferences.getString("Fri R_A_3", "");
                    RequiredAttendance3.setText(""); RequiredAttendance3.setText(fRA3);
                    if (fRA3.length()>0) RequiredAttendance3.setEnabled(false);
                }
                if (sharedPreferences.contains("Fri R_A_4")){
                    String fRA4 = sharedPreferences.getString("Fri R_A_4", "");
                    RequiredAttendance4.setText(""); RequiredAttendance4.setText(fRA4);
                    if (fRA4.length()>0) RequiredAttendance4.setEnabled(false);
                }
                if (sharedPreferences.contains("Fri R_A_5")){
                    String fRA5 = sharedPreferences.getString("Fri R_A_5", "");
                    RequiredAttendance5.setText(""); RequiredAttendance5.setText(fRA5);
                    if (fRA5.length()>0) RequiredAttendance5.setEnabled(false);
                }
                //Selected Time
                if (sharedPreferences.contains("FriClassTime1")){
                    String fTime1 = sharedPreferences.getString("FriClassTime1", "");
                    SelectClassTime1.setText(""); SelectClassTime1.setText(fTime1);
                    if (fTime1.length()>0)SelectClassTime1.setEnabled(false);
                }
                if (sharedPreferences.contains("FriClassTime2")){
                    String fTime2 = sharedPreferences.getString("FriClassTime2", "");
                    SelectClassTime2.setText(""); SelectClassTime2.setText(fTime2);
                    if (fTime2.length()>0)SelectClassTime2.setEnabled(false);
                }
                if (sharedPreferences.contains("FriClassTime3")){
                    String fTime3 = sharedPreferences.getString("FriClassTime3", "");
                    SelectClassTime3.setText(""); SelectClassTime3.setText(fTime3);
                    if (fTime3.length()>0)SelectClassTime3.setEnabled(false);
                }
                if (sharedPreferences.contains("FriClassTime4")){
                    String fTime4 = sharedPreferences.getString("FriClassTime4", "");
                    SelectClassTime4.setText(""); SelectClassTime4.setText(fTime4);
                    if (fTime4.length()>0)SelectClassTime4.setEnabled(false);
                }
                if (sharedPreferences.contains("FriClassTime5")){
                    String fTime5 = sharedPreferences.getString("FriClassTime5", "");
                    SelectClassTime5.setText(""); SelectClassTime5.setText(fTime5);
                    if (fTime5.length()>0)SelectClassTime5.setEnabled(false);
                }
                break;
            case "saturday":
                //Subject Name
                if (sharedPreferences.contains("Sat S_N_1")){
                    String sS1 = sharedPreferences.getString("Sat S_N_1", "");
                    SubjectName1.setText(""); SubjectName1.setText(sS1);
                    if (sS1.length()>0) SubjectName1.setEnabled(false);
                }
                if (sharedPreferences.contains("Sat S_N_2")){
                    String sS2 = sharedPreferences.getString("Sat S_N_2", "");
                    SubjectName2.setText(""); SubjectName2.setText(sS2);
                    if (sS2.length()>0) SubjectName2.setEnabled(false);
                }
                if (sharedPreferences.contains("Sat S_N_3")){
                    String sS3 = sharedPreferences.getString("Sat S_N_3", "");
                    SubjectName3.setText(""); SubjectName3.setText(sS3);
                    if (sS3.length()>0) SubjectName3.setEnabled(false);
                }
                if (sharedPreferences.contains("Sat S_N_4")){
                    String sS4 = sharedPreferences.getString("Sat S_N_4", "");
                    SubjectName4.setText(""); SubjectName4.setText(sS4);
                    if (sS4.length()>0) SubjectName4.setEnabled(false);
                }
                if (sharedPreferences.contains("Sat S_N_5")){
                    String sS5 = sharedPreferences.getString("Sat S_N_5", "");
                    SubjectName5.setText(""); SubjectName5.setText(sS5);
                    if (sS5.length()>0) SubjectName5.setEnabled(false);
                }
                //Required Attendance
                if (sharedPreferences.contains("Sat R_A_1")){
                    String sRA1 = sharedPreferences.getString("Sat R_A_1", "");
                    RequiredAttendance1.setText(""); RequiredAttendance1.setText(sRA1);
                    if (sRA1.length()>0) RequiredAttendance1.setEnabled(false);
                }
                if (sharedPreferences.contains("Sat R_A_2")){
                    String sRA2 = sharedPreferences.getString("Sat R_A_2", "");
                    RequiredAttendance2.setText(""); RequiredAttendance2.setText(sRA2);
                    if (sRA2.length()>0) RequiredAttendance2.setEnabled(false);
                }
                if (sharedPreferences.contains("Sat R_A_3")){
                    String sRA3 = sharedPreferences.getString("Sat R_A_3", "");
                    RequiredAttendance3.setText(""); RequiredAttendance3.setText(sRA3);
                    if (sRA3.length()>0) RequiredAttendance3.setEnabled(false);
                }
                if (sharedPreferences.contains("Sat R_A_4")){
                    String sRA4 = sharedPreferences.getString("Sat R_A_4", "");
                    RequiredAttendance4.setText(""); RequiredAttendance4.setText(sRA4);
                    if (sRA4.length()>0) RequiredAttendance4.setEnabled(false);
                }
                if (sharedPreferences.contains("Sat R_A_5")){
                    String sRA5 = sharedPreferences.getString("Sat R_A_5", "");
                    RequiredAttendance5.setText(""); RequiredAttendance5.setText(sRA5);
                    if (sRA5.length()>0) RequiredAttendance5.setEnabled(false);
                }
                //Selected Time
                if (sharedPreferences.contains("SatClassTime1")){
                    String sTime1 = sharedPreferences.getString("SatClassTime1", "");
                    SelectClassTime1.setText(""); SelectClassTime1.setText(sTime1);
                    if (sTime1.length()>0) SelectClassTime1.setEnabled(false);
                }
                if (sharedPreferences.contains("SatClassTime2")){
                    String sTime2 = sharedPreferences.getString("SatClassTime2", "");
                    SelectClassTime2.setText(""); SelectClassTime2.setText(sTime2);
                    if (sTime2.length()>0) SelectClassTime2.setEnabled(false);
                }
                if (sharedPreferences.contains("SatClassTime3")){
                    String sTime3 = sharedPreferences.getString("SatClassTime3", "");
                    SelectClassTime3.setText(""); SelectClassTime3.setText(sTime3);
                    if (sTime3.length()>0) SelectClassTime3.setEnabled(false);
                }
                if (sharedPreferences.contains("SatClassTime4")){
                    String sTime4 = sharedPreferences.getString("SatClassTime4", "");
                    SelectClassTime4.setText(""); SelectClassTime4.setText(sTime4);
                    if (sTime4.length()>0) SelectClassTime4.setEnabled(false);
                }
                if (sharedPreferences.contains("SatClassTime5")){
                    String sTime5 = sharedPreferences.getString("SatClassTime5", "");
                    SelectClassTime5.setText(""); SelectClassTime5.setText(sTime5);
                    if (sTime5.length()>0) SelectClassTime5.setEnabled(false);
                }
                break;
        }
    }

    private void SaveToSharedPref() {
        sharedPreferences = getSharedPreferences("StudyBuddy", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        String subName1 =  SubjectName1.getText().toString(); String subName2 =  SubjectName2.getText().toString();
        String subName3 =  SubjectName3.getText().toString(); String subName4 =  SubjectName4.getText().toString();
        String subName5 =  SubjectName5.getText().toString();
        String reqAttendancePercentage1 =  RequiredAttendance1.getText().toString(); String reqAttendancePercentage2 =  RequiredAttendance2.getText().toString();
        String reqAttendancePercentage3 =  RequiredAttendance3.getText().toString(); String reqAttendancePercentage4 =  RequiredAttendance4.getText().toString();
        String reqAttendancePercentage5 =  RequiredAttendance5.getText().toString();

        switch (day){
            case "monday":
                editor.putString("Mon S_N_1", subName1); editor.putString("Mon S_N_2", subName2); editor.putString("Mon S_N_3", subName3);
                editor.putString("Mon S_N_4", subName4);editor.putString("Mon S_N_5", subName5);

                editor.putString("Mon R_A_1", reqAttendancePercentage1); editor.putString("Mon R_A_2", reqAttendancePercentage2);
                editor.putString("Mon R_A_3", reqAttendancePercentage3); editor.putString("Mon R_A_4", reqAttendancePercentage4);
                editor.putString("Mon R_A_5", reqAttendancePercentage5); editor.apply();
                break;
            case "tuesday":
                editor.putString("Tue S_N_1", subName1); editor.putString("Tue S_N_2", subName2); editor.putString("Tue S_N_3", subName3);
                editor.putString("Tue S_N_4", subName4); editor.putString("Tue S_N_5", subName5);

                editor.putString("Tue R_A_1", reqAttendancePercentage1); editor.putString("Tue R_A_2", reqAttendancePercentage2);
                editor.putString("Tue R_A_3", reqAttendancePercentage3); editor.putString("Tue R_A_4", reqAttendancePercentage4);
                editor.putString("Tue R_A_5", reqAttendancePercentage5); editor.apply();
                break;
            case "wednesday":
                editor.putString("Wed S_N_1", subName1); editor.putString("Wed S_N_2", subName2); editor.putString("Wed S_N_3", subName3);
                editor.putString("Wed S_N_4", subName4); editor.putString("Wed S_N_5", subName5);

                editor.putString("Wed R_A_1", reqAttendancePercentage1); editor.putString("Wed R_A_2", reqAttendancePercentage2);
                editor.putString("Wed R_A_3", reqAttendancePercentage3); editor.putString("Wed R_A_4", reqAttendancePercentage4);
                editor.putString("Wed R_A_5", reqAttendancePercentage5); editor.apply();
                break;
            case "thursday":
                editor.putString("Thu S_N_1", subName1);editor.putString("Thu S_N_2", subName2); editor.putString("Thu S_N_3", subName3);
                editor.putString("Thu S_N_4", subName4); editor.putString("Thu S_N_5", subName5);

                editor.putString("Thu R_A_1", reqAttendancePercentage1); editor.putString("Thu R_A_2", reqAttendancePercentage2);
                editor.putString("Thu R_A_3", reqAttendancePercentage3); editor.putString("Thu R_A_4", reqAttendancePercentage4);
                editor.putString("Thu R_A_5", reqAttendancePercentage5); editor.apply();
                break;
            case "friday":
                editor.putString("Fri S_N_1", subName1); editor.putString("Fri S_N_2", subName2); editor.putString("Fri S_N_3", subName3);
                editor.putString("Fri S_N_4", subName4);editor.putString("Fri S_N_5", subName5);

                editor.putString("Fri R_A_1", reqAttendancePercentage1);editor.putString("Fri R_A_2", reqAttendancePercentage2);
                editor.putString("Fri R_A_3", reqAttendancePercentage3);editor.putString("Fri R_A_4", reqAttendancePercentage4);
                editor.putString("Fri R_A_5", reqAttendancePercentage5); editor.apply();
                break;
            case "saturday":
                editor.putString("Sat S_N_1", subName1); editor.putString("Sat S_N_2", subName2); editor.putString("Sat S_N_3", subName3);
                editor.putString("Sat S_N_4", subName4); editor.putString("Sat S_N_5", subName5);
                editor.putString("Sat R_A_1", reqAttendancePercentage1); editor.putString("Sat R_A_2", reqAttendancePercentage2);
                editor.putString("Sat R_A_3", reqAttendancePercentage3); editor.putString("Sat R_A_4", reqAttendancePercentage4);
                editor.putString("Sat R_A_5", reqAttendancePercentage5); editor.apply();
                break;
        }

        Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
    }
}