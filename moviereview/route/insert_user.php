<?php
if (! isset($_POST['username'])) {
    responJson(['success' => false, 'messege' => "'username' harus diisi"]);
    exit;
}
if (! isset($_POST['password'])) {
    responJson(['success' => false, 'messege' => "'password' harus diisi"]);
    exit;
}

//bersihkan data
$username = mysqli_real_escape_string($connection, $_POST['username']);
$password = mysqli_real_escape_string($connection, $_POST['password']);

//masukkan data ke db
$query = mysqli_query($connection, 'INSERT INTO users (username, password)
values ("'. $username .'", "'. $password .'")');
//cek berhasil atau tidak dimasukkan db
if ($query) {
    responJson(['success' => true, 'messege' => 'sukses memasukkan data']);
} else {
    responJson(['success' => false, 'messege' =>  mysqli_error($connection)]);
}
