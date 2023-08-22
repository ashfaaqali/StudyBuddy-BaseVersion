package com.example.studybuddybaseversion;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WedScheduleFragment extends Fragment {
    Button PresentBtn1, PresentBtn2, PresentBtn3, PresentBtn4, PresentBtn5;
    Button AbsentBtn1, AbsentBtn2, AbsentBtn3, AbsentBtn4, AbsentBtn5;
    Button CancelledBtn1, CancelledBtn2, CancelledBtn3, CancelledBtn4, CancelledBtn5;
    TextView TotalClasses1, TotalClasses2, TotalClasses3, TotalClasses4, TotalClasses5;
    TextView PresentClasses1, PresentClasses2, PresentClasses3, PresentClasses4, PresentClasses5;
    TextView AbsentClasses1, AbsentClasses2, AbsentClasses3, AbsentClasses4, AbsentClasses5;
    TextView CancelledClasses1, CancelledClasses2, CancelledClasses3, CancelledClasses4, CancelledClasses5;
    TextView CurrentPercentage1, CurrentPercentage2, CurrentPercentage3, CurrentPercentage4, CurrentPercentage5;
    CardView CV1, CV2, CV3, CV4, CV5;
    LinearLayout LinearLayout1, LinearLayout2, LinearLayout3, LinearLayout4, LinearLayout5;
    String s1, s2, s3, s4, s5;
    int totalClasses1=0, totalClasses2=0, totalClasses3=0, totalClasses4=0, totalClasses5=0;
    int presentCount1=0, presentCount2=0, presentCount3=0, presentCount4=0, presentCount5=0;
    int absentCount1=0, absentCount2=0, absentCount3=0, absentCount4=0, absentCount5=0;
    int cancelledCount1=0, cancelledCount2=0, cancelledCount3=0, cancelledCount4=0, cancelledCount5=0;
    float currentPercentage1=0, currentPercentage2=0, currentPercentage3=0,currentPercentage4=0, currentPercentage5=0;
    FloatingActionButton AddButton;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.wed_schedule_fragment, null);

        //All text views for displaying information
        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TotalClasses1 = view.findViewById(R.id.total_classes_1); TotalClasses2 = view.findViewById(R.id.total_classes_2);
                TotalClasses3 = view.findViewById(R.id.total_classes_3); TotalClasses4 = view.findViewById(R.id.total_classes_4);
                TotalClasses5 = view.findViewById(R.id.total_classes_5);
                PresentClasses1 = view.findViewById(R.id.present_classes_1); PresentClasses2 = view.findViewById(R.id.present_classes_2);
                PresentClasses3 = view.findViewById(R.id.present_classes_3);PresentClasses4 = view.findViewById(R.id.present_classes_4);
                PresentClasses5 = view.findViewById(R.id.present_classes_5);
                AbsentClasses1 = view.findViewById(R.id.absent_classes_1); AbsentClasses2 = view.findViewById(R.id.absent_classes_2);
                AbsentClasses3 = view.findViewById(R.id.absent_classes_3); AbsentClasses4 = view.findViewById(R.id.absent_classes_4);
                AbsentClasses5 = view.findViewById(R.id.absent_classes_5);
                CancelledClasses1 = view.findViewById(R.id.cancelled_classes_1); CancelledClasses2 = view.findViewById(R.id.cancelled_classes_2);
                CancelledClasses3 = view.findViewById(R.id.cancelled_classes_3); CancelledClasses4 = view.findViewById(R.id.cancelled_classes_4);
                CancelledClasses5 = view.findViewById(R.id.cancelled_classes_5);
                CurrentPercentage1 = view.findViewById(R.id.current_percentage_1); CurrentPercentage2 = view.findViewById(R.id.current_percentage_2);
                CurrentPercentage3 = view.findViewById(R.id.current_percentage_3); CurrentPercentage4 = view.findViewById(R.id.current_percentage_4);
                CurrentPercentage5 = view.findViewById(R.id.current_percentage_5);

                //Card Views for visibility setting
                CV1 = (CardView) view.findViewById(R.id.cv1); CV2 = (CardView) view.findViewById(R.id.cv2); CV3 = (CardView) view.findViewById(R.id.cv3);
                CV4 = (CardView) view.findViewById(R.id.cv4);CV5 = (CardView) view.findViewById(R.id.cv5);

                //Linear layouts to expand
                LinearLayout1 = (LinearLayout) view.findViewById(R.id.expandable_layout1);
                LinearLayout2 = (LinearLayout) view.findViewById(R.id.expandable_layout2);
                LinearLayout3 = (LinearLayout) view.findViewById(R.id.expandable_layout3);
                LinearLayout4 = (LinearLayout) view.findViewById(R.id.expandable_layout4);
                LinearLayout5 = (LinearLayout) view.findViewById(R.id.expandable_layout5);

                //Counter Buttons
                PresentBtn1 = (Button) view.findViewById(R.id.present_button1); PresentBtn2 = (Button) view.findViewById(R.id.present_button2);
                PresentBtn3 = (Button) view.findViewById(R.id.present_button3); PresentBtn4 = (Button) view.findViewById(R.id.present_button4);
                PresentBtn5 = (Button) view.findViewById(R.id.present_button5);
                AbsentBtn1 = (Button) view.findViewById(R.id.absent_button1); AbsentBtn2 = (Button) view.findViewById(R.id.absent_button2);
                AbsentBtn3 = (Button) view.findViewById(R.id.absent_button3);AbsentBtn4 = (Button) view.findViewById(R.id.absent_button4);
                AbsentBtn5 = (Button) view.findViewById(R.id.absent_button5);
                CancelledBtn1 = (Button) view.findViewById(R.id.cancelled_button1); CancelledBtn2 = (Button) view.findViewById(R.id.cancelled_button2);
                CancelledBtn3 = (Button) view.findViewById(R.id.cancelled_button3);CancelledBtn4 = (Button) view.findViewById(R.id.cancelled_button4);
                CancelledBtn5 = (Button) view.findViewById(R.id.cancelled_button5);

                AddButton = (FloatingActionButton) view.findViewById(R.id.add_subjects);
            }
        });

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddSubjects.class);
                String day = "wednesday";
                intent.putExtra("day", day);
                startActivity(intent);
            }
        });
