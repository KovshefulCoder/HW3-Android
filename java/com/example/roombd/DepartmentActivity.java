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

public class DepartmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        configView();
    }

    private  List<Department> departments;

    private void configView() {
        Department department = new Department();
        departments = new ArrayList<>();
        EditText inputName = findViewById(R.id.departmentInputName);
        EditText inputHours = findViewById(R.id.departmentInputHours);
        ListView listView = findViewById(R.id.listView);

        Button buttonAdd = findViewById(R.id.departmentAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                department.setHours(inputHours.getText().toString());
                department.setName(inputName.getText().toString());
                ClassDB.getClassDB(getApplicationContext()).departmentDAO().insertDepartment(department);
                departments = ClassDB.getClassDB(getApplicationContext()).departmentDAO().findAllDepartment();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DepartmentActivity.this, android.R.layout.simple_list_item_1, show(departments));
                listView.setAdapter(adapter);
                Toast.makeText(getApplicationContext(),"Запись добавлена",Toast.LENGTH_LONG).show();
            }
        });

        Button buttonShowAll = findViewById(R.id.departmentShowAll);
        buttonShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                departments = ClassDB.getClassDB(getApplicationContext()).departmentDAO().findAllDepartment();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DepartmentActivity.this, android.R.layout.simple_list_item_1, show(departments));
                listView.setAdapter(adapter);
            }
        });

        Button buttonFindByName = findViewById(R.id.departmentFindByName);
        buttonFindByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                departments = ClassDB.getClassDB(getApplicationContext()).departmentDAO().findDepartmentByName(inputName.getText().toString());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DepartmentActivity.this, android.R.layout.simple_list_item_1, show(departments));
                listView.setAdapter(adapter);
            }
        });

        Button buttonFindByHours = findViewById(R.id.departmentFindByHours);
        buttonFindByHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                departments = ClassDB.getClassDB(getApplicationContext()).departmentDAO().findDepartmentByHours(inputHours.getText().toString());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DepartmentActivity.this, android.R.layout.simple_list_item_1, show(departments));
                listView.setAdapter(adapter);
            }
        });

        Button deleteByName = findViewById(R.id.departmentDeleteByName);
        deleteByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClassDB.getClassDB(getApplicationContext()).departmentDAO().deleteDepartmentByName(inputName.getText().toString());
                departments = ClassDB.getClassDB(getApplicationContext()).departmentDAO().findAllDepartment();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DepartmentActivity.this, android.R.layout.simple_list_item_1, show(departments));
                listView.setAdapter(adapter);
                Toast.makeText(getApplicationContext(),"Запись удалена",Toast.LENGTH_LONG).show();
            }
        });
    }

    private <E> String[] show(List<E> array){
        return array.stream().map(E::toString).toArray(String[]::new);
    }
}