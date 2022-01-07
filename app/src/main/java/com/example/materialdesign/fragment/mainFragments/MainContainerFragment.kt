package com.example.materialdesign.fragment.mainFragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.materialdesign.R
import com.example.materialdesign.databinding.MainContainerFragmentBinding
import com.example.materialdesign.fragment.coordinator.CoordinatorFragment
import com.example.materialdesign.fragment.slide.PlanetFragment

class MainContainerFragment : Fragment() {
    private var _binding: MainContainerFragmentBinding? = null
    private val binding: MainContainerFragmentBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = MainContainerFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomNavigationView()
        setFirstFragment()
    }

    private fun bottomNavigationView(){
        binding.bottomNavigationView.setOnItemSelectedListener {
            when  (it.itemId) {
                R.id.navigation_one -> {
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.mainContainer, PictureFragment.newInstance())
                        .addToBackStack("stack1")
                        .commit()
                }
                R.id.navigation_two -> {
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.mainContainer, PlanetFragment.newInstance())
                        .addToBackStack("stack1")
                        .commit()
                }
                R.id.navigation_three -> {
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.mainContainer, OptionsFragment.newInstance())
                        .addToBackStack("stack1")
                        .commit()
                }
                R.id.navigation_four -> {
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.mainContainer, CoordinatorFragment.newInstance())
                        .addToBackStack("stack1")
                        .commit()
                }
            }
            true
        }
    }

    private fun setFirstFragment(){
        binding.bottomNavigationView.selectedItemId = R.id.navigation_one
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.bottom_app_bar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> BottomNavigationFragment().show(requireActivity().supportFragmentManager, "")

            R.id.app_bar_settings -> {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainContainer, OptionsFragment.newInstance())
                    .addToBackStack("stack1")
                    .commit()
            }
            R.id.app_bar_fav -> Toast.makeText(requireContext(), "fav", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    /*private fun setBottomAppBar(view: View) {
        val context = activity as MainActivity
        context.setSupportActionBar( view.findViewById(R.id.bottomAppBar) )
        setHasOptionsMenu(true)
    }*/

    /* private fun bottomSheet() {
        val behavior = BottomSheetBehavior.from(binding.includeBottomSheet.bottomSheetContainer)

        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    /* BottomSheetBehavior.STATE_DRAGGING ->  TODO("not implemented")
                       BottomSheetBehavior.STATE_COLLAPSED -> TODO("not implemented")
                       BottomSheetBehavior.STATE_EXPANDED ->  TODO("not implemented")
                       BottomSheetBehavior.STATE_HALF_EXPANDED -> TODO("not implemented")
                       BottomSheetBehavior.STATE_HIDDEN ->    TODO("not implemented")
                       BottomSheetBehavior.STATE_SETTLING ->  TODO("not implemented")*/
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })
    }*/

    /* private fun setTextBottomSheet(){
        binding.includeBottomSheet.bottomSheetDescription.text = "text"
    } */

    companion object{
        fun newInstance() = MainContainerFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}