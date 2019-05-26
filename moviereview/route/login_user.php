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
$query = mysqli_query($connection, 'SELECT * FROM users WHERE username="'.$username.'" AND password = "'.$password.'"');
//cek berhasil atau tidak dimasukkan db
// if ($query) {
//     responJson(['success' => true, 'messege' => 'sukses memasukkan data']);
// } else {
//     responJson(['success' => false, 'messege' =>  mysqli_error($connection)]);
// }
if (mysqli_num_rows($query) === 0) {
  responJson (['data' => ['success' => false, 'messege' =>  mysqli_error($connection)]]);
} else {
  //jika ada
  $result = [];
  //tampilkan berulang
  while ($data = mysqli_fetch_assoc($query)) {
    array_push($result, $data);
  }
  responJson(['data' =>
  $result]);
}
   ?>
