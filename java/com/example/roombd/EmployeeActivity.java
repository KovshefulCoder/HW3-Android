package com.example.roombd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        configView();
    }

    private  List<Employee> employees;

    private void configView() {
        Employee employee = new Employee();
        employees = new ArrayList<>();
        EditText inputName = findViewById(R.id.employeeInputName);
        EditText inputDepartment = findViewById(R.id.employeeInputDepartment);
        EditText inputPosition = findViewById(R.id.employeeInputPosition);
        ListView listView = findViewById(R.id.listView);

        Button buttonAdd = findViewById(R.id.employeeAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employee.setDepartment(inputDepartment.getText().toString());
                employee.setName(inputName.getText().toString());
                employee.setPosition(inputPosition.getText().toString());
                ClassDB.getClassDB(getApplicationContext()).employeeDAO().insertEmployee(employee);
                employees = ClassDB.getClassDB(getApplicationContext()).employeeDAO().findAllEmployee();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(EmployeeActivity.this, android.R.layout.simple_list_item_1, show(employees));
                listView.setAdapter(adapter);
                Toast.makeText(getApplicationContext(),"Запись добавлена",Toast.LENGTH_LONG).show();
            }
        });

        Button buttonShowAll = findViewById(R.id.employeeShowAll);
        buttonShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employees = ClassDB.getClassDB(getApplicationContext()).employeeDAO().findAllEmployee();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(EmployeeActivity.this, android.R.layout.simple_list_item_1, show(employees));
                listView.setAdapter(adapter);
            }
        });

        Button buttonFindByName = findViewById(R.id.employeeFindByName);
        buttonFindByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employees = ClassDB.getClassDB(getApplicationContext()).employeeDAO().findEmployeeByName(inputName.getText().toString());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(EmployeeActivity.this, android.R.layout.simple_list_item_1, show(employees));
                listView.setAdapter(adapter);
            }
        });

        Button buttonFindByDepartment = findViewById(R.id.employeeFindByDepartment);
        buttonFindByDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employees = ClassDB.getClassDB(getApplicationContext()).employeeDAO().findEmployeeByDepartment(inputDepartment.getText().toString());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(EmployeeActivity.this, android.R.layout.simple_list_item_1, show(employees));
                listView.setAdapter(adapter);
            }
        });

        Button buttonFindByPosition = findViewById(R.id.employeeFindByPosition);
        buttonFindByPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employees = ClassDB.getClassDB(getApplicationContext()).employeeDAO().findEmployeeByPosition(inputPosition.getText().toString());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(EmployeeActivity.this, android.R.layout.simple_list_item_1, show(employees));
                listView.setAdapter(adapter);
            }
        });

        Button deleteByName = findViewById(R.id.employeeDeleteByName);
        deleteByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClassDB.getClassDB(getApplicationContext()).employeeDAO().deleteEmployeeByName(inputName.getText().toString());
                employees = ClassDB.getClassDB(getApplicationContext()).employeeDAO().findAllEmployee();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(EmployeeActivity.this, android.R.layout.simple_list_item_1, show(employees));
                listView.setAdapter(adapter);
                Toast.makeText(getApplicationContext(),"Запись удалена",Toast.LENGTH_LONG).show();
            }
        });
    }

    private <E> String[] show(List<E> array){
        return array.stream().map(E::toString).toArray(String[]::new);
    }
}