package com.ankan.retrofitsqlitemvvm.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.ankan.retrofitsqlitemvvm.DataAccessObject.TestDAO;

@Database(entities = {TestDBObject.class}, version = 1,exportSchema = false)
public abstract class TestDB extends RoomDatabase {

    private static TestDB testDBInstance;

    public abstract TestDAO testDAO();

    public static synchronized TestDB getTestDBInstance(Context context){

        if(testDBInstance == null)
        {
            testDBInstance = Room.databaseBuilder(context.getApplicationContext(),TestDB.class,"Test_DB")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return testDBInstance;
    }
}
