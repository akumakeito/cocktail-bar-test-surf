package ru.akumakeito.cocktailbar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.akumakeito.cocktailbar.R
import ru.akumakeito.cocktailbar.databinding.FragmentAboutCocktailBinding
import ru.akumakeito.cocktailbar.ui.FragmentCocktailFeed.Companion.KEYID
import ru.akumakeito.cocktailbar.viewModel.ViewModel


@AndroidEntryPoint
class FragmentAboutCocktail : Fragment() {

    private val viewModel: ViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        val binding = FragmentAboutCocktailBinding.inflate(inflater, container, false)
        val cocktail = viewModel.getCurrentCocktail()

        if (cocktail != null) {

            binding.strCocktailName.text = cocktail.title

            val ingredientsAdapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, cocktail.ingredients)
            binding.listIngredients.adapter = ingredientsAdapter



            if (cocktail.description != null) {
                binding.strDescription.text = cocktail.description
            } else {
                binding.strDescription.visibility = View.GONE
            }


            if (cocktail.recipe != null) {
                binding.strRecipe.text = cocktail.recipe
            } else {
                binding.frameCocktailRecipe.visibility = View.GONE
            }




        }





        return binding.root

    }
}