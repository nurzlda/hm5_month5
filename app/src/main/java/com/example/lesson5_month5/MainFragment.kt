package com.example.lesson5_month5

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lesson5_month5.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainFragment : Fragment(), ItemClickListener {

    private lateinit var binding: FragmentMainBinding

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




         sharedPreferences = requireActivity().getSharedPreferences("setting", Context.MODE_PRIVATE)
        val isShow : Boolean  = sharedPreferences.getBoolean( "Key1", false)
        if (isShow){
            findNavController().navigate(R.id.blankFragment)
        }
        changePreference()

        onBoard()

    }


    private fun onBoard(){
        val list = arrayListOf<BoardModel>()

        list.add(BoardModel(1, "next"))
        list.add(BoardModel(2, "next"))
        list.add(BoardModel(3, "next"))
        list.add(BoardModel(4, "open fragment"))

        val boardAdapter = BoardAdapter(list, this)
        binding.viewPager.adapter = boardAdapter
    }

    override fun openFragment() {
        findNavController().navigate(R.id.action_mainFragment_to_blankFragment)
    }

    override fun btnNext() {
        binding.viewPager.setCurrentItem(getItemOfViewPager(+1), true)
    }
    private fun getItemOfViewPager(i: Int): Int {
        return binding.viewPager.currentItem + i
    }

    private fun changePreference(){

        sharedPreferences= requireActivity().getSharedPreferences("setting", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("Key1", true).apply()

    }

}