<?php
if (! isset($_POST['review'])) {
    responJson(['success' => false, 'messege' => "'review' harus diisi"]);
    exit;
}
if (! isset($_POST['rating'])) {
    responJson(['success' => false, 'messege' => "'rating' harus diisi"]);
    exit;
}
if (! isset($_POST['id_review'])) {
    responJson(['success' => false, 'messege' => "'id_review' harus diisi"]);
    exit;
}
if (! isset($_POST['id_movie'])) {
    responJson(['success' => false, 'messege' => "'id_movie' harus diisi"]);
    exit;
}
if (! isset($_POST['id_user'])) {
    responJson(['success' => false, 'messege' => "'id_user' harus diisi"]);
    exit;
}

//bersihkan data
$review = mysqli_real_escape_string($connection, $_POST['review']);
$rating = mysqli_real_escape_string($connection, $_POST['rating']);
$id = mysqli_real_escape_string($connection, $_POST['id_review']);
$id_movie = mysqli_real_escape_string($connection, $_POST['id_movie']);
$id_user = mysqli_real_escape_string($connection, $_POST['id_user']);

//masukkan data ke db
$query = mysqli_query($connection, 'UPDATE user_review
  SET review = "'.$review.'", rating = "'.$rating.'", id_movie = "'.$id_movie.'", id_user = "'.$id_user.'"
  WHERE id_review = ' . $id);
//cek berhasil atau tidak dimasukkan db
if ($query) {
    responJson(['success' => true, 'messege' => 'sukses update data']);
} else {
    responJson(['success' => false, 'messege' =>  mysqli_error($connection)]);
}