//        Card 1 Buttons
        CV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout1.setVisibility(View.VISIBLE);
            }
        });
        CardView1Attendance();
        CV2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout2.setVisibility(View.VISIBLE);
            }
        });
        CardView2Attendance();
        CV3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout3.setVisibility(View.VISIBLE);
            }
        });
        CardView3Attendance();
        CV4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout4.setVisibility(View.VISIBLE);
            }
        });
        CardView4Attendance();
        CV5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout5.setVisibility(View.VISIBLE);
            }
        });
        CardView5Attendance();
        DisplaySchedule();
        return view;
    }

    private void CardView5Attendance() {
        sharedPreferences = requireActivity().getSharedPreferences("StudyBuddy", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        totalClasses5 = presentCount5+absentCount5+cancelledCount5;
        currentPercentage5 = (float) presentCount5  / totalClasses5 * 100;

        editor.putFloat("Wed Percentage Count 5", currentPercentage5);
        editor.putInt("Wed Total Count 5", totalClasses5);
        editor.apply();

        sharedPreferences = requireActivity().getSharedPreferences("StudyBuddy", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        PresentBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setCancelable(true);
                builder.setMessage("Tap yes to confirm presence in "+s5+" class?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //CODE FOR POSITIVE RESPONSE
                                presentCount5++;
                                editor.putInt("Wed Present Count 5", presentCount5);
                                editor.apply();
                                Toast.makeText(getContext(), "presence recorded in "+s5, Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //CODE FOR NEGATIVE RESPONSE
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        AbsentBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setCancelable(true);
                builder.setMessage("Tap yes to confirm absence in "+s5+" class?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //CODE FOR POSITIVE RESPONSE
                                absentCount5++;
                                editor.putInt("Wed Absent Count 5", absentCount5);
                                editor.apply();
                                Toast.makeText(getContext(), "absence recorded in "+s5, Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //CODE FOR NEGATIVE RESPONSE
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        CancelledBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setCancelable(true);
                builder.setMessage("Tap yes to confirm class cancellation in "+54+" class?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //CODE FOR POSITIVE RESPONSE
                                cancelledCount5++;
                                editor.putInt("Wed Cancel Count 5", cancelledCount5);
                                editor.apply();
                                Toast.makeText(getContext(), "cancellation recorded in "+s5, Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //CODE FOR NEGATIVE RESPONSE
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void CardView4Attendance() {
        sharedPreferences = requireActivity().getSharedPreferences("StudyBuddy", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        totalClasses4 = presentCount4+absentCount4+cancelledCount4;
        currentPercentage4 = (float) presentCount4  / totalClasses4 * 100;

        editor.putFloat("Wed Percentage Count 4", currentPercentage4);
        editor.putInt("Wed Total Count 4", totalClasses4);
        editor.apply();

        PresentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setCancelable(true);
                builder.setMessage("Tap yes to confirm presence in "+s4+" class?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //CODE FOR POSITIVE RESPONSE
                                presentCount4++;
                                editor.putInt("Wed Present Count 4", presentCount4);
                                editor.apply();
                                Toast.makeText(getContext(), "presence recorded in "+s4, Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //CODE FOR NEGATIVE RESPONSE
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        AbsentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setCancelable(true);
                builder.setMessage("Tap yes to confirm absence in "+s4+" class?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //CODE FOR POSITIVE RESPONSE
                                absentCount4++;
                                editor.putInt("Wed Absent Count 4", absentCount4);
                                editor.apply();
                                Toast.makeText(getContext(), "absence recorded in "+s4, Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //CODE FOR NEGATIVE RESPONSE
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        CancelledBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setCancelable(true);
                builder.setMessage("Tap yes to confirm class cancellation in "+s4+" class?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //CODE FOR POSITIVE RESPONSE
                                cancelledCount4++;
                                editor.putInt("Wed Cancel Count 4", cancelledCount4);
                                editor.apply();
                                Toast.makeText(getContext(), "cancellation recorded in "+s4, Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //CODE FOR NEGATIVE RESPONSE
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void CardView3Attendance() {
        sharedPreferences = requireActivity().getSharedPreferences("StudyBuddy", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        totalClasses3 = presentCount3+absentCount3+cancelledCount3;
        currentPercentage3 = (float) presentCount3  / totalClasses3 * 100;

        editor.putFloat("Wed Percentage Count 3", currentPercentage3);
        editor.putInt("Wed Total Count 3", totalClasses3);
        editor.apply();

        PresentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setCancelable(true);
                builder.setMessage("Tap yes to confirm presence in "+s1+" class?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //CODE FOR POSITIVE RESPONSE
                                presentCount3++;
                                editor.putInt("Wed Present Count 3", presentCount3);
                                editor.apply();
                                Toast.makeText(getContext(), "presence recorded in "+s1, Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //CODE FOR NEGATIVE RESPONSE
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        AbsentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setCancelable(true);
                builder.setMessage("Tap yes to confirm absence in "+s1+" class?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //CODE FOR POSITIVE RESPONSE
                                absentCount3++;
                                editor.putInt("Wed Absent Count 3", absentCount3);
                                editor.apply();
                                Toast.makeText(getContext(), "absence recorded in "+s1, Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //CODE FOR NEGATIVE RESPONSE
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        CancelledBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setCancelable(true);
                builder.setMessage("Tap yes to confirm class cancellation in "+s1+" class?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //CODE FOR POSITIVE RESPONSE
                                cancelledCount3++;
                                editor.putInt("Wed Cancel Count 3", cancelledCount3);
                                editor.apply();
                                Toast.makeText(getContext(), "cancellation recorded in "+s1, Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //CODE FOR NEGATIVE RESPONSE
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    private void CardView2Attendance() {
        sharedPreferences = requireActivity().getSharedPreferences("StudyBuddy", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        totalClasses2 = presentCount2+absentCount2+cancelledCount2;
        currentPercentage2 = (float) presentCount2  / totalClasses2 * 100;

        editor.putFloat("Wed Percentage Count 2", currentPercentage2);
        editor.putInt("Wed Total Count 2", totalClasses2);
        editor.apply();

        PresentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setCancelable(true);
                builder.setMessage("Tap yes to confirm presence in "+s2+" class?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //CODE FOR POSITIVE RESPONSE
                                presentCount2++;
                                editor.putInt("Wed Present Count 2", presentCount2);
                                editor.apply();
                                Toast.makeText(getContext(), "presence recorded in "+s2, Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //CODE FOR NEGATIVE RESPONSE
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        AbsentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setCancelable(true);
                builder.setMessage("Tap yes to confirm absence in "+s2+" class?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //CODE FOR POSITIVE RESPONSE
                                absentCount2++;
                                editor.putInt("Wed Absent Count 2", absentCount2);
                                editor.apply();
                                Toast.makeText(getContext(), "absence recorded in "+s2, Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //CODE FOR NEGATIVE RESPONSE
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        CancelledBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setCancelable(true);
                builder.setMessage("Tap yes to confirm class cancellation in "+s2+" class?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //CODE FOR POSITIVE RESPONSE
                                cancelledCount2++;
                                editor.putInt("Wed Cancel Count 2", cancelledCount2);
                                editor.apply();
                                Toast.makeText(getContext(), "cancellation recorded in "+s2, Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //CODE FOR NEGATIVE RESPONSE
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void CardView1Attendance() {
        sharedPreferences = requireActivity().getSharedPreferences("StudyBuddy", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        totalClasses1 = presentCount1+absentCount1+cancelledCount1;
        currentPercentage1 = (float) presentCount1  / totalClasses1 * 100;

        editor.putFloat("Wed Percentage Count 1", currentPercentage1);
        editor.putInt("Wed Total Count 1", totalClasses1);
        editor.apply();

        PresentBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setCancelable(true);
                builder.setMessage("Tap yes to confirm presence in "+s1+" class?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //CODE FOR POSITIVE RESPONSE
                                presentCount1++;
                                editor.putInt("Wed Present Count 1", presentCount1);
                                editor.apply();
                                Toast.makeText(getContext(), "presence recorded in "+s1, Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //CODE FOR NEGATIVE RESPONSE
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        AbsentBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setCancelable(true);
                builder.setMessage("Tap yes to confirm absence in "+s1+" class?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //CODE FOR POSITIVE RESPONSE
                                absentCount1++;
                                editor.putInt("Wed Absent Count 1", absentCount1);
                                editor.apply();
                                Toast.makeText(getContext(), "absence recorded in "+s1, Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //CODE FOR NEGATIVE RESPONSE
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        CancelledBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setCancelable(true);
                builder.setMessage("Tap yes to confirm class cancellation in "+s1+" class?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //CODE FOR POSITIVE RESPONSE
                                cancelledCount1++;
                                editor.putInt("Wed Cancel Count 1", cancelledCount1);
                                editor.apply();
                                Toast.makeText(getContext(), "cancellation recorded in "+s1, Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //CODE FOR NEGATIVE RESPONSE
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void DisplaySchedule() {
        TextView SN1, SN2, SN3, SN4, SN5, ClassTime1, ClassTime2, ClassTime3, ClassTime4, ClassTime5;
        CardView C1, C2, C3, C4, C5;
        sharedPreferences = requireActivity().getSharedPreferences("StudyBuddy", MODE_PRIVATE);
        if (sharedPreferences.contains("Wed S_N_1")) {
            s1 = sharedPreferences.getString("Wed S_N_1", "");
            if (!s1.isEmpty()){
                TextView CurrPerInstruction = view.findViewById(R.id.current_percentage_inst);
                CurrPerInstruction.setVisibility(View.VISIBLE);
                SN1 = view.findViewById(R.id.wed_s_name1);
                C1 = view.findViewById(R.id.cv1);
                C1.setVisibility(View.VISIBLE);
                SN1.setText(s1);
                ClassTime1 = view.findViewById(R.id.class_time1);
                String time = sharedPreferences.getString("WedClassTime1", "");
                ClassTime1.setText(time);

                totalClasses1 = sharedPreferences.getInt("Wed Total Count 1", 0);
                TotalClasses1.setText(String.valueOf(totalClasses1));
                presentCount1 = sharedPreferences.getInt("Wed Present Count 1", 0);
                PresentClasses1.setText(String.valueOf(presentCount1));
                absentCount1 = sharedPreferences.getInt("Wed Absent Count 1", 0);
                AbsentClasses1.setText(String.valueOf(absentCount1));
                cancelledCount1 = sharedPreferences.getInt("Wed Cancel Count 1", 0);
                CancelledClasses1.setText(String.valueOf(cancelledCount1));
                currentPercentage1 = sharedPreferences.getFloat("Wed Percentage Count 1", 0);
                CurrentPercentage1.setText(String.valueOf(currentPercentage1));
            }

        }
        if (sharedPreferences.contains("Wed S_N_2")) {
            s2 = sharedPreferences.getString("Wed S_N_2", "");
            if (!s2.isEmpty()){
                SN2 = view.findViewById(R.id.wed_s_name2);
                C2 = view.findViewById(R.id.cv2);
                C2.setVisibility(View.VISIBLE);
                SN2.setText(s2);
                ClassTime2 = view.findViewById(R.id.class_time2);
                String time = sharedPreferences.getString("WedClassTime2", "");
                ClassTime2.setText(time);

                totalClasses2 = sharedPreferences.getInt("Wed Total Count 2", 0);
                TotalClasses2.setText(String.valueOf(totalClasses2));
                presentCount2 = sharedPreferences.getInt("Wed Present Count 2", 0);
                PresentClasses2.setText(String.valueOf(presentCount2));
                absentCount2 = sharedPreferences.getInt("Wed Absent Count 2", 0);
                AbsentClasses2.setText(String.valueOf(absentCount2));
                cancelledCount2 = sharedPreferences.getInt("Wed Cancel Count 2", 0);
                CancelledClasses2.setText(String.valueOf(cancelledCount2));
                currentPercentage2 = sharedPreferences.getFloat("Wed Percentage Count 2", 0);
                CurrentPercentage2.setText(String.valueOf(currentPercentage2));
            }
        }
        if (sharedPreferences.contains("Wed S_N_3")) {
            s3 = sharedPreferences.getString("Wed S_N_3", "");
            if (!s3.isEmpty()){
                SN3 = view.findViewById(R.id.wed_s_name3);
                C3 = view.findViewById(R.id.cv3);
                C3.setVisibility(View.VISIBLE);
                SN3.setText(s3);
                ClassTime3 = view.findViewById(R.id.class_time3);
                String time = sharedPreferences.getString("WedClassTime3", "");
                ClassTime3.setText(time);

                totalClasses3 = sharedPreferences.getInt("Wed Total Count 2", 0);
                TotalClasses3.setText(String.valueOf(totalClasses3));
                presentCount3 = sharedPreferences.getInt("Wed Present Count 2", 0);
                PresentClasses3.setText(String.valueOf(presentCount3));
                absentCount3 = sharedPreferences.getInt("Wed Absent Count 2", 0);
                AbsentClasses3.setText(String.valueOf(absentCount3));
                cancelledCount3 = sharedPreferences.getInt("Wed Cancel Count 2", 0);
                CancelledClasses3.setText(String.valueOf(cancelledCount3));
                currentPercentage3 = sharedPreferences.getFloat("Wed Percentage Count 2", 0);
                CurrentPercentage3.setText(String.valueOf(currentPercentage3));
            }
        }
        if (sharedPreferences.contains("Wed S_N_4")) {
            s4 = sharedPreferences.getString("Wed S_N_4", "");
            if (!s4.isEmpty()){
                SN4 = view.findViewById(R.id.wed_s_name4);
                C4 = view.findViewById(R.id.cv4);
                C4.setVisibility(View.VISIBLE);
                SN4.setText(s4);
                ClassTime4 = view.findViewById(R.id.class_time4);
                String time = sharedPreferences.getString("WedClassTime4", "");
                ClassTime4.setText(time);

                totalClasses4 = sharedPreferences.getInt("Wed Total Count 4", 0);
                TotalClasses4.setText(String.valueOf(totalClasses4));
                presentCount4 = sharedPreferences.getInt("Wed Present Count 4", 0);
                PresentClasses4.setText(String.valueOf(presentCount4));
                absentCount4 = sharedPreferences.getInt("Wed Absent Count 4", 0);
                AbsentClasses4.setText(String.valueOf(absentCount4));
                cancelledCount4 = sharedPreferences.getInt("Wed Cancel Count 4", 0);
                CancelledClasses4.setText(String.valueOf(cancelledCount4));
                currentPercentage4 = sharedPreferences.getFloat("Wed Percentage Count 4", 0);
                CurrentPercentage4.setText(String.valueOf(currentPercentage4));
            }
        }
        if (sharedPreferences.contains("Wed S_N_5")) {
            s5 = sharedPreferences.getString("Wed S_N_5", "");
            if (!s5.isEmpty()){
                SN5 = view.findViewById(R.id.wed_s_name5);
                C5 = view.findViewById(R.id.cv5);
                C5.setVisibility(View.VISIBLE);
                SN5.setText(s5);
                ClassTime5 = view.findViewById(R.id.class_time5);
                String time = sharedPreferences.getString("WedClassTime5", "");
                ClassTime5.setText(time);

                totalClasses5 = sharedPreferences.getInt("Wed Total Count 5", 0);
                TotalClasses5.setText(String.valueOf(totalClasses5));
                presentCount5 = sharedPreferences.getInt("Wed Present Count 5", 0);
                PresentClasses5.setText(String.valueOf(presentCount5));
                absentCount5 = sharedPreferences.getInt("Wed Absent Count 5", 0);
                AbsentClasses5.setText(String.valueOf(absentCount5));
                cancelledCount5 = sharedPreferences.getInt("Wed Cancel Count 5", 0);
                CancelledClasses5.setText(String.valueOf(cancelledCount5));
                currentPercentage5 = sharedPreferences.getFloat("Wed Percentage Count 5", 0);
                CurrentPercentage5.setText(String.valueOf(currentPercentage5));
            }
        }
    }
}
