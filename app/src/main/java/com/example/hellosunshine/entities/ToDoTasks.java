package com.example.hellosunshine.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_tasks", foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "user_id",
        childColumns = "user_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)})
public class ToDoTasks {

    @PrimaryKey (autoGenerate = true)
    int id = 0;

    @ColumnInfo (name = "task_name")
    String taskName = " ";

    @ColumnInfo (name = "status")
    boolean status = false;

    @ColumnInfo(name = "due_date")
    String dueDate = " ";

    @ColumnInfo(name = "user_id")
    int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
