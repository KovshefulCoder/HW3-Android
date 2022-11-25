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

public class OfficeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office);
        configView();
    }

    private  List<Office> offices;

    private void configView() {
        Office office = new Office();
        offices = new ArrayList<>();
        EditText inputNumber = findViewById(R.id.officeInputNumber);
        EditText inputDepartment = findViewById(R.id.officeInputDepartment);
        ListView listView = findViewById(R.id.listView);

        Button buttonAdd = findViewById(R.id.officeAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                office.setDepartment(inputDepartment.getText().toString());
                office.setNumber(inputNumber.getText().toString());
                ClassDB.getClassDB(getApplicationContext()).officeDAO().insertOffice(office);
                offices = ClassDB.getClassDB(getApplicationContext()).officeDAO().findAllOffice();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(OfficeActivity.this, android.R.layout.simple_list_item_1, show(offices));
                listView.setAdapter(adapter);
                Toast.makeText(getApplicationContext(),"Запись добавлена",Toast.LENGTH_LONG).show();
            }
        });

        Button buttonShowAll = findViewById(R.id.officeShowAll);
        buttonShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offices = ClassDB.getClassDB(getApplicationContext()).officeDAO().findAllOffice();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(OfficeActivity.this, android.R.layout.simple_list_item_1, show(offices));
                listView.setAdapter(adapter);
            }
        });

        Button updateByNumber = findViewById(R.id.officeUpdateByNumber);
        updateByNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClassDB.getClassDB(getApplicationContext()).officeDAO().updateOffice(inputDepartment.getText().toString(), inputNumber.getText().toString());
                offices = ClassDB.getClassDB(getApplicationContext()).officeDAO().findAllOffice();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(OfficeActivity.this, android.R.layout.simple_list_item_1, show(offices));
                listView.setAdapter(adapter);
                Toast.makeText(getApplicationContext(),"Запись обновлена",Toast.LENGTH_LONG).show();
            }
        });

        Button buttonFindByNumber = findViewById(R.id.officeFindByNumber);
        buttonFindByNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offices = ClassDB.getClassDB(getApplicationContext()).officeDAO().findOfficeByNumber(inputNumber.getText().toString());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(OfficeActivity.this, android.R.layout.simple_list_item_1, show(offices));
                listView.setAdapter(adapter);
            }
        });

        Button buttonFindByDepartment = findViewById(R.id.officeFindByDepartment);
        buttonFindByDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offices = ClassDB.getClassDB(getApplicationContext()).officeDAO().findOfficeByDepartment(inputDepartment.getText().toString());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(OfficeActivity.this, android.R.layout.simple_list_item_1, show(offices));
                listView.setAdapter(adapter);
            }
        });

        Button deleteByNumber = findViewById(R.id.officeDeleteByNumber);
        deleteByNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClassDB.getClassDB(getApplicationContext()).officeDAO().deleteOfficeByNumber(inputNumber.getText().toString());
                offices = ClassDB.getClassDB(getApplicationContext()).officeDAO().findAllOffice();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(OfficeActivity.this, android.R.layout.simple_list_item_1, show(offices));
                listView.setAdapter(adapter);
                Toast.makeText(getApplicationContext(),"Запись удалена",Toast.LENGTH_LONG).show();
            }
        });
    }

    private <E> String[] show(List<E> array){
        return array.stream().map(E::toString).toArray(String[]::new);
    }
}