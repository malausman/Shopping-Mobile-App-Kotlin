package com.example.areebashoppingapp

class ShoppingRepository(private val db: ShoppingDatabase) {
    suspend fun insert(items: ShoppingItem) = db.getItemsDao().insert(items)
    suspend fun delete(items: ShoppingItem) = db.getItemsDao().Delete(items)

     fun getAllItems() = db.getItemsDao().getAllItems()

}