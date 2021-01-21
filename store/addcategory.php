<?php
	
	include 'connection.php';

	if (isset($_POST['submit'])) {

		$name = $_POST['name'];
		$photo_tmp = $_FILES['photo']['tmp_name'];
		$photo = $_FILES['photo']['name'];

		$query = "INSERT INTO tbl_category (name, photo) VALUES ('".$name."', '".$photo."')";

		move_uploaded_file($photo_tmp, "image/".$photo);
		mysqli_query($conn, $query);

		?>
			<script type="text/javascript">
				window.location = 'categoryview.php';
			</script>
		<?php

	}
?>
<!DOCTYPE html>
<html>
<head>
	<title>Tambah Category</title>
	<style>

	body{
		width: 50%;
		margin: 0 auto;
	}

	input[type=text], select {
	  width: 100%;
	  padding: 12px 20px;
	  margin: 8px 0;
	  display: inline-block;
	  border: 1px solid #ccc;
	  border-radius: 4px;
	  box-sizing: border-box;
	}

	input[type=submit] {
	  width: 100%;
	  background-color: #4CAF50;
	  color: white;
	  padding: 14px 20px;
	  margin: 8px 0;
	  border: none;
	  border-radius: 4px;
	  cursor: pointer;
	}

	input[type=submit]:hover {
	  background-color: #45a049;
	}

	div {
	  border-radius: 5px;
	  background-color: #f2f2f2;
	  padding: 20px;
	}
	</style>
</head>
<body>
	<form action="" method="post" enctype="multipart/form-data">
		<input type="text" name="name"></br>
		<input type="file" name="photo" accept="image/*"></br>
		<input type="submit" name="submit">
	</form>
</body>
</html>