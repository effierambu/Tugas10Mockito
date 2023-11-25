package com.pemrogandroid.movieku.ui.addmovie

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.pemrogandroid.movieku.databinding.ActivityAddMovieBinding

import com.pemrogandroid.movieku.model.Movie
import com.pemrogandroid.movieku.repository.LocalDataSource
import com.pemrogandroid.movieku.viewmodel.MoviesViewModel

import java.util.HashSet
import androidx.activity.viewModels
import com.pemrogandroid.movieku.ui.search.SearchActivity
import com.squareup.picasso.Picasso

open class AddMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddMovieBinding
    private val movieViewModel by viewModels<MoviesViewModel>()

    private lateinit var activitySearchLauncher: ActivityResultLauncher<Intent>
    val selectedMovies = Movie()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddMovie.setOnClickListener {
            onClickAddMovie()
        }

        activitySearchLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                this@AddMovieActivity.runOnUiThread {
                    selectedMovies.id = result.data?.getIntExtra(SearchActivity.EXTRA_ID, -1)
                    selectedMovies.title = result.data?.getStringExtra(SearchActivity.EXTRA_TITLE)
                    selectedMovies.releaseDate = result.data?.getStringExtra(SearchActivity.EXTRA_RELEASE_DATE)
                    selectedMovies.posterPath = result.data?.getStringExtra(SearchActivity.EXTRA_POSTER_PATH)

                    binding.movieTitle.setText(selectedMovies.title)
                    binding.movieReleaseDate.setText(selectedMovies.releaseDate)
                    binding.movieImageview.tag = selectedMovies.posterPath
//                    Picasso.get().load(RetrofitClient.TMDB_IMAGEURL + selectedMovies.posterPath)
//                        .into(binding.movieImageview)
                }
            }
        }

        binding.searchButton.setOnClickListener({
            goToSearchMovieActivity()
        })

    }

    //search onClick
    fun goToSearchMovieActivity() {
        val title = binding.movieTitle.text.toString()
        val intent = Intent(this@AddMovieActivity, SearchActivity::class.java)
        intent.putExtra(SearchActivity.SEARCH_QUERY, title)
        activitySearchLauncher.launch(intent)
    }

    //addMovie onClick
    fun onClickAddMovie() {
        if (selectedMovies.id == null) {
            Toast.makeText(this@AddMovieActivity, "Movie belum dipilih", Toast.LENGTH_LONG).show()
        } else {
            val movie = Movie(
                id = selectedMovies.id,
                title = selectedMovies.title,
                releaseDate = selectedMovies.releaseDate,
                posterPath = selectedMovies.posterPath
            )
            movieViewModel.addMovie(movie)

            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    companion object {

    }
}
