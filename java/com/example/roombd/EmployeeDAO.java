package com.example.roombd;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface EmployeeDAO {
    @Insert
    void insertEmployee(Employee employee);

    @Query("SELECT * FROM employee")
    List<Employee> findAllEmployee();

    @Query("SELECT * FROM employee where name LIKE :name")
    List<Employee> findEmployeeByName(String name);

    @Query("SELECT * FROM employee where department LIKE :department")
    List<Employee> findEmployeeByDepartment(String department);

    @Query("SELECT * FROM employee where position LIKE :position")
    List<Employee> findEmployeeByPosition(String position);

    @Query("DELETE FROM employee where name LIKE :name")
    void deleteEmployeeByName(String name);
}
