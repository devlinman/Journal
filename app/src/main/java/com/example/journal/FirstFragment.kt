package com.example.journal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.journal.databinding.FragmentFirstBinding
//
import com.example.journal.adapter.ItemAdapter
import com.example.journal.data.Datasource
import com.example.journal.task_model.RecyclerItemClickListener
import com.google.android.material.snackbar.Snackbar

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myDataset = Datasource().loadAffirmations()

        val recyclerView = binding.recyclerView
        recyclerView.adapter = ItemAdapter(requireContext(), myDataset)
        recyclerView.setHasFixedSize(true)

        // Add a click listener to the RecyclerView items to display a Snackbar
        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(
                requireContext(),
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        val affirmation = myDataset[position]
                        val message = "You clicked on: ${affirmation.imageResourceId}"
                        displaySnackbar(view, message)
                    }
                })
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Function to display a Snackbar message
    private fun displaySnackbar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }}