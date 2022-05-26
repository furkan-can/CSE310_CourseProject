package com.example.thiefdetector.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thiefdetector.R
import com.example.thiefdetector.adapter.ThiefDetectorAdapter
import com.example.thiefdetector.databinding.FragmentPhotoWarehouseBinding
import com.example.thiefdetector.model.Photos
import com.google.firebase.database.*

/**
 * @author Hümeyra Köseoğlu
 * @since 24.06.2022
 */

class PhotoWarehouseFragment : Fragment() {

    private var _binding: FragmentPhotoWarehouseBinding? = null
    private val binding get() = _binding!!


    private lateinit var dbRef: DatabaseReference
    private lateinit var photoArrayList: ArrayList<Photos>
    private lateinit var photoRecyclerview: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding  = FragmentPhotoWarehouseBinding.inflate(layoutInflater)

        /*val photo_recycler = binding.photosRecycler
        photo_recycler.layoutManager = LinearLayoutManager(context)
        photo_recycler.setHasFixedSize(true)
        photoRecyclerview.adapter = ThiefDetectorAdapter(photoArrayList)
        photoArrayList = arrayListOf<Photos>()
*/
        photoRecyclerview = binding.photosRecycler
        photoRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        photoRecyclerview.setHasFixedSize(true)
        photoRecyclerview.adapter?.notifyDataSetChanged()
        photoArrayList = arrayListOf<Photos>()


        getDataFromFirebase()
        // Inflate the layout for this fragment
        return binding.root
    }

    fun getDataFromFirebase() {

        dbRef = FirebaseDatabase.getInstance().getReference("records")

        dbRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {

                    photoArrayList.clear()
                    for (userSnapshot in snapshot.children) {


                        val user = userSnapshot.getValue(Photos::class.java)
                        photoArrayList.add(user!!)

                    }

                    photoRecyclerview.adapter = ThiefDetectorAdapter(photoArrayList)



                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }
}
