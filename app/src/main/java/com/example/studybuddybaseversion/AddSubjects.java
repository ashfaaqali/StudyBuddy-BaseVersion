package com.example.studybuddybaseversion;

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
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studybuddybaseversion.ScheduleFragmentModels.NotificationReceiver;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class AddSubjects extends AppCompatActivity {
    EditText SubjectName1, SubjectName2, SubjectName3, SubjectName4, SubjectName5;
    EditText RequiredAttendance1, RequiredAttendance2, RequiredAttendance3, RequiredAttendance4, RequiredAttendance5;
    Button Save;
    ImageView SelectClassTime1;
    String day;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public  MaterialTimePicker materialTimePicker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
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
        Save = (Button) findViewById(R.id.add_subjects);
        SelectClassTime1 = (ImageView) findViewById(R.id.select_time1);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        day = (String) bundle.get("day");
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveToSharedPref();
                SetReminder();
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
        CreateNotificationChannel();
        SelectClassTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowTimePicker();
            }
        });
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
                String selectedTimePM;
                String selectedTimeAM;
                if (materialTimePicker.getHour() > 12){
                    selectedTimePM = String.format("%02d", (materialTimePicker.getHour()-12))+" : "+String.format("%02d", materialTimePicker.getMinute())+" PM";
                    editor.putString("ClassTime1", selectedTimePM);
                    editor.apply();
                    Toast.makeText(AddSubjects.this, selectedTimePM, Toast.LENGTH_SHORT).show();
                } else {
                    selectedTimeAM = materialTimePicker.getHour()+" : "  +  materialTimePicker.getMinute()+" AM";
                    editor.putString("ClassTime1", selectedTimeAM);
                    editor.apply();
                    Toast.makeText(AddSubjects.this, selectedTimeAM, Toast.LENGTH_SHORT).show();
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
        editor = sharedPreferences.edit();

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