package dev.th0m4s.labelshackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ResultActivity extends AppCompatActivity {
    ResultFragment resultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultFragment = (ResultFragment) getSupportFragmentManager().findFragmentById(R.id.resultActivityFragment);
        Intent startIntent = getIntent();

        new Thread(() -> {
            resultFragment.loadResult(startIntent.getStringExtra("code"), false);
        }).start();
    }
}