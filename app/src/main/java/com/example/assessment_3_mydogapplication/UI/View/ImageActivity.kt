  package com.example.assessment_3_mydogapplication.UI.View

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.assessment_3_mydogapplication.Data.Repository.DogRepository
import com.example.assessment_3_mydogapplication.R
import com.example.assessment_3_mydogapplication.UI.ViewModel.ImageViewModel
import com.example.assessment_3_mydogapplication.UI.ViewModel.ImageViewModelFactory
import com.example.assessment_3_mydogapplication.Utils.DogViewerApplication
import com.example.assessment_3_mydogapplication.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity() {
    private val viewModel by viewModels<ImageViewModel> {
        val apiService = (application as DogViewerApplication).serviceLocator.apiService
        val breedDatabase = (application as DogViewerApplication).serviceLocator.breedDatabase
        val repository = DogRepository(apiService,breedDatabase)
        ImageViewModelFactory(repository, this, intent.extras)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val imageView = findViewById<ImageView>(R.id.dog_image_view)
        viewModel.imageUrl.observe(this) {
            it?.let {
                Glide.with(this).load(it).centerInside().into(imageView)
            }
        }

        viewModel.shareImage.observe(this) {
            it?.let {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, it)
                }
                startActivity(Intent.createChooser(intent, getString(R.string.share)))
                viewModel.onShareComplete()
            }
        }

        viewModel.browseImage.observe(this) {
            it?.let {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                try {
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {

                }
                viewModel.onBrowseComplete()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.image_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.browse -> {
                viewModel.onBrowseClick()
                true
            }
            R.id.share -> {
                viewModel.share()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}