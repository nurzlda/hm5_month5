package com.example.lesson5_month5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson5_month5.databinding.ItemBoardBinding

class BoardAdapter(


    private val list: ArrayList<BoardModel>,
    private val listener: ItemClickListener):
    RecyclerView.Adapter<BoardAdapter.BoardViewHolder> (){



    inner class BoardViewHolder(private var binding: ItemBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(boardModel: BoardModel) {

            binding.tvNumber.text = boardModel.number.toString()
            binding.btnNext.text = boardModel.button

            binding.btnNext.setOnClickListener {
                if (adapterPosition == list.size -1) {
                    listener.openFragment()
                }else {
                    listener.btnNext()
                }
            }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        return BoardViewHolder(ItemBoardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false))

    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        return holder.onBind(list[position])

    }


    override fun getItemCount(): Int {
        return list.size
    }


}