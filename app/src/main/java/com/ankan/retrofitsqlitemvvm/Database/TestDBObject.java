package com.ankan.retrofitsqlitemvvm.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "TEST_TABLE")
public class TestDBObject {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String description;

    private String priority;

    public TestDBObject(String name, String description, String priority) {
        this.name = name;
        this.description = description;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }
}
