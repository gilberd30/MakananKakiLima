<?php
	include 'connection.php';

	$email = $_GET['email'];
	$password = $_GET['password'];

	$cek = "SELECT * FROM tbl_register WHERE email = '$email' AND password = '$password'";
	$msql = mysqli_query($cek);
	$result = mysqli_num_rows($msql);

	if (!empty($email) && !empty($password)) {
		
		if ($result == 0) {
		
			echo "0";

		}else{

			echo "Selamat datang";
			
		}
	}else{
		echo "Semua harus di isi terlebih dahulu";
	}
?>