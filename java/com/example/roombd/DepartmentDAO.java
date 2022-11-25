package com.example.roombd;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface DepartmentDAO {
    @Insert
    void insertDepartment(Department department);

    @Query("SELECT * FROM department")
    List<Department> findAllDepartment();

    @Query("SELECT * FROM department where name LIKE :name")
    List<Department> findDepartmentByName(String name);

    @Query("SELECT * FROM department where hours LIKE :hours")
    List<Department> findDepartmentByHours(String hours);

    @Query("DELETE FROM department where name LIKE :name")
    void deleteDepartmentByName(String name);
}
