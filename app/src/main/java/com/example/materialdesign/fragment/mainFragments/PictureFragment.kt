package com.example.materialdesign.fragment.mainFragments

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.materialdesign.R
import com.example.materialdesign.databinding.FragmentPictureBinding
import com.example.materialdesign.viewModel.AppState
import com.example.materialdesign.viewModel.PictureViewModel
import java.util.*

class PictureFragment : Fragment() {
    private var _binding: FragmentPictureBinding? = null
    private val binding: FragmentPictureBinding get() = _binding!!

    private val viewModel: PictureViewModel by lazy {
        ViewModelProvider(this).get(PictureViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPictureBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, Observer { it -> renderData(it) })

        viewModel.sendServer()
        searchWiki()
        chipPicture()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SimpleDateFormat")
    private fun chipPicture() = with(binding) {

        val c = Calendar.getInstance()

        val year = Integer.parseInt(SimpleDateFormat("yyyy").format(c.time))
        val moth = Integer.parseInt(SimpleDateFormat("MM").format(c.time))
        val day = Integer.parseInt(SimpleDateFormat("dd").format(c.time))

        chipGroup.check(R.id.chipToday)

        chipToday.setOnClickListener {
            chipGroup.clearCheck()
            chipGroup.check(R.id.chipToday)

            viewModel.getPODFromServer("$year-$moth-$day")
        }

        chipYesterday.setOnClickListener {
            chipGroup.clearCheck()
            chipGroup.check(R.id.chipYesterday)

            viewModel.getPODFromServer("$year-$moth-${day - 1}")
        }

        chipDayBeforeYesterday.setOnClickListener {
            chipGroup.clearCheck()
            chipGroup.check(R.id.chipDayBeforeYesterday)

            viewModel.getPODFromServer("$year-$moth-${day - 2}")
        }
    }

    private fun searchWiki() {
        binding.textInputLayout.setEndIconOnClickListener {
            val text = binding.inputEditText.text.toString()
            val uri = Uri.parse("https://ru.wikipedia.org/wiki/${text}")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    private fun renderData(state: AppState) {
        when (state) {
            is AppState.Error -> {
                binding.imageView.load(R.drawable.ic_load_error_vector)
            }
            is AppState.Loading -> {
                binding.imageView.load(R.drawable.ic_no_photo_vector)
            }
            is AppState.Success -> {
                val responseData = state.responseData
                val url = responseData.url
                binding.imageView.load(url) {}
            }
        }
    }

    companion object {
        fun newInstance() = PictureFragment()
    }
}