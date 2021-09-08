package com.dlocal.sampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.dlocal.datacollector.DLCollector;
import com.dlocal.datacollector.api.DLAdditionalData;
import com.dlocal.sampleapp.databinding.ActivityJavaSampleBinding;

public class JavaSampleActivity extends AppCompatActivity {

    private ActivityJavaSampleBinding binding;
    private final DLCollector dlCollector = DLCollector.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJavaSampleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.startSessionButton.setOnClickListener(button -> startSession());
        binding.getSessionIdButton.setOnClickListener(button -> getSessionId());

        binding.kotlinSampleButton.setOnClickListener(button -> {
            Intent intent = new Intent(this, SampleActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void startSession() {
        DLAdditionalData additionalData = new DLAdditionalData("USER_REFERENCE_ID");
        dlCollector.startSession(additionalData);
        binding.sessionIdText.setText(getString(R.string.session_started));
    }

    private void getSessionId() {
        String sessionId = dlCollector.getSessionId();
        binding.sessionIdText.setText(sessionId != null ? sessionId : getString(R.string.no_session_id));
    }
}