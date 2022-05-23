package com.example.thiefdetector.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation


class HomeFragment : Fragment() {

    private lateinit var btn_warehouse: Button
    private lateinit var btn_instant_photo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =
            inflater.inflate(com.example.thiefdetector.R.layout.fragment_home, container, false)

        btn_warehouse = view.findViewById(com.example.thiefdetector.R.id.button_warehouse)
        btn_instant_photo = view.findViewById(com.example.thiefdetector.R.id.button_instant)
        btn_warehouse.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(com.example.thiefdetector.R.id.action_homeFragment_to_photoWarehouseFragment)
        }
        btn_instant_photo.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(com.example.thiefdetector.R.id.action_homeFragment_to_instantPhotoFragment)
        }

        // Inflate the layout for this fragment
        return view
    }


}