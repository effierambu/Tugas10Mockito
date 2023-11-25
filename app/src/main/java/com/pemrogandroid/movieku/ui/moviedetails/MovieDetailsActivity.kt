package com.pemrogandroid.movieku.ui.moviedetails

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.pemrogandroid.movieku.databinding.ActivityMovieDetailsBinding
import com.pemrogandroid.movieku.model.Movie
import com.pemrogandroid.movieku.ui.mainmenu.MainActivity
import com.pemrogandroid.movieku.viewmodel.MoviesViewModel
import com.squareup.picasso.Picasso
import network.MoviesApi
import network.RetrofitClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieDetailsActivity : AppCompatActivity() {
    private val TAG = "MovieDetailsActivity"

    lateinit var binding: ActivityMovieDetailsBinding
    val selectedMovie = Movie()
    private val movieViewModel by viewModels<MoviesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectedMovie.id = intent.getIntExtra(MainActivity.EXTRA_ID, -1)
        getMovieDetails(selectedMovie.id!!)
        binding.btnUpdate.setOnClickListener {
            updateMovieDetails()
        }
    }

    fun updateMovieDetails() {
        selectedMovie.title = binding.titleTextview.text.toString()
        selectedMovie.releaseDate = binding.releaseDateTextview.text.toString()

        movieViewModel.updateDetails(selectedMovie)

        Toast.makeText(this@MovieDetailsActivity, "Movie berhasil diupdate.", Toast.LENGTH_LONG)
            .show()
    }

    fun getMovieDetails(id: Int) {

    }


    private fun createTmdbApiService(): MoviesApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(MoviesApi::class.java)


    }
}

