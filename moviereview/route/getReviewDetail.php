<?php
//bersihkan data
$id = mysqli_real_escape_string($connection, $_GET['id']);

// ambil data
  $query = mysqli_query($connection, "SELECT user_review.*, DATE_FORMAT(user_review.created_at, '%a, %d %b %Y at %H:%i') AS formatted_date FROM user_review WHERE id_review='$id.' ")
  or die(mysqli_error($connection));

//cek datanya ada atau tidak
//jika tidak
  if (mysqli_num_rows($query) === 0) {
      responJson(['data' => []]);
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
