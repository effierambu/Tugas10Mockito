package com.pemrogandroid.movieku.ui.mainmenu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.pemrogandroid.movieku.model.Movie
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pemrogandroid.movieku.R
import com.pemrogandroid.movieku.databinding.ActivityMainBinding
import com.pemrogandroid.movieku.repository.LocalDataSource
import com.pemrogandroid.movieku.ui.addmovie.AddMovieActivity
import com.pemrogandroid.movieku.ui.moviedetails.MovieDetailsActivity
import com.pemrogandroid.movieku.viewmodel.MoviesViewModel

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    lateinit var binding: ActivityMainBinding
    private var adapter: MainAdapter? = null
    private val movieViewModel by viewModels<MoviesViewModel>()
    private lateinit var activityAddMovieResultLauncher: ActivityResultLauncher<Intent>

    companion object {
        val EXTRA_ID = "DetailsActivity.MOVIE_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getMyMoviesList()

        binding.moviesRecyclerview.layoutManager = LinearLayoutManager(this)
        supportActionBar?.title = "Movies Tontonanku"

        activityAddMovieResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this@MainActivity, "Movie berhasil ditambahkan.", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@MainActivity, "Movie gagal ditambahkan.", Toast.LENGTH_LONG).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    private fun getMyMoviesList() {
        movieViewModel.getMovies()?.observe(
            this, {
                it?.let {
                    displayMovies(it)
                }
            }
        )
    }

    fun displayMovies(movieList: List<Movie>?) {
        if (movieList == null || movieList.size == 0) {
            Log.d(TAG, "No movies to display")
            binding.moviesRecyclerview.visibility = INVISIBLE
            binding.noMoviesLayout.visibility = VISIBLE
        } else {
            adapter = MainAdapter(movieList, this@MainActivity, itemListener)
            binding.moviesRecyclerview.adapter = adapter

            binding.moviesRecyclerview.visibility = VISIBLE
            binding.noMoviesLayout.visibility = INVISIBLE
        }
    }

    //fab onClick
    fun goToAddMovieActivity(v: View) {
        val myIntent = Intent(this@MainActivity, AddMovieActivity::class.java)
        activityAddMovieResultLauncher.launch(myIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.deleteMenuItem) {
            val adapter = this.adapter
            if (adapter != null) {
                for (movie in adapter.selectedMovies) {
                    movieViewModel.delete(movie)
                }
                if (adapter.selectedMovies.size == 1) {
                    Toast.makeText(this@MainActivity, "Movie berhasil dihapus", Toast.LENGTH_LONG).show()
                } else if (adapter.selectedMovies.size > 1) {
                    Toast.makeText(this@MainActivity, "Beberapa movie berhasil dihapus", Toast.LENGTH_LONG).show()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    internal var itemListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemClick(v: View, position: Int) {
            val movie = adapter!!.getItemAtPosition(position)
            val intent = Intent(this@MainActivity, MovieDetailsActivity::class.java)
            intent.putExtra(EXTRA_ID, movie.id)
            startActivity(intent)
            Log.i(TAG, "RecyclerItemListener onItemClick: " + movie.id + " " + movie.title)
        }
    }

    interface RecyclerItemListener {
        fun onItemClick(v: View, position: Int)
    }

}