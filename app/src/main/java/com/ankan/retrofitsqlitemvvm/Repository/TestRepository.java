package com.ankan.retrofitsqlitemvvm.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.ankan.retrofitsqlitemvvm.DataAccessObject.TestDAO;
import com.ankan.retrofitsqlitemvvm.Database.TestDB;
import com.ankan.retrofitsqlitemvvm.Database.TestDBObject;

import java.util.List;

public class TestRepository {

    private TestDAO testDAO;

    private LiveData<List<TestDBObject>> listLiveData;

    public TestRepository (Application application){

        TestDB database = TestDB.getTestDBInstance(application);

        testDAO = database.testDAO();

        listLiveData = testDAO.getAllData();
    }

    public void insert(TestDBObject testDBObject){
        new InsertInTestDB(testDAO).execute(testDBObject);
    }

    public void update(TestDBObject testDBObject){
        new UpdateInTestDB(testDAO).execute(testDBObject);
    }

    public void delete(TestDBObject testDBObject){
        new DeleteInTestDB(testDAO).execute();
    }

    public void deleteAll(){
        new DeleteAllInTestDB(testDAO).execute();
    }

    public LiveData<List<TestDBObject>> getAll(){
        return listLiveData;
    }

    private static class InsertInTestDB extends AsyncTask<TestDBObject,Void,Void>{

        private TestDAO testDAO;

        InsertInTestDB(TestDAO testDAO){
            this.testDAO = testDAO;
        }

        @Override
        protected Void doInBackground(TestDBObject... testDBObjects) {
            testDAO.insert(testDBObjects[0]);
            return null;
        }
    }

    private static class UpdateInTestDB extends AsyncTask<TestDBObject,Void,Void>{

        private TestDAO testDAO;

        UpdateInTestDB(TestDAO testDAO){
            this.testDAO = testDAO;
        }

        @Override
        protected Void doInBackground(TestDBObject... testDBObjects) {
            testDAO.update(testDBObjects[0]);
            return null;
        }
    }

    private static class DeleteInTestDB extends AsyncTask<TestDBObject,Void,Void>{

        private TestDAO testDAO;

        DeleteInTestDB(TestDAO testDAO){
            this.testDAO = testDAO;
        }

        @Override
        protected Void doInBackground(TestDBObject... testDBObjects) {
            testDAO.delete(testDBObjects[0]);
            return null;
        }
    }

    private static class DeleteAllInTestDB extends AsyncTask<Void,Void,Void>{

        private TestDAO testDAO;

        DeleteAllInTestDB(TestDAO testDAO){
            this.testDAO = testDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            testDAO.deleteAllData();
            return null;
        }
    }

}
