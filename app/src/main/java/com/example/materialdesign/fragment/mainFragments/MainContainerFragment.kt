package com.example.materialdesign.fragment.mainFragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.materialdesign.R
import com.example.materialdesign.databinding.FragmentMainContainerBinding
import com.example.materialdesign.fragment.animation.AnimationsFragment
import com.example.materialdesign.fragment.coordinator.CoordinatorFragment
import com.example.materialdesign.fragment.recycler.RecyclerFragment
import com.example.materialdesign.fragment.slide.PlanetFragment

class MainContainerFragment : Fragment() {
    private var _binding: FragmentMainContainerBinding? = null
    private val binding: FragmentMainContainerBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomNavigationView()
        setFirstFragment()
        toolbarMenu()
    }

    private fun bottomNavigationView(){
        binding.bottomNavigationView.setOnItemSelectedListener {
            when  (it.itemId) {
                R.id.navigation_picture ->      { setFragmentToMainContainer(PictureFragment.newInstance())     }
                R.id.navigation_planet ->       { setFragmentToMainContainer(PlanetFragment.newInstance())      }
                R.id.recycler ->                { setFragmentToMainContainer(RecyclerFragment.newInstance())    }
                R.id.navigation_coordinator ->  { setFragmentToMainContainer(CoordinatorFragment.newInstance()) }
                R.id.navigation_home ->         { setFragmentToMainContainer(AnimationsFragment.newInstance())  }
            }
            true
        }
    }

    private fun toolbarMenu(){
        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.setting){
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.anim_layout_2,R.anim.anim_layout)
                    .replace(R.id.mainContainer, OptionsFragment.newInstance())
                    .addToBackStack("stack")
                    .commit()

                binding.back.visibility = View.VISIBLE
            }
            true
        }

        binding.back.setOnClickListener {
            binding.back.visibility = View.GONE

            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun setFragmentToMainContainer(fragment: Fragment){
        requireActivity().supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.anim_layout_2,R.anim.anim_layout)
            .replace(R.id.mainContainer, fragment)
            .commit()
    }

    private fun setFirstFragment(){
        binding.back.visibility = View.GONE

        binding.bottomNavigationView.selectedItemId = R.id.navigation_home
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

    companion object{
        fun newInstance() = MainContainerFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}