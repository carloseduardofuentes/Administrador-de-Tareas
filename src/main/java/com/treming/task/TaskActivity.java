package com.treming.task;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.treming.task.Global.*;
import com.treming.task.Models.*;

import java.util.Calendar;

public class TaskActivity extends AppCompatActivity implements View.OnClickListener {
    private int TYPE_OPERATION=1;
    Button buttonSave;
    EditText editTextTask;
    EditText editTextDateInit;
    EditText editTextDateEnd;
    Spinner spinnerState;
    Spinner spinnerPriority;
    private static final String[] states={"To-Do", "Doing", "Done", "Seleccione el estado"};
    private static final String[] prioritys={"Alta", "Media", "Baja", "Seleccione la prioridad"};
    private Calendar calendar;
    private int mYear, mMonth, mDay;
    private DatePickerDialog datePickerDialog;
    private Intent intent;
    private TaskModel taskModel;
    private int indice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        buttonSave=(Button) findViewById(R.id.buttonSave);
        editTextTask=(EditText) findViewById(R.id.editTextTask);
        editTextDateInit=(EditText) findViewById(R.id.editTextDateInit);
        editTextDateEnd=(EditText) findViewById(R.id.editTextDateEnd);
        spinnerState=(Spinner) findViewById(R.id.spinnerState);
        spinnerPriority=(Spinner) findViewById(R.id.spinnerPriority);

        ArrayAdapter<String> ArrayAdapterStates=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, states){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view= super.getView(position, convertView, parent);
                if(position==getCount())
                {
                    ((TextView) view.findViewById(android.R.id.text1)).setText("");
                    ((TextView) view.findViewById(android.R.id.text1)).setHint(getItem(getCount()));
                }
                return  view;
            }

            @Override
            public int getCount() {
                return super.getCount()-1;
            }
        };

        ArrayAdapterStates.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinnerState.setAdapter(ArrayAdapterStates);
        spinnerState.setSelection(ArrayAdapterStates.getCount());

        ArrayAdapter<String> ArrayAdapterPriority=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, prioritys){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view= super.getView(position, convertView, parent);
                if(position==getCount())
                {
                    ((TextView) view.findViewById(android.R.id.text1)).setText("");
                    ((TextView) view.findViewById(android.R.id.text1)).setHint(getItem(getCount()));
                }
                return  view;
            }

            @Override
            public int getCount() {
                return super.getCount()-1;
            }
        };


        ArrayAdapterPriority.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinnerPriority.setAdapter(ArrayAdapterPriority);
        spinnerPriority.setSelection(ArrayAdapterPriority.getCount());

        calendar=Calendar.getInstance();
        mYear=calendar.get(Calendar.YEAR);
        mMonth=calendar.get(Calendar.MONTH);
        mDay=calendar.get(Calendar.DAY_OF_MONTH);

        editTextDateInit.setOnClickListener(this);
        editTextDateEnd.setOnClickListener(this);
        buttonSave.setOnClickListener(this);


        intent=getIntent();

        if(intent.getSerializableExtra("data")!=null){
            taskModel=(TaskModel) intent.getSerializableExtra("data");
            editTextTask.setText(taskModel.getTask());
            editTextDateInit.setText(taskModel.getDateInit());
            editTextDateEnd.setText(taskModel.getDateEnd());
            spinnerState.setSelection(ArrayAdapterStates.getPosition(taskModel.getState()));
            spinnerPriority.setSelection(ArrayAdapterPriority.getPosition(taskModel.getPriority()));
            TYPE_OPERATION=2;
        }

        if(intent.getSerializableExtra("indice")!=null) {
            indice = (Integer) intent.getSerializableExtra("indice");
        }
    }

    public void onClick(View view){
        if(view == editTextDateInit){
            datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    editTextDateInit.setText(day + "/" + (month + 1) + "/" + year);

                }
            }, mYear,mMonth,mDay);

            datePickerDialog.show();
        }
        else if(view == editTextDateEnd){
            datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    editTextDateEnd.setText(day + "/" + (month + 1) + "/" + year);

                }
            }, mYear,mMonth,mDay);

            datePickerDialog.show();
        }
        else if(view == buttonSave) {

            if(TYPE_OPERATION==1) {

                TaskModel taskModel = new TaskModel(editTextTask.getText().toString(), editTextDateInit.getText().toString(), editTextDateEnd.getText().toString(), spinnerState.getSelectedItem().toString(), spinnerPriority.getSelectedItem().toString());

                DataTask.listDataModel.add(taskModel);

                setResult(RESULT_OK);

                this.finish();
            }

            else {
                DataTask.listDataModel.get(indice).setTask(editTextTask.getText().toString());
                DataTask.listDataModel.get(indice).setDateInit(editTextDateInit.getText().toString());
                DataTask.listDataModel.get(indice).setDateEnd(editTextDateEnd.getText().toString());
                DataTask.listDataModel.get(indice).setState(spinnerState.getSelectedItem().toString());
                DataTask.listDataModel.get(indice).setPriority(spinnerPriority.getSelectedItem().toString());

                setResult(RESULT_OK);

                this.finish();
            }

        }
    }
}
