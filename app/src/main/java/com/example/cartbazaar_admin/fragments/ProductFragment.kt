package com.example.cartbazaar_admin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.cartbazaar_admin.R
import com.example.cartbazaar_admin.adapter.AllProductAdapter
import com.example.cartbazaar_admin.databinding.FragmentProductBinding
import com.example.cartbazaar_admin.model.AllProductModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProductFragment : Fragment() {

    private lateinit var binding : FragmentProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentProductBinding.inflate(layoutInflater)

        getProducts()

        binding.floatingActionButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_productFragment_to_addProductFragment)
        }
        return binding.root
    }

    private fun getProducts() {
        val list = ArrayList<AllProductModel>()
        Firebase.firestore.collection("products")
            .get().addOnSuccessListener {
                list.clear()
                for (doc in it.documents) {
                    val data = doc.toObject(AllProductModel::class.java)
                    list.add(data!!)
                }
                binding.productRecyclerView.adapter = AllProductAdapter(requireContext(), list)
            }
    }

}