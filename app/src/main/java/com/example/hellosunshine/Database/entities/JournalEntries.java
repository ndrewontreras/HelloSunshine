package com.example.hellosunshine.Database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "journal_entries", foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "user_id",
        childColumns = "user_id", onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE)})
public class JournalEntries {

    @PrimaryKey (autoGenerate = true)
    int id = 0;

    @ColumnInfo(name = "entry")
    String entry = " ";

    @ColumnInfo(name = "date")
    String Date = " ";

    @ColumnInfo(name = "user_id")
    int userId;

    public int getId() {        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
