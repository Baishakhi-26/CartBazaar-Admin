package com.example.cartbazaar_admin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.cartbazaar_admin.databinding.AllOrdersItemLayoutBinding
import com.example.cartbazaar_admin.model.AllOrderModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AllOrderAdapter(val list: ArrayList<AllOrderModel>, val context: Context)
    :RecyclerView.Adapter<AllOrderAdapter.AllOrderViewHolder>( ){

    inner class AllOrderViewHolder(val binding: AllOrdersItemLayoutBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllOrderViewHolder {
        return AllOrderViewHolder(
            AllOrdersItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AllOrderViewHolder, position: Int) {
        holder.binding.productTitle.text = list[position].name
        holder.binding.productPrice.text = list[position].price
        holder.binding.allOrdersCancelButton.setOnClickListener {
            //holder.binding.allOrdersProceedButton.text = "Cancelled"

            holder.binding.allOrdersProceedButton.visibility = GONE


            updateStatus("Cancelled" , list[position].orderId!!)
        }

        when (list[position].status) {
            "Ordered" -> { holder.binding.allOrdersProceedButton.text = "Dispatched"
                holder.binding.allOrdersProceedButton.setOnClickListener {
                    updateStatus("Dispatched" , list[position].orderId!!)
                }
            }
            "Dispatched" -> { holder.binding.allOrdersProceedButton.text = "Delivered"
                holder.binding.allOrdersProceedButton.setOnClickListener {
                    updateStatus("Delivered" , list[position].orderId!!)
                }
            }
            "Delivered" -> {
                holder.binding.allOrdersCancelButton.visibility = GONE
                holder.binding.allOrdersProceedButton.isEnabled = false
                holder.binding.allOrdersProceedButton.text = "Already Delivered"
//                holder.binding.allOrdersCancelButton.setOnClickListener {
//                    updateStatus("Cancelled" , list[position].orderId!!)
//                }
               }
            "Cancelled" -> {
                holder.binding.allOrdersProceedButton.visibility = GONE
                holder.binding.allOrdersCancelButton.isEnabled = false
            }
        }
    }

    fun updateStatus(str : String, doc : String) {
        val data = hashMapOf<String, Any>();
        data["status"] = str
        Firebase.firestore.collection("allOrders")
            .document(doc).update(data).addOnSuccessListener {
                Toast.makeText(context, "Status Updated", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
    }


    override fun getItemCount(): Int {
        return list.size
    }

}