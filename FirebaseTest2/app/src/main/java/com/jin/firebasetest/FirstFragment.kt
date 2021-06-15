package com.jin.firebasetest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    val firebaseManager = FirebaseManager()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_add_docs)
            .setOnClickListener {
            firebaseManager.addRandomErrorData()
        }

        view.findViewById<Button>(R.id.btn_update_docs)
            .setOnClickListener {
                firebaseManager.updateDocuments()
            }

        view.findViewById<Button>(R.id.btn_counter_added) .setOnClickListener {
            firebaseManager.counterAdded()
        }

        view.findViewById<Button>(R.id.btn_query)
            .setOnClickListener {
                firebaseManager.querySampleDoc()
            }
    }
}