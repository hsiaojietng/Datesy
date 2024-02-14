package com.example.datesy

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Holds the logic for each DateIdea
class DateIdeaAdapter(private val dateIdeas: MutableList<DateIdea>) : RecyclerView.Adapter<DateIdeaAdapter.DateIdeaViewHolder> () {
    class DateIdeaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateIdeaViewHolder {
        return DateIdeaViewHolder(
            // Inflates the layout view to be used
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_dateidea,
                parent,
                false
            )
        )
    }

    fun addDateIdea(dateIdea: DateIdea) {
        // Adds new date idea and notify adapter new item is inserted to refresh the recyclerview
        dateIdeas.add(dateIdea)
        notifyItemInserted(dateIdeas.size - 1)
    }

    fun deleteCompletedDateIdeas() {
        // Remove all the ideas that has been checked
        dateIdeas.removeAll { dateIdea ->
            dateIdea.isChecked
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dateIdeas.size
    }

    override fun onBindViewHolder(holder: DateIdeaViewHolder, position: Int) {
        var currDateIdea = dateIdeas[position]
        holder.itemView.apply {
            val tvDateIdea = findViewById<TextView>(R.id.rvDateIdeas)
            val cbComplete = findViewById<CheckBox>(R.id.cbComplete)
            tvDateIdea.text = currDateIdea.title
            cbComplete.isChecked = currDateIdea.isChecked
            toggleStrikeThrough(tvDateIdea, currDateIdea.isChecked)
            cbComplete.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvDateIdea, isChecked)
                currDateIdea.isChecked = !currDateIdea.isChecked}
        }
    }

    private fun toggleStrikeThrough(tvDateIdeaTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvDateIdeaTitle.paintFlags = tvDateIdeaTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvDateIdeaTitle.paintFlags = tvDateIdeaTitle.paintFlags or STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}