package com.ankan.retrofitsqlitemvvm.RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ankan.retrofitsqlitemvvm.Database.TestDBObject;
import com.ankan.retrofitsqlitemvvm.R;

import java.util.ArrayList;
import java.util.List;

public class TestRecyclerAdapter extends RecyclerView.Adapter<TestRecyclerAdapter.TestHolder> {

    private List<TestDBObject> testDBObjectList = new ArrayList<>();

    public TestRecyclerAdapter(){
    }

    @NonNull
    @Override
    public TestHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.test_item,viewGroup,false);
        return new TestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestHolder testHolder, int i) {

        TestDBObject testDBObject = testDBObjectList.get(i);

        testHolder.id.setText(testDBObject.getPriority());
        testHolder.name.setText(testDBObject.getName());
        testHolder.description.setText(testDBObject.getDescription());

    }

    @Override
    public int getItemCount() {
        return testDBObjectList.size();
    }

    public void setList(List<TestDBObject> testDBObjectList)
    {
        this.testDBObjectList = testDBObjectList;
        notifyDataSetChanged();
    }



    class TestHolder extends RecyclerView.ViewHolder{

        private TextView id;
        private TextView name;
        private TextView description;

         TestHolder(@NonNull View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.identify);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }
}
