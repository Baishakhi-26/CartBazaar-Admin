package com.example.cartbazaar_admin.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cartbazaar_admin.databinding.ImageItemBinding

class AddProductImageAdapter(val list: ArrayList<Uri>) :
    Adapter<AddProductImageAdapter.AddProductImageViewHolder>() {
    inner class AddProductImageViewHolder(val binding: ImageItemBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddProductImageViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddProductImageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AddProductImageViewHolder, position: Int) {
        holder.binding.itemImg.setImageURI(list[position])
    }
}