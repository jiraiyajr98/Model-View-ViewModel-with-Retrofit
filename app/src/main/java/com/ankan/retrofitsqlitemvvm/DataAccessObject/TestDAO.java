package com.ankan.retrofitsqlitemvvm.DataAccessObject;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.ankan.retrofitsqlitemvvm.Database.TestDBObject;

import java.util.List;

@Dao
public interface TestDAO {

    @Insert
    void insert(TestDBObject testDBObject);

    @Update
    void update(TestDBObject testDBObject);

    @Delete
    void delete(TestDBObject testDBObject);

    @Query("DELETE FROM TEST_TABLE")
    void deleteAllData();

    @Query("SELECT * FROM TEST_TABLE ORDER BY priority DESC")
    LiveData<List<TestDBObject>> getAllData();
}
