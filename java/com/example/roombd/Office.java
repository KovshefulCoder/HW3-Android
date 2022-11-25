package com.example.roombd;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Office {
    @ColumnInfo(name = "number")
    @PrimaryKey
    @NonNull
    private String number;
    @ColumnInfo(name = "department")
    private String department;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return number + " " + department;
    }
}
