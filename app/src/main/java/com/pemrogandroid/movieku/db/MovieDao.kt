
package com.pemrogandroid.movieku.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.pemrogandroid.movieku.model.Movie

@Dao
interface MovieDao {

  @Query("SELECT * FROM movie_table")
  fun loadAll(): LiveData<List<Movie>>

  @Insert(onConflict = REPLACE)
  fun insert(movie: Movie)

  @Query("DELETE FROM movie_table WHERE id = :id")
  fun delete(id: Int?)

  @Query("DELETE FROM movie_table")
  fun deleteAll()

  @Update(onConflict = REPLACE)
  fun update(movie: Movie)

  @Query("SELECT * FROM movie_table WHERE id = :id")
  fun getMovie(id: Int): Movie

}