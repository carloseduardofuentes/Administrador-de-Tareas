package com.treming.task.Global;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.treming.task.Models.*;
import com.treming.task.R;

import java.util.ArrayList;
import java.util.List;

public class DataTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_task);
    }

    public static List<TaskModel> ListDataModel=new ArrayList<>();
}
