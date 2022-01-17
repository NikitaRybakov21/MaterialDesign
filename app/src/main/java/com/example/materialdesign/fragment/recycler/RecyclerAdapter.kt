package com.example.materialdesign.fragment.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesign.R

class RecyclerAdapter(private val data: ArrayList<Data>, private val fragment: RecyclerFragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType){
            TYPE_NOTES -> {
                val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_notes,parent,false)
                NewViewHolderNotes(layout)
            }
            else -> {
                val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_header,parent,false)
                NewViewHolderHeader(layout)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return when (getItemViewType(position)){
            TYPE_NOTES -> {
                val holderNotes = (holder as NewViewHolderNotes)

                holderNotes.text.text = data[position].text
                holderNotes.itemRemoved()
            }
            else -> {
                val holderHeader = (holder as NewViewHolderHeader)

                holderHeader.text.text = data[position].text
                holderHeader.addItemToPosition()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun generateItem() : Data{
        return Data("new notes", TYPE_NOTES)
    }

    inner class NewViewHolderNotes(view : View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.textView)
        private val close: ImageView = view.findViewById(R.id.close)

        fun itemRemoved(){
            close.setOnClickListener {
                data.removeAt(layoutPosition)
                notifyItemRemoved(layoutPosition)
            }
        }
    }

    inner class NewViewHolderHeader(view : View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.textView)
        private val add: ImageView = view.findViewById(R.id.addView)

        fun addItemToPosition(){
            add.setOnClickListener {
                data.add(layoutPosition + 1, generateItem())
                notifyItemInserted(layoutPosition + 1)
            }
        }
    }
}