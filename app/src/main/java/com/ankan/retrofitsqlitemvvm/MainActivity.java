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
import com.ankan.retrofitsqlitemvvm.ViewModel.TestViewModel;

import java.util.ArrayList;
import java.util.List;

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
