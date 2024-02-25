package com.example.pizzahut

import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizzahut.databinding.FragmentListOfPizzaBinding
import com.example.pizzahut.model.Pizza

class ListOfPizzaFragment:BaseFragment<FragmentListOfPizzaBinding>(FragmentListOfPizzaBinding::inflate) {
    override fun onBindView() {
        super.onBindView()
        val adapter = PizzaAdapter()
        binding.listPizza.adapter = adapter
        binding.listPizza.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter.submitList(getList())
        adapter.itemClick = {it ->
            findNavController().navigate(ListOfPizzaFragmentDirections.actionListOfPizzaFragmentToPizzaDetailsFragment(it.title, it.img, it.desc,it.price, it.size))
        }
        binding.editText.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                if(binding.editText.text!!.isNotEmpty()){
                    adapter.submitList(searchPizza(binding.editText.text.toString()))
                }
                else{
                    Toast.makeText(requireContext(), "Enter the pizza name", Toast.LENGTH_SHORT).show()
                }
            }

            false
        })
    }

    private fun getList():List<Pizza>{
        return listOf(
            Pizza("Melts Margarita", R.drawable.img_5, "Two juicy slices on our signature thin and crispy Thin N' Crispy® crust, loaded with cheese and more!", 2500, "combo", "Medium" ),
            Pizza("Cheese Lovers", R.drawable.img, "Mozarella, Parmezan, Cheddar", 2900, "pizza", "Medium" ),
            Pizza("Italian Delight", R.drawable.img_3, "Italian Sausage, Ham, Bell Peppers, Mushrooms, Pineapples", 2000, "pizza", "Small" ),
            Pizza("Bacon Lovers ", R.drawable.img_1, "Bacon, Bell Peppers, Mushrooms, Onions", 2500, "pizza", "Medium" ),
            Pizza("Combo #2", R.drawable.img_4, "Any medium Supreme Pizza, one cesadilia, and Pepsi 1l ", 4900, "combo", "Medium" ),
            Pizza("Veggie Lovers ", R.drawable.img_2, "Mushrooms, Pineapples, Bell Peppers, Onions", 3500, "pizza", "Large" ),
            Pizza("Supreme", R.drawable.img_3, "Beef, Pepperoni, Pork, Bell Peppers, Mushrooms, Onions", 2000, "pizza", "Small" ),
            Pizza("Hawaiian Supreme", R.drawable.img_3, "Ham, Pineapple", 2000, "pizza", "Small" ),
        )
    }
    private fun searchPizza(input: String):List<Pizza>{
        val pizzas = getList()
        return pizzas.filter{pizza->
            pizza.title.contains(input, ignoreCase = true)
        }
    }
}