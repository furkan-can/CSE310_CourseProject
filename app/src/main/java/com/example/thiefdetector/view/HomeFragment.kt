package com.example.thiefdetector.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

/**
 * @author Hümeyra Polat
 * @since 24.06.2022
 */


class HomeFragment : Fragment() {


    private lateinit var btn_warehouse: Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =
            inflater.inflate(com.example.thiefdetector.R.layout.fragment_home, container, false)

        btn_warehouse = view.findViewById(com.example.thiefdetector.R.id.button_warehouse)
        btn_warehouse.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(com.example.thiefdetector.R.id.action_homeFragment_to_photoWarehouseFragment)
        }


        // Inflate the layout for this fragment
        return view
    }






}