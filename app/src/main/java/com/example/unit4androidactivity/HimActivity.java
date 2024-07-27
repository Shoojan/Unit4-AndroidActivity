package com.example.unit4androidactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.unit4androidactivity.constants.Constant;

public class HimActivity extends AppCompatActivity {

    private Spinner giftsSpinner;
    private Button sendGiftButton;
    private String selectedGift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_him);

        giftsSpinner = findViewById(R.id.giftsSpinner);
        sendGiftButton = findViewById(R.id.giftSendBtn);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String[] gifts = getResources().getStringArray(R.array.gifts);

        giftsSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(
                            AdapterView<?> parent,
                            View view, int position, long id) {
                        selectedGift = gifts[position];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        selectedGift = gifts[0];
                    }
                }
        );

        sendGiftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HimActivity.this, HerActivity.class);
                intent.putExtra(Constant.GIFT, selectedGift);
//                startActivity(intent);
                startActivityForResult(intent, Constant.GIFT_SEND_CODE);
            }
        });


    }

    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constant.GIFT_SEND_CODE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    String responseFromHer = data.getStringExtra(Constant.GIFT_RESPONSE);
                    Toast.makeText(this, "She is " + responseFromHer, Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}