<?php

// ambil data
  $query = mysqli_query($connection, "SELECT * FROM data_movie")
  or die(mysqli_error($connection));

//cek datanya ada atau tidak
//jika tidak
  if (mysqli_num_rows($query) === 0) {
    responJson (['data' => []]);
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
