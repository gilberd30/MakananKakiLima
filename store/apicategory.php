<?php
	include 'connection.php';

	$query = "SELECT * FROM tbl_category";
	$msql = mysqli_query($conn, $query);

	$jsonArray = array();

	$photo = "http://localhost/store/image/";

	while ($category = mysqli_fetch_assoc($msql)) {
		
		$rows['id'] = $category['id'];
		$rows['name'] = $category['name'];
		$rows['photo'] = $photo.$category['photo'];

		array_push($jsonArray, $rows);
	}

	echo json_encode($jsonArray, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
?>