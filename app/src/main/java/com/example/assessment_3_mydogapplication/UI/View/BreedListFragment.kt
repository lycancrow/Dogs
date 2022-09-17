package com.example.assessment_3_mydogapplication.UI.View

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assessment_3_mydogapplication.Data.Network.Domain.Breed
import com.example.assessment_3_mydogapplication.Data.Repository.DogRepository
import com.example.assessment_3_mydogapplication.Keys.BundleKeys
import com.example.assessment_3_mydogapplication.UI.ViewModel.BreedListAdapter
import com.example.assessment_3_mydogapplication.UI.ViewModel.MainViewModel
import com.example.assessment_3_mydogapplication.UI.ViewModel.MainViewModelFactory
import com.example.assessment_3_mydogapplication.UI.ViewModel.OnBreedClickListener
import com.example.assessment_3_mydogapplication.Utils.DogViewerApplication
import com.example.assessment_3_mydogapplication.databinding.FragmentBreedListBinding

class BreedListFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel> {
        val apiService = (requireActivity().application as DogViewerApplication).serviceLocator.apiService
        val breedDatabase = (requireActivity().application as DogViewerApplication).serviceLocator.breedDatabase
        val repository = DogRepository(apiService, breedDatabase)
        MainViewModelFactory(repository, this, arguments)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentBreedListBinding.inflate(inflater,container,false)

        binding.recyclerView.layoutManager = LinearLayoutManager(activity)



        val adapter = BreedListAdapter(object: OnBreedClickListener {
            override fun onBreedClick(breed: Breed) {
                viewModel.onBreedClicked(breed)
            }

            override fun onFaveIconClick(breed: Breed) {
                viewModel.onFaveClicked(breed)
            }
        })




        binding.recyclerView.also {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = adapter
            it.itemAnimator = null
        }

        viewModel.breedList.observe(viewLifecycleOwner) {
            it?.let {
                adapter.breeds = it
                adapter.notifyDataSetChanged()
            }
        }

        viewModel.navigateToDetails.observe(viewLifecycleOwner) {
            it?.let {
                val intent = Intent(activity, ImageActivity::class.java).apply {
                    putExtra(BundleKeys.BREED, it)
                }
                startActivity(intent)
                viewModel.onNavigateToDetailFinished()
            }
        }

        return binding.root

    }


}