package com.example.expandablelistview.roomdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class Product implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "task")
    private String task;

    @ColumnInfo(name = "desc")
    private String desc;

    @ColumnInfo(name = "finish_by")
    private String finishBy;

    @ColumnInfo(name = "finished")
    private boolean finished;
}
