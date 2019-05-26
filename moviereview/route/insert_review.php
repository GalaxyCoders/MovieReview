<?php
if (! isset($_POST['review'])) {
    responJson(['success' => false, 'messege' => "'review' harus diisi"]);
    exit;
}
if (! isset($_POST['rating'])) {
    responJson(['success' => false, 'messege' => "'rating' harus diisi"]);
    exit;
}
if (! isset($_POST['id_user'])) {
    responJson(['success' => false, 'messege' => "'id_user' harus diisi"]);
    exit;
}
if (! isset($_POST['id_movie'])) {
    responJson(['success' => false, 'messege' => "'id_movie' harus diisi"]);
    exit;
}

//bersihkan data
$review = mysqli_real_escape_string($connection, $_POST['review']);
$rating = mysqli_real_escape_string($connection, $_POST['rating']);
$user_id = mysqli_real_escape_string($connection, $_POST['id_user']);
$movie_id = mysqli_real_escape_string($connection, $_POST['id_movie']);

//masukkan data ke db
$query = mysqli_query($connection, 'INSERT INTO user_review (review, rating, id_user, id_movie)
values ("'. $review .'", "'. $rating .'", "'. $user_id .'", "'. $movie_id .'")');
//cek berhasil atau tidak dimasukkan db
if ($query) {
    responJson(['success' => true, 'messege' => 'sukses memasukkan data']);
} else {
    responJson(['success' => false, 'messege' =>  mysqli_error($connection)]);
}
