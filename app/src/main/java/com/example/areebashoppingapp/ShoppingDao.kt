package com.example.areebashoppingapp
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingDao {

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insert(item : ShoppingItem)

@Delete
suspend fun Delete(item : ShoppingItem)

@Query("select * from items")
fun getAllItems() :LiveData<List<ShoppingItem>>
}