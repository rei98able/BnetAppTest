package com.kinotech.bnetapptest.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kinotech.bnetapptest.R
import com.kinotech.bnetapptest.models.Entry

class EntriesAdapter(
    private val entries: List<Entry>,
) : RecyclerView.Adapter<MoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.data_item, parent, false)
        Log.d("count", "in adapter")
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        return holder.bind(entries[position])
    }
}

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val entryDa: TextView = itemView.findViewById(R.id.daText)
    private val entryDm: TextView = itemView.findViewById(R.id.dmText)
    private val entryShortData: TextView = itemView.findViewById(R.id.shortData)
    val context: Context = itemView.context
    fun bind(entry: Entry) {
        Log.d("count", "near bind")
        entryDa.text = entry.da
        entryDm.text = entry.dm
        entryShortData.text = entry.body

        itemView.setOnClickListener {
//            val activity: AppCompatActivity = itemView.context as AppCompatActivity
//            val transaction = activity.supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.container, FilmPageFragment(movie, result, 1))
//            transaction.addToBackStack(null)
//            transaction.commit()
        }
    }
}
