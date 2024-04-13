package com.example.areebashoppingapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="items")
data class ShoppingItem (
    @ColumnInfo(name="ItemName")
    var ItemName :String,

    @ColumnInfo(name="ItemDescription")
    var ItemDescription :String,

    @ColumnInfo(name="ItemCategory")
    var ItemCategory :String,

    @ColumnInfo(name="ItemPrice")
    var ItemPrice :Int,
    @ColumnInfo(name="ItemIsBought")
    var ItemIsBought : Boolean
)
{
    @PrimaryKey(autoGenerate = true)
    var Id :Int? = null
}