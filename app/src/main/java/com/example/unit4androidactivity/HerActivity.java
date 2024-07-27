package com.example.unit4androidactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unit4androidactivity.constants.Constant;

public class HerActivity extends AppCompatActivity {
    private TextView giftMessageTextView;
    private Spinner replySpinner;

    private String[] emotions;
    private String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_her);

        giftMessageTextView = findViewById(R.id.giftMessage);
        replySpinner = findViewById(R.id.replySpinner);

        emotions = getResources().getStringArray(R.array.emotions);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Get the gift sent from him
        Intent intent = getIntent();
        String gift = intent.getStringExtra(Constant.GIFT);
        giftMessageTextView.setText("Gift 🎁 from him: " + gift);

        // Determine the response based on the gift
        replySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                response = emotions[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                response = emotions[0];
            }
        });
    }


    public void onReplyBtnClicked(View view) {
        // Send back the response
        Intent responseIntent = new Intent();
        responseIntent.putExtra(Constant.GIFT_RESPONSE, response);
        setResult(RESULT_OK, responseIntent);
        finish();
    }
}