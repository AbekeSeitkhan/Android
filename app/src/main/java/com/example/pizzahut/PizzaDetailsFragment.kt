package com.example.pizzahut

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pizzahut.databinding.FragmentPizzaDetailsBinding

class PizzaDetailsFragment:BaseFragment<FragmentPizzaDetailsBinding>(FragmentPizzaDetailsBinding::inflate) {

    private val args : PizzaDetailsFragmentArgs by navArgs()
    override fun onBindView() {
        super.onBindView()
        with(binding){
            title.text = args.title
            mainImg.setImageResource(args.img)
            price.text = "Add to cart for "+args.price+" KZT"
            desc.text = args.desc
            size.text = args.size
            backBtn.setOnClickListener {
                findNavController().popBackStack()
            }
            price.setOnClickListener {
                Toast.makeText(requireContext(), "Pizza is added to your cart", Toast.LENGTH_SHORT).show()
            }
        }
    }
}