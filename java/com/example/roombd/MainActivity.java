package com.example.roombd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configView();
    }

    private void configView(){
        Button buttonEmployee = findViewById(R.id.mainAcitivityEmployee);
        buttonEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EmployeeActivity.class));
            }
        });
        Button buttonDepartment = findViewById(R.id.mainAcitivityDepartment);
        buttonDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DepartmentActivity.class));
            }
        });
        Button buttonOffice = findViewById(R.id.mainAcitivityOffice);
        buttonOffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), OfficeActivity.class));
            }
        });
    }
}