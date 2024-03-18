package com.example.hellosunshine.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "shopping_list_items", foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "user_id",
        childColumns = "user_id", onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE)})
public class ShoppingListItem {

    @PrimaryKey (autoGenerate = true)
    int id = 0;

    @ColumnInfo (name = "item_name")
    String itemName = " ";

    @ColumnInfo (name = "item_status")
    boolean itemStatus = false;

    @ColumnInfo (name = "date_due")
    String dateDue = " ";

    @ColumnInfo (name = "user_id")
    int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(boolean itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getDateDue() {
        return dateDue;
    }

    public void setDateDue(String dateDue) {
        this.dateDue = dateDue;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
