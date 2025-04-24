package com.analytics.useranalyticsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.analytics.client_sdk.AnalyticsTracker;
import com.analytics.useranalyticsproject.R;

public class MainActivity extends AppCompatActivity {

    EditText editUserId;
    Button btnTrack;
    TextView txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUserId = findViewById(R.id.editUserId);
        btnTrack = findViewById(R.id.btnTrack);
        txtStatus = findViewById(R.id.txtStatus);

        btnTrack.setOnClickListener(v -> {
            String userId = editUserId.getText().toString();
            if (userId.isEmpty()) {
                txtStatus.setText("Please enter a User ID");
                return;
            }

            AnalyticsTracker.init(this, userId);
            AnalyticsTracker.trackAction("login_clicked");
            txtStatus.setText("Action sent ✅");
        });
    }
}
