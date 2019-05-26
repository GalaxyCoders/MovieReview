<?php

function responJson($data)
{
  header('Content-Type: application/json');
  echo json_encode($data);
}

  $connection = mysqli_connect('localhost', 'root', '', 'db_movie');

  if (! $connection) {
    echo "Error connection to database";
    exit;
  }

  $action = $_GET['action'] ?? 'none';
  $file = 'route/' . $action . '.php';

  if (! file_exists($file)) {
    $file = 'route/non.php';
  }

  require_once $file;


?>
