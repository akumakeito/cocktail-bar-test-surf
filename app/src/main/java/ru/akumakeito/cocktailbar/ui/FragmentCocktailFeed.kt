package ru.akumakeito.cocktailbar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.akumakeito.cocktailbar.R
import ru.akumakeito.cocktailbar.databinding.FeedCocktailBinding
import ru.akumakeito.cocktailbar.dto.Cocktail
import ru.akumakeito.cocktailbar.viewModel.ViewModel


@AndroidEntryPoint
class FragmentCocktailFeed : Fragment() {

    companion object {
        val KEYID = "id"
    }

    private val viewModel: ViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = FeedCocktailBinding.inflate(inflater, container, false)

        val adapter = CocktailAdapter(object : OnInteractionListener {
            override fun onShow(cocktail: Cocktail) {
               viewModel.edit(cocktail)
                findNavController().navigate(R.id.action_fragmentCocktailFeed_to_fragmentAboutCocktail)
            }
        })

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentCocktailFeed_to_fragmentNewCocktail)
        }

        binding.listCocktail.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner, {cocktail : List<Cocktail> ->
            adapter.submitList(cocktail)
        })

        return binding.root
    }


}