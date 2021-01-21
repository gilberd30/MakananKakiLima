<?php
	include 'connection.php';

	$user = $_GET['user'];
	$email = $_GET['email'];
	$phone = $_GET['phone'];
	$password = $_GET['password'];

	$queryRegister = "SELECT * FROM tbl_register WHERE email = '".$email."'";

	$msql = mysqli_query($conn, $queryRegister);

	$result = mysqli_num_rows($msql);

	if (!empty($user) && !empty($email) && !empty($phone) && !empty($password)) {
		
		if ($result == 0) {
			
			$regis = "INSERT INTO tbl_register(user, email, phone, password) VALUES ('$user', '$email', '$phone', '$password')";
			$msqlRegis = mysqli_query($conn, $regis);

			echo "1";
		}else{
			echo "Email sudah di gunakan";
		}
	}else{
		echo "Semua data harus di isi lengkap";
	}
?>