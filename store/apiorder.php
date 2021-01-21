<?php
	include 'connection.php';

	$query = "SELECT * FROM `tbl_order`";
	$msql = mysqli_query($conn, $query);

	$jsonArray = array();

	$photo = "http://localhost/store/image/";

	while ($category = mysqli_fetch_assoc($msql)) {
		
		$rows['id'] = $category['id'];
		$rows['id_user'] = $category['id_user'];
		$rows['jumlah'] = $category['jumlah'];
		$rows['catatan'] = $category['catatan'];
		$rows['nama_product'] = $category['nama_product'];

		array_push($jsonArray, $rows);
	}

	echo json_encode($jsonArray, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
?>