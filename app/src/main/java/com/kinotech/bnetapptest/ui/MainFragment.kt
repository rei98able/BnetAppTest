package com.kinotech.bnetapptest.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kinotech.bnetapptest.R
import com.kinotech.bnetapptest.viewmodels.MainActivityViewModel
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.main_fragment, container, false)
        val loadEntry = root.findViewById<Button>(R.id.loadData)
        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerViewData)
        val addEntryFragment = AddEntryFragment()
        val viewModel: MainActivityViewModel =
            ViewModelProvider(this).get(MainActivityViewModel::class.java)
        lifecycleScope.launch {
            viewModel.generateSession()
            viewModel.getSession().observe(viewLifecycleOwner, {
                    session ->
                lifecycleScope.launch {
                    viewModel.readAllEntries(session.session)
                    viewModel.getEntries().observe(viewLifecycleOwner, {
                        entries ->
                        recyclerView.apply {
                            setHasFixedSize(true)
                            Toast.makeText(context, session.toString(), Toast.LENGTH_LONG).show()
                            layoutManager = LinearLayoutManager(context)
                            Log.d("count", "response is $entries")
                            adapter = EntriesAdapter(entries)
                        }
                    })
                }
            })
        }

        loadEntry.setOnClickListener {
            openFragment(addEntryFragment)
        }



        return root
    }

    private fun openFragment(fragment: Fragment){
        val activity: AppCompatActivity = context as AppCompatActivity
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}