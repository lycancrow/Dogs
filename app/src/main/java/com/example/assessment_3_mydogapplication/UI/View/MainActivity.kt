package com.example.assessment_3_mydogapplication.UI.View

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assessment_3_mydogapplication.Data.Network.Domain.Breed
import com.example.assessment_3_mydogapplication.Data.Repository.DogRepository
import com.example.assessment_3_mydogapplication.Keys.BundleKeys
import com.example.assessment_3_mydogapplication.R
import com.example.assessment_3_mydogapplication.UI.ViewModel.*
import com.example.assessment_3_mydogapplication.Utils.DogViewerApplication
import com.example.assessment_3_mydogapplication.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

//Name: Felipe Cuervo
//Student #: A00229760

class MainActivity : AppCompatActivity() {
    /*
    private val viewModel by viewModels<MainViewModel> {
        val apiService = (application as DogViewerApplication).serviceLocator.apiService
        val breedDatabase = (application as DogViewerApplication).serviceLocator.breedDatabase
        val repository = DogRepository(apiService, breedDatabase)
        MainViewModelFactory(repository)
    }
    */

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val background: ImageView = binding.background
        Glide.with(this)
            .load(R.drawable.background)
            .centerCrop()
            .into(background)


        binding.mainViewPager.adapter = MainViewPagerAdapter(this)
        //conecta el view pager con el tabLayout
        TabLayoutMediator(binding.mainTabLayout, binding.mainViewPager){tab, position ->
            tab.text = getString(MainTab.values()[position].titleRest)
        }.attach()

        /*
        binding.recyclerView.layoutManager = LinearLayoutManager(this)



        val adapter = BreedListAdapter(object: OnBreedClickListener{
            override fun onBreedClick(breed: Breed) {
                viewModel.onBreedClicked(breed)
            }

            override fun onFaveIconClick(breed: Breed) {
                viewModel.onFaveClicked(breed)
            }
        })




        binding.recyclerView.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }

        viewModel.breedList.observe(this) {
            it?.let {
                adapter.breeds = it
                adapter.notifyDataSetChanged()
            }
        }

        viewModel.navigateToDetails.observe(this) {
            it?.let {
                val intent = Intent(this, ImageActivity::class.java).apply {
                    putExtra(BundleKeys.BREED, it)
                }
                startActivity(intent)
                viewModel.onNavigateToDetailFinished()
            }
        }*/
    }
}