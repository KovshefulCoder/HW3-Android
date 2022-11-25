package com.example.roombd;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Department.class, Employee.class, Office.class}, version = 1)
public abstract class ClassDB extends RoomDatabase {

    private static ClassDB INSTANCE;

    public abstract DepartmentDAO departmentDAO();

    public abstract EmployeeDAO employeeDAO();

    public abstract OfficeDAO officeDAO();

    public static ClassDB getClassDB(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ClassDB.class, "Company")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
