<?php

// ambil data
  $query = mysqli_query($connection, "SELECT
    user_review.*,
    data_movie.*,
    users.username,
    DATE_FORMAT(user_review.created_at, '%a, %d %b %Y at %H:%i') AS formatted_date
FROM
    data_movie
    INNER JOIN user_review ON data_movie.id = user_review.id_movie
    INNER JOIN users ON user_review.id_user = users.id
order by created_at desc ")
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
