package com.example.themovie.view.fragment

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.themovie.databinding.FragmentMainMenuBinding
import com.example.themovie.viewmodel.MainMenuViewModel
import com.example.themovie.viewmodel.MapsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuFragment : Fragment() {

    private val vieModel: MainMenuViewModel by viewModels()
    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!
    private val navController by lazy {
        findNavController()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.theMovieDBButton.setOnClickListener { goToTheMovieDB() }
        binding.locationButton.setOnClickListener { goToLocation() }
        getPermision()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun goToTheMovieDB(){
        val action =
            MainMenuFragmentDirections.actionMainMenuFragmentToTheMovieDBFragment()
        navController.navigate(action)
    }

    fun goToLocation(){
        val action =
            MainMenuFragmentDirections.actionMainMenuFragmentToMapsFragment()
        navController.navigate(action)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getPermision(){
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    vieModel.onPermisionsGaranted()
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    vieModel.onPermisionsGaranted()
                } else -> {
                // No location access granted.
            }
            }
        }

        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION))
    }
}
