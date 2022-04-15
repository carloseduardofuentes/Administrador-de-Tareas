package com.treming.task.Models;

import java.io.Serializable;

/**
 * Created by Treming20 on 18/11/2017.
 */
public class TaskModel implements Serializable {
    protected String task;
    protected String dateInit;
    protected String dateEnd;
    protected String state;
    protected String priority;

    public TaskModel(String task, String dateInit, String dateEnd, String state, String priority) {
        this.task = task;
        this.dateInit = dateInit;
        this.dateEnd = dateEnd;
        this.state = state;
        this.priority = priority;
    }

    public TaskModel(){
        this.task = "";
        this.dateInit = "";
        this.dateEnd = "";
        this.state = "";
        this.priority = "";
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDateInit() {
        return dateInit;
    }

    public void setDateInit(String dateInit) {
        this.dateInit = dateInit;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "TaskModel{" +
                "task='" + task + '\'' +
                ", dateInit='" + dateInit + '\'' +
                ", dateEnd='" + dateEnd + '\'' +
                ", state='" + state + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }
}
