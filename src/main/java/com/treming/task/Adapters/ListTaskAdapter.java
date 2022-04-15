package com.treming.task.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.treming.task.Models.TaskModel;
import com.treming.task.R;
import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Treming20 on 18/11/2017.
 */
public class ListTaskAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<TaskModel> listDataTask;

    public ListTaskAdapter(Context context, int layout, List<TaskModel> listDataTask) {
        this.context = context;
        this.layout = layout;
        this.listDataTask = listDataTask;
    }

    @Override
    public int getCount() {
        return listDataTask.size();
    }

    @Override
    public Object getItem(int i) {
        return listDataTask.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater= LayoutInflater.from(this.context);
        view=layoutInflater.inflate(layout, null);
        TextView textViewTask= (TextView) view.findViewById(R.id.textViewTask);
        TextView textViewDateInit= (TextView) view.findViewById(R.id.textViewDateInit);
        TextView textViewDateEnd= (TextView) view.findViewById(R.id.textViewDateEnd);
        TextView textViewState= (TextView) view.findViewById(R.id.textViewState);
        TextView textViewPriority= (TextView) view.findViewById(R.id.textViewPriority);

        textViewTask.setText( (CharSequence) this.listDataTask.get(i).getTask());
        textViewDateInit.setText((CharSequence) this.listDataTask.get(i).getDateInit());
        textViewDateEnd.setText((CharSequence) this.listDataTask.get(i).getDateEnd());
        textViewState.setText((CharSequence) this.listDataTask.get(i).getState());
        textViewPriority.setText((CharSequence) this.listDataTask.get(i).getPriority());

        return view;
    }
}
