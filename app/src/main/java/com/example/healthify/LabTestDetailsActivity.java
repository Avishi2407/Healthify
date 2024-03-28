package com.example.healthify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LabTestDetailsActivity extends AppCompatActivity {

    TextView tvPackageName, tvTotalCost;
    EditText edDetails;
    Button btnAddToCart, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        tvPackageName = findViewById(R.id.textViewLDPackageName);
        tvTotalCost = findViewById(R.id.textViewLDTotalCost);
        edDetails = findViewById(R.id.editTextLDTextMultiline);
        btnAddToCart = findViewById(R.id.buttonLDAddToCart);
        btnBack = findViewById(R.id.buttonLDBack);

        edDetails.setKeyListener(null);

        Intent intent = getIntent();
        String packageName = intent.getStringExtra("text1");
        String packageDetails = intent.getStringExtra("text2");
        String totalCost = intent.getStringExtra("text3");

        tvPackageName.setText(packageName);
        edDetails.setText(packageDetails);
        tvTotalCost.setText("Total cost: " + totalCost + "/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "");
                String product = tvPackageName != null ? tvPackageName.getText().toString() : "";

                String totalCostStr = intent.getStringExtra("text3");
                String[] parts = totalCostStr.split(": ");
                float price = 0.0f;
                if (parts.length == 2) {
                    try {
                        price = Float.parseFloat(parts[1]);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                tvTotalCost.setText("Total cost: " + price + "/-");


                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                if (db.checkCart(username, product) == 1) {
                    Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();
                } else {
                    db.addCart(username, product, price, "lab");
                    Toast.makeText(getApplicationContext(), "Record Inserted to Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
                }
            }
        });

    }
}
