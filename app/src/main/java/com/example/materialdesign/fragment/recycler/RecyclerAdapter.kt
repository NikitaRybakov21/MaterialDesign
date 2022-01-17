package com.example.materialdesign.fragment.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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

                holderNotes.editView.setOnClickListener {
                    fragment.callbackRecycler(holderNotes)
                }

                holderNotes.up.setOnClickListener   { holderNotes.moveUp()   }
                holderNotes.down.setOnClickListener { holderNotes.moveDown() }

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
        val editView: TextView = view.findViewById(R.id.editView)
        val up: ImageView = view.findViewById(R.id.up)
        val down: ImageView = view.findViewById(R.id.down)

        fun itemRemoved(){
            close.setOnClickListener {
                data.removeAt(layoutPosition)
                notifyItemRemoved(layoutPosition)
            }
        }

        fun editNotes(text: String){
            data.removeAt(layoutPosition)
            data.add(layoutPosition, Data(text, TYPE_NOTES))
            notifyItemChanged(layoutPosition)
        }

        fun moveUp() {
            if(layoutPosition == 1){
                data.removeAt(layoutPosition).apply {
                    data.add(data.size - 1, this)
                }
                notifyItemMoved(layoutPosition, data.size - 1)

            } else {
                data.removeAt(layoutPosition).apply {
                    data.add(layoutPosition - 1, this)
                }
                notifyItemMoved(layoutPosition, layoutPosition - 1)
            }
        }

        fun moveDown() {
            if(layoutPosition == data.size - 1) {
                data.removeAt(layoutPosition).apply {
                    data.add(1, this)
                }
                notifyItemMoved(layoutPosition, 1)
            } else {
                data.removeAt(layoutPosition).apply {
                    data.add(layoutPosition + 1, this)
                }
                notifyItemMoved(layoutPosition, layoutPosition + 1)
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