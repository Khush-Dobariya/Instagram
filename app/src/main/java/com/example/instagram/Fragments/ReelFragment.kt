package com.example.instagram.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagram.Adapters.ReelAdapter
import com.example.instagram.Models.Reel
import com.example.instagram.Utils.REEL
import com.example.instagram.databinding.FragmentReelBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase


class ReelFragment : Fragment() {
    private lateinit var binding: FragmentReelBinding
    lateinit var adapter: ReelAdapter
    var reelsList = ArrayList<Reel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReelBinding.inflate(inflater, container, false)

        adapter = ReelAdapter(requireContext(), reelsList)
        binding.viewPager.adapter = adapter
        Firebase.firestore.collection(REEL).get().addOnSuccessListener {

            var tempList = ArrayList<Reel>()
            reelsList.clear()

            for (i in it.documents) {
                var reel = i.toObject<Reel>()!!
                tempList.add(reel)
            }
            reelsList.addAll(tempList)
            reelsList.reverse()
            adapter.notifyDataSetChanged()

        }


        return binding.root
    }

    companion object {

    }
}