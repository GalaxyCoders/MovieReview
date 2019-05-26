<?php
if (! isset($_GET['id_review'])) {
  responJson(['messege' => "'id' harus diisi"]);
  exit;
}

$id = mysqli_real_escape_string($connection, $_GET['id_review']);

$query = mysqli_query($connection, "DELETE FROM user_review WHERE id_review=". $id);

if ($query) {
  responJson(['messege' => "sukses delete"]);
} else {
  responJson(['messege' => "error!!" . mysqli_error($connection)]);
}
 ?>
