package com.ankan.retrofitsqlitemvvm;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ankan.retrofitsqlitemvvm.Database.TestDBObject;
import com.ankan.retrofitsqlitemvvm.RecyclerView.TestRecyclerAdapter;
import com.ankan.retrofitsqlitemvvm.Retrofit.JsonPlaceHolderApi;
import com.ankan.retrofitsqlitemvvm.Retrofit.Posts;
import com.ankan.retrofitsqlitemvvm.ViewModel.TestViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button addTestObjectButton;

    TestViewModel testViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addTestObjectButton = (Button)findViewById(R.id.additems);

        addTestObjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publishDialog();
            }
        });

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.main_recyclerview);
        final TestRecyclerAdapter adapter = new TestRecyclerAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        testViewModel.getAllData().observe(this, new Observer<List<TestDBObject>>() {
            @Override
            public void onChanged(@Nullable List<TestDBObject> testDBObjects) {
                adapter.setList(testDBObjects);
            }
        });

        createApiCall();

    }

    void createApiCall()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Posts>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Error Receiving", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Posts> posts = response.body();

                if(posts != null)
                for(Posts p: posts)
                {
                    testViewModel.insert(new TestDBObject(p.getTitle(),p.getBody(),String.valueOf(p.getId())));
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    void publishDialog(){

        final AlertDialog dialog;
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.dialog,null);

        final EditText idET = view.findViewById(R.id.identifyDialog);
        final EditText nameET = view.findViewById(R.id.name);
        final EditText descET = view.findViewById(R.id.desc);
        final Button save = view.findViewById(R.id.save);

        mBuilder.setView(view);
        dialog = mBuilder.create();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = idET.getText().toString();
                String name = nameET.getText().toString();
                String description = descET.getText().toString();

                if(!TextUtils.isEmpty(id.trim()) && !TextUtils.isEmpty(name.trim()) && !TextUtils.isEmpty(description.trim()))
                {
                    testViewModel.insert(new TestDBObject(name,description,id));
                    Toast.makeText(MainActivity.this, "Saving", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else
                    Toast.makeText(MainActivity.this, "Fields can't be Empty", Toast.LENGTH_SHORT).show();

            }
        });

        dialog.setTitle("Insert");
        dialog.show();
    }
}
