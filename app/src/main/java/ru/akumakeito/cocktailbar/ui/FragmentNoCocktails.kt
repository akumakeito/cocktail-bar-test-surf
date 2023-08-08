package ru.akumakeito.cocktailbar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.akumakeito.cocktailbar.R
import ru.akumakeito.cocktailbar.databinding.FragmentNoCocktailBinding


@AndroidEntryPoint
class FragmentNoCocktails : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        val binding = FragmentNoCocktailBinding.inflate(inflater,container,false)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentNoCocktails2_to_fragmentNewCocktail)
        }

        return binding.root

    }
}