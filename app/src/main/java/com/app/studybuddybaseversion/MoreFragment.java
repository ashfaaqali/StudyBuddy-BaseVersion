package com.app.studybuddybaseversion;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
public class MoreFragment extends Fragment {
    TextView Name, ShareButton, AboutButton, AboutExpand;
    RelativeLayout ExitButton;
    SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.more_fragment, null);
        Name = v.findViewById(R.id.name_textview);
        ExitButton = v.findViewById(R.id.exit_button);
        ShareButton = v.findViewById(R.id.share_app);
        AboutButton = v.findViewById(R.id.about_button);
        AboutExpand = v.findViewById(R.id.about_detail_textview);

        AboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutExpand.setVisibility(View.VISIBLE);
            }
        });

        ShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareApp();
            }
        });
        
        ExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();
;            }
        });

        return v;
    }

    private void ShareApp() {
        String appLink = "https://play.google.com/store/apps/details?id=com.example.studybuddybaseversion";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Check out this app!");
        intent.putExtra(Intent.EXTRA_TEXT, "Download the app: " + appLink);
        startActivity(Intent.createChooser(intent, "Share via"));
    }
}
