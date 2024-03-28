package com.example.healthify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailActivity extends AppCompatActivity {

    private String[][] doctor_detail1 = {
            {"Doctor Name : Avishi Jain" , "Hospital Address : Jain's Hospital", "Exp : 5 yrs" , "Mobile no. : 8989898989", "3000"},
            {"Doctor Name : Rahul Gupta" , "Hospital Address : Gupta Clinic", "Exp : 8 yrs" , "Mobile no. : 9876543210", "3500"},
            {"Doctor Name : Priya Sharma" , "Hospital Address : Sharma's Clinic", "Exp : 6 yrs" , "Mobile no. : 8765432109", "3200"},
            {"Doctor Name : Amit Patel" , "Hospital Address : Patel Medical Center", "Exp : 9 yrs" , "Mobile no. : 7654321098", " 3000"},
            {"Doctor Name : Anjali Singh" , "Hospital Address : Singh Health Point", "Exp : 7 yrs" , "Mobile no. : 6543210987", "3500"}

    };

    private String[][] doctor_detail2 = {

            {"Doctor Name : Aarav Kumar" , "Hospital Address : Kumar's Clinic", "Exp : 4 yrs" , "Mobile no. : 9988776655", "2800"},
            {"Doctor Name : Neha Shah" , "Hospital Address : Shah Medical Center", "Exp : 10 yrs" , "Mobile no. : 8877665544", "3200"},
            {"Doctor Name : Rohit Verma" , "Hospital Address : Verma Clinic", "Exp : 7 yrs" , "Mobile no. : 7766554433", "3000"},
            {"Doctor Name : Pooja Patel" , "Hospital Address : Patel Health Point", "Exp : 6 yrs" , "Mobile no. : 6655443322", "3400"},
            {"Doctor Name : Sameer Singh" , "Hospital Address : Singh Clinic", "Exp : 8 yrs" , "Mobile no. : 5544332211", "3100"}

    };

    private String[][] doctor_detail3 = {
            {"Doctor Name : Riya Kumar" , "Hospital Address : Kumar's Clinic", "Exp : 5 yrs" , "Mobile no. : 8989898989", " 3000"},

            {"Doctor Name : Sameer Gupta" , "Hospital Address : Gupta Clinic", "Exp : 8 yrs" , "Mobile no. : 9876543210", " 3500"},

            {"Doctor Name : Nidhi Sharma" , "Hospital Address : Sharma's Clinic", "Exp : 6 yrs" , "Mobile no. : 8765432109", " 3200"},

            {"Doctor Name : Arjun Patel" , "Hospital Address : Patel Medical Center", "Exp : 9 yrs" , "Mobile no. : 7654321098", " 3000"},

            {"Doctor Name : Karishma Singh" , "Hospital Address : Singh Health Point", "Exp : 7 yrs" , "Mobile no. : 6543210987", "3500"}
    };
    private String[][] doctor_detail4 = {
            {"Doctor Name : Sophie Müller" , "Hospital Address : Müller's Clinic", "Exp : 5 yrs" , "Mobile no. : +49123456789", "300"},

            {"Doctor Name : Luca Rossi" , "Hospital Address : Rossi Clinic", "Exp : 8 yrs" , "Mobile no. : +393456789012", "350"},

            {"Doctor Name : Maria García" , "Hospital Address : García Medical Center", "Exp : 6 yrs" , "Mobile no. : +34678901234", "320"},

            {"Doctor Name : Pierre Dupont" , "Hospital Address : Dupont Health Point", "Exp : 9 yrs" , "Mobile no. : +33123456789", "300"},

            {"Doctor Name : Anna Petrov" , "Hospital Address : Petrov Clinic", "Exp : 7 yrs" , "Mobile no. : +74987654321", "350"}

    };
    private String[][] doctor_detail5 = {
            {"Doctor Name : Aarav Patel" , "Hospital Address : Patel Clinic", "Exp : 5 yrs" , "Mobile no. : 9876543210", "3000"},

            {"Doctor Name : Naina Sharma" , "Hospital Address : Sharma Medical Center", "Exp : 8 yrs" , "Mobile no. : 8765432109", "3500"},

            {"Doctor Name : Rohan Gupta" , "Hospital Address : Gupta Health Point", "Exp : 6 yrs" , "Mobile no. : 7654321098", "3200"},

            {"Doctor Name : Alisha Singh" , "Hospital Address : Singh Clinic", "Exp : 9 yrs" , "Mobile no. : 6543210987", "3000"},

            {"Doctor Name : Aditya Mishra" , "Hospital Address : Mishra's Clinic", "Exp : 7 yrs" , "Mobile no. : 5432109876", "3500"}








    };




    TextView tv;
    Button btn;

    String [][] doctor_details = {};
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);

        tv = findViewById(R.id.textviewDDTitle);
        btn = findViewById(R.id.buttonLTBack);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physicians") == 0)
            doctor_details = doctor_detail1;
        else
        if(title.compareTo("Dietician") == 0)
            doctor_details = doctor_detail2;
        else
        if(title.compareTo("Dentist") == 0)
            doctor_details = doctor_detail3;
        else
        if(title.compareTo("Surgeon") == 0)
            doctor_details = doctor_detail4;
        else
            doctor_details = doctor_detail5;


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailActivity.this, FindDoctorActivity.class));

            }
        });

        list = new ArrayList();
        for(int i = 0; i< doctor_details.length;i++ ) {
            item = new HashMap<String, String>();
            item.put("Line1", doctor_details[i][0]);
            item.put("Line2", doctor_details[i][1]);
            item.put("Line3", doctor_details[i][2]);
            item.put("Line4", doctor_details[i][3]);
            item.put("Line5", "Cons Fess:" + doctor_details[i][4] + "-/");
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"Line1", "Line2", "Line3", "Line4", "Line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );

        ListView lst = findViewById(R.id.listViewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1", title);
                it.putExtra("text2", doctor_details[i][0]);
                it.putExtra("text3", doctor_details[i][1]);
                it.putExtra("text4", doctor_details[i][3]);
                it.putExtra("text5", doctor_details[i][4]);

                startActivity(it);
            }
        });


        }
    }
