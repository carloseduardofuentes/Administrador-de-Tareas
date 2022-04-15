package com.treming.task;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;
import com.treming.task.Adapters.ListTaskAdapter;
import com.treming.task.Global.DataTask;
import com.treming.task.Models.TaskModel;

public class ListTaskActivity extends AppCompatActivity {
    static final int ADD_TASK = 1;
    static final int MODIFY_TASK = 2;
    ListView listViewTask;
    ListTaskAdapter listTaskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_task);

        listViewTask=(ListView) findViewById(R.id.listViewTask);

        listTaskAdapter=new ListTaskAdapter(this, R.layout.activity_data_task, DataTask.listDataModel);

        listViewTask.setAdapter(listTaskAdapter);

        registerForContextMenu(listViewTask);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater=getMenuInflater();

        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) menuInfo;

        menu.setHeaderTitle((CharSequence) DataTask.listDataModel.get(info.position).getTask());

        inflater.inflate(R.menu.context_list_data_task,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo inf=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        TaskModel taskModel=DataTask.listDataModel.get(inf.position);

        switch (item.getItemId()){
            case R.id.menuItemDelete:
                DataTask.listDataModel.remove(inf.position);
                this.listTaskAdapter.notifyDataSetChanged();
                return true;
            case R.id.menuItemEdit:
                Intent intent=new Intent(ListTaskActivity.this, TaskActivity.class);
                intent.putExtra("data", taskModel);
                intent.putExtra("indice", inf.position);
                startActivityForResult(intent,MODIFY_TASK);
                return true;

            default:
                return super.onContextItemSelected(item);
        }




    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuItemAdd:
                Intent intent=new Intent(ListTaskActivity.this, TaskActivity.class);
                startActivityForResult(intent,ADD_TASK);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch(requestCode){
            case ADD_TASK:
                if(resultCode==RESULT_OK){
                    listTaskAdapter.notifyDataSetChanged();
                    Toast.makeText(this, "Datos almacenados:  Datos: " + DataTask.listDataModel.size(),Toast.LENGTH_LONG).show();
                }

                break;
            case MODIFY_TASK:
                if(resultCode==RESULT_OK){
                    listTaskAdapter.notifyDataSetChanged();
                    Toast.makeText(this, "Dato Modificado exitosamente",Toast.LENGTH_LONG).show();
                }

                break;
            default:

        }
    }
}
