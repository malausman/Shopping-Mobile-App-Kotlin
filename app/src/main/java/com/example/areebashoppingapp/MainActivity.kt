package com.example.areebashoppingapp

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.ArrayAdapter
import android.widget.AdapterView

import android.widget.AdapterView.OnItemSelectedListener







class MainActivity : AppCompatActivity() , ShoppingRvAdapter.ShoppingItemClickInterface{

    lateinit var itemsRV :RecyclerView
    lateinit var addFAB :FloatingActionButton
lateinit var list : List<ShoppingItem>
lateinit var shoppingRvAdapter: ShoppingRvAdapter
lateinit var shoppingViewModel : ShoopingViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemsRV = findViewById(R.id.idRVItems)
        addFAB = findViewById(R.id.idFABAdd)
        list = ArrayList<ShoppingItem>()
        shoppingRvAdapter = ShoppingRvAdapter(list,this )
itemsRV.layoutManager =  LinearLayoutManager(this)
   itemsRV.adapter = shoppingRvAdapter
       val shoppingRepository = ShoppingRepository(ShoppingDatabase(this))
val factory = ShoppingViewModelFactory(shoppingRepository)
        shoppingViewModel = ViewModelProvider(this,factory).get(ShoopingViewModel::class.java)
shoppingViewModel.getAllItems().observe(
    this, Observer {

        shoppingRvAdapter.list = it
        shoppingRvAdapter.notifyDataSetChanged()
    }
)
        addFAB.setOnClickListener {
             openDialog()
        }



    }

    private fun openDialog() {

        val dialog  = Dialog(this)
        dialog.setContentView(R.layout.shopping_add_dialog)

        val items = arrayOf("Food", "Electronic", "Clothing")
        var spinnercat = dialog.findViewById<Spinner>(R.id.category_spinner)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, items
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnercat.prompt = "Select your Category!";
        spinnercat.adapter = adapter




        val cancelBtn = dialog.findViewById<Button>(R.id.idBtnCancel)
        val addBtn = dialog.findViewById<Button>(R.id.idBtnADDItems)
        val itemEdit = dialog.findViewById<EditText>(R.id.idEditItemName)
        val itemDescriptionEdit = dialog.findViewById<EditText>(R.id.idEditItemDescription)
        val itemPriceEdit = dialog.findViewById<EditText>(R.id.idEditItemPrice)
        var spinnercattext: String =""

       // val itemQuantityEdit = dialog.findViewById<EditText>(R.id.idEditItemQuantity)

        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        addBtn.setOnClickListener {



            //val category_id = ddlCategory.selectedItemPosition.
            val itemName :String = itemEdit.text.toString()

            spinnercat.setOnItemSelectedListener(object : OnItemSelectedListener {
                override fun onItemSelected(
                    arg0: AdapterView<*>?,
                    arg1: View?,
                    position: Int,
                    id: Long
                ) {
                    //items[0] = "One"
                    var selectedItem = items[position]
                    spinnercattext= spinnercat.getSelectedItem().toString()
                }

                override fun onNothingSelected(arg0: AdapterView<*>?) {}
            })

            spinnercattext= spinnercat.getSelectedItem().toString()

            val itemDescription :String = itemDescriptionEdit.text.toString()
            val itemPrice :String = itemPriceEdit.text.toString()
            val pr: Int = itemPrice.toInt()
            val isBought = false
            if(itemName.isNotEmpty() && itemDescription.isNotEmpty() &&
                    itemPrice.isNotEmpty())
            {

                val items = ShoppingItem(itemName,itemDescription,spinnercattext,pr, isBought)
                shoppingViewModel.insert(items)
                Toast.makeText(applicationContext,"Item Inserted..",Toast.LENGTH_SHORT).show()
                shoppingRvAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            else {
                Toast.makeText(applicationContext,"Please enter all details..",Toast.LENGTH_SHORT).show()
            }

        }

        dialog.show()
    }

    override fun onItemClick(shoppingitem: ShoppingItem) {
        shoppingViewModel.delete(shoppingitem)
        shoppingRvAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext, "Item Deleted" , Toast.LENGTH_SHORT).show()
    }
}