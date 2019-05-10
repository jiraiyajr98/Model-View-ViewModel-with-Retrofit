package com.ankan.retrofitsqlitemvvm.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.ankan.retrofitsqlitemvvm.Database.TestDBObject;
import com.ankan.retrofitsqlitemvvm.Repository.TestRepository;

import java.util.List;

public class TestViewModel extends AndroidViewModel {

    private TestRepository testRepository;

    private LiveData<List<TestDBObject>> listLiveData;

    public TestViewModel(@NonNull Application application) {
        super(application);

        testRepository = new TestRepository(application);
        listLiveData = testRepository.getAll();
    }

    public void insert(TestDBObject testDBObject)
    {
        testRepository.insert(testDBObject);
    }

    public void update(TestDBObject testDBObject)
    {
        testRepository.update(testDBObject);
    }

    public void delete(TestDBObject testDBObject)
    {
        testRepository.delete(testDBObject);
    }

    public void deleteAll()
    {
        testRepository.deleteAll();
    }

    public LiveData<List<TestDBObject>> getAllData(){
        return listLiveData;
    }
}
