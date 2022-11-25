package com.example.roombd;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface OfficeDAO {
    @Insert
    void insertOffice(Office office);

    @Query("SELECT * FROM office")
    List<Office> findAllOffice();

    @Query("SELECT * FROM office WHERE number=:number")
    List<Office> findOfficeByNumber(String number);

    @Query("SELECT * FROM office WHERE department=:department")
    List<Office> findOfficeByDepartment(String department);

    @Query("UPDATE office SET department=:department WHERE number =:number")
    void updateOffice(String department, String number);

    @Query("DELETE FROM office WHERE number=:number")
    void deleteOfficeByNumber(String number);
}
