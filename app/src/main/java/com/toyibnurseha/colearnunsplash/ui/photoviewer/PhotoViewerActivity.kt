package com.toyibnurseha.colearnunsplash.ui.photoviewer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.piasy.biv.indicator.progresspie.ProgressPieIndicator
import com.toyibnurseha.colearnunsplash.R
import com.toyibnurseha.colearnunsplash.databinding.ActivityPhotoViewerBinding

class PhotoViewerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotoViewerBinding

    companion object {
        const val PHOTO_URL = "photo_url"
        const val TITLE_PHOTO = "title_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra(TITLE_PHOTO)
        val imageUrl = intent.getStringExtra(PHOTO_URL)

        title?.let {
            binding.tvTitle.text = it
        }

        imageUrl?.let {
            binding.photoView.setProgressIndicator(ProgressPieIndicator())
            binding.photoView.showImage(Uri.parse(it))
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}