package com.example.materialdesign.fragment.recycler

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesign.databinding.FragmentRecyclerBinding
import java.text.FieldPosition

class RecyclerFragment : Fragment() {
    private var _binding : FragmentRecyclerBinding? = null
    private val  binding : FragmentRecyclerBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRecyclerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecycler()
    }

    private fun setRecycler() = with(binding){
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)

        val data = ArrayList<Data>()
        data.add(Data("#Важное", TYPE_HEADER))
        data.add(Data("notes", TYPE_NOTES))
        data.add(Data("notes", TYPE_NOTES))
        data.add(Data("notes", TYPE_NOTES))
        data.add(Data("#Телефоны", TYPE_HEADER))
        data.add(Data("notes", TYPE_NOTES))
        data.add(Data("notes", TYPE_NOTES))
        data.add(Data("notes", TYPE_NOTES))
        data.add(Data("#Другое", TYPE_HEADER))
        data.add(Data("notes", TYPE_NOTES))
        data.add(Data("notes", TYPE_NOTES))
        data.add(Data("notes", TYPE_NOTES))


        recyclerView.adapter = RecyclerAdapter(data,this@RecyclerFragment)
    }

    fun callbackRecycler(holder: RecyclerAdapter.NewViewHolderNotes) {
        showCreateNotes()

        binding.buttonApply.setOnClickListener {
            val text = binding.editText.editableText.toString()
            hideCreateNotes()
            holder.editNotes(text)
        }
    }

    private fun showCreateNotes(){
        ObjectAnimator.ofFloat(binding.containerCreateNotes,View.TRANSLATION_Y,-500f).start()
    }

    private fun hideCreateNotes(){
        ObjectAnimator.ofFloat(binding.containerCreateNotes,View.TRANSLATION_Y,500f).start()
    }

    companion object {
        fun newInstance() = RecyclerFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}