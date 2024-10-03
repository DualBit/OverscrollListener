package com.dualbit.overscroll.sample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dualbit.overscroll.databinding.AdapterMainItemBinding

class MainAdapter(
    var elements: List<String>,
) : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainAdapter.MainHolder {
        return MainHolder(
            AdapterMainItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = elements.size

    override fun onBindViewHolder(holder: MainAdapter.MainHolder, position: Int) {
        holder.bind(elements[position])
    }

    inner class MainHolder(private val viewBinding: AdapterMainItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(item: String) {
            viewBinding.name.text = item
        }
    }
}
