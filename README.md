[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/J4jjBdeQ)
# UG10-AndroidNetworkComunication
Lengkapilah aplikasi MovieKu dengan fitur tambahan untuk melakukan pencarian Movie yang akan dibookmark saat akan melakukan tambah Movie dan akses detail Movie ke TMDB, dengan ketentuan sebagai berikut:  
1. Pencarian data Movie dilakukan dengan mengkases data dari TMDB API. Endpoint untuk pencarian adalah : https://api.themoviedb.org/3/search/movie. 
2. Detail Movie akan mengakses data dari TMDB yang ada di Endpoint https://api.themoviedb.org/3/movie/{movie_id}. Detail movie akan dapat diubah, namun perubahan akan disimpan pada LocalDatabase. Data yang dapat dirubah adalah Title Text dan release date,
dan perubahan juga ditampilkan pada detail movie.
4. Daftar Movie yang ditampilkan pada MainMenu adalah daftar Movie yang dibookmark dan tersimpan pada LocalDatabase.
5. Gambar Movie diakses dari TMDB melalui endpoint https://image.tmdb.org/t/p/w500/{poster_path} dan ditampilkan dengan Picasso
6. Menggunakan Retrofit sebagai library untuk mengkases API TMDB. Gunakan dependency berikut pada project Anda :
```
  //Retrofit
  implementation 'com.squareup.retrofit2:retrofit:2.9.0'
  implementation 'com.squareup.retrofit2:converter-gson:2.1.0'
```
7. Menggungakan Room sebagai basis data lokal
8. Menggunakan Live Data
9. Menggunakan ViewModel

NB :
Dokumentasi TMDB dapat dilihat di 
- search : https://developer.themoviedb.org/reference/search-movie
- movie details : https://developer.themoviedb.org/reference/movie-details
- Anda dapat melihat hasil akhir dengan menginstall sample-app.apk pada smartphone Anda.
