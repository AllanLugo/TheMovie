package com.example.themovie.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.themovie.R
import com.example.themovie.databinding.FragmentMainMenuBinding
import com.example.themovie.databinding.FragmentTheMovieDBBinding
import com.example.themovie.viewmodel.TheMovieDBViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TheMovieDBFragment : Fragment() {

    private val vieModel: TheMovieDBViewModel by viewModels()
    private var _binding: FragmentTheMovieDBBinding? = null
    private val binding get() = _binding!!
    private val navController by lazy {
        findNavController()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTheMovieDBBinding.inflate(inflater, container, false)

        vieModel.movieModel.observe(viewLifecycleOwner, Observer {
            binding.movieTittleTextView.text = it.original_title
            binding.releaseDateTextView.text = it.release_date
            binding.originalLanguageTextView.text = it.original_language
            binding.descriptionTextView.text = it.overview
            Picasso.get().load("https://image.tmdb.org/t/p/w500/"+it.name).into(binding.movieImage)
        })

        vieModel.OnCreate()


        return binding.root
    }
}
