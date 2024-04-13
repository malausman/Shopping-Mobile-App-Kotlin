package com.example.areebashoppingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.areebashoppingapp.ShoppingRvAdapter.ShoppingItemClickInterface as ShoppingItemClickInterface1

class ShoppingRvAdapter (
    var list :List<ShoppingItem>,
    val shoppingItemClickInterface: ShoppingItemClickInterface
) : RecyclerView.Adapter<ShoppingRvAdapter.ShoppingViewHolder>()
{

    inner class ShoppingViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
      val nameTV = itemView.findViewById<TextView>(R.id.idTVItemName)
        val DescriptionTV = itemView.findViewById<TextView>(R.id.idTVDescription)
        val rateTV = itemView.findViewById<TextView>(R.id.idTVPrice)
        val amountTV = itemView.findViewById<TextView>(R.id.idTVTotalAmt)
        val deleteTV = itemView.findViewById<ImageView>(R.id.idTVDelete)

    }
   interface  ShoppingItemClickInterface {
        fun onItemClick(shoppingitem : ShoppingItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_rv_item,parent,false)
    return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
       holder.nameTV.text = list.get(position).ItemName
        holder.DescriptionTV.text = list.get(position).ItemDescription
        holder.rateTV.text = "Rs. "+list.get(position).ItemPrice.toString()

        //val itemTotal :Int = (list.get(position).ItemPrice)*(list.get(position).ItemQuantity)
        val itemTotal :Int = (list.get(position).ItemPrice)

        holder.amountTV.text = "Rs " +itemTotal.toString() // * list.get(position).ItemQuantity
        holder.deleteTV.setOnClickListener {

            //ShoppingItemClickInterface.onItemClick(0)
            shoppingItemClickInterface.onItemClick(list.get(position))

        }

        }


    override fun getItemCount(): Int {
        return list.size
    }
}


