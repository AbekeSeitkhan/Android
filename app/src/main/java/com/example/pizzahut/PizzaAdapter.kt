package com.example.pizzahut

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.pizzahut.databinding.ItemNewPizzaBinding
import com.example.pizzahut.databinding.ItemPizzaBinding
import com.example.pizzahut.model.Pizza
import java.lang.IllegalArgumentException

class PizzaAdapter: ListAdapter<Pizza, BasePizzaViewHolder<*>>(PizzasDiffUtil()) {
    var itemClick: ((Pizza) -> Unit)? = null
    class PizzasDiffUtil: DiffUtil.ItemCallback<Pizza>(){
        override fun areContentsTheSame(oldItem: Pizza, newItem: Pizza): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areItemsTheSame(oldItem: Pizza, newItem: Pizza): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasePizzaViewHolder<*> {
        return when(viewType){
            VIEW_TYPE_PIZZA -> PizzaViewHolder(
                ItemPizzaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            VIEW_TYPE_COMBO -> PizzaComboViewHolder(
                ItemNewPizzaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalArgumentException("Invalid")
        }
    }

    override fun onBindViewHolder(holder: BasePizzaViewHolder<*>, position: Int) {
        holder.bindView(getItem(position))
    }
    inner class PizzaViewHolder(binding:ItemPizzaBinding):
        BasePizzaViewHolder<ItemPizzaBinding>(binding){
        override fun bindView(item: Pizza) {
            with(binding){
                title.text = item.title
                desc.text = item.desc
                price.text = item.price.toString()+" KZT"
                img.setImageResource(item.img)
            }
            itemView.setOnClickListener {
                itemClick?.invoke(item)
            }
        }
    }

    inner class PizzaComboViewHolder(binding: ItemNewPizzaBinding):
        BasePizzaViewHolder<ItemNewPizzaBinding>(binding){
        override fun bindView(item: Pizza) {
            with(binding){
                title.text = item.title
                content.text = item.desc
                img.setImageResource(item.img)
                price.text = item.price.toString()+" KZT"
            }
        }
    }
    companion object {
        private const val VIEW_TYPE_PIZZA = 1
        private const val VIEW_TYPE_COMBO = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).type){
            "pizza" -> VIEW_TYPE_PIZZA
            "combo" -> VIEW_TYPE_COMBO
            else -> throw IllegalArgumentException("Invalid")
        }
    }

}