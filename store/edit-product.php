<?php
	
	include 'connection.php';

	if ($_GET['id']) {

		$id = $_GET['id'];

		$query = "SELECT * FROM tbl_product WHERE id = '".$id."'";

		$msql = mysqli_query($conn, $query);

		$row = mysqli_fetch_assoc($msql);

		if (isset($_POST['submit'])) {
			
			$name = $_POST['name'];
			$harga = $_POST['harga'];
			$deskripsi = $_POST['deskripsi'];
			$cat_id = $_POST['cat_product'];

			if ($_FILES['photo']['name']!="") {
				
				$name = $_POST['name'];
				$harga = $_POST['harga'];
				$deskripsi = $_POST['deskripsi'];
				$cat_id = $_POST['cat_product'];

				$photo_tmp = $_FILES['photo']['tmp_name'];
				$photo = $_FILES['photo']['name'];

				$queUpdate = "UPDATE tbl_product SET name = '".$name."', photo = '".$photo."', harga = '".$harga."', deskripsi = '".$deskripsi."', cat_id = '".$cat_id."' WHERE id = '".$id."'";

				if ($row['photo']!="") {
					unlink('image/'.$row['photo']);
				}

				move_uploaded_file($photo_tmp, "image/".$photo);
				mysqli_query($conn, $queUpdate);
				?>
					<script type="text/javascript">
						window.location = 'productview.php';
					</script>
				<?php

			}

			$queUpdateNoImage = "UPDATE tbl_product SET name = '".$name."', harga = '".$harga."', deskripsi = '".$deskripsi."', cat_id = '".$cat_id."' WHERE id = '".$id."'";
			mysqli_query($conn, $queUpdateNoImage);

			?>
				<script type="text/javascript">
					window.location = 'productview.php';
				</script>
			<?php


		}

		// $name = $_POST['name'];
		// $photo_tmp = $_FILES['photo']['tmp_name'];
		// $photo = $_FILES['photo']['name'];

		// $query = "INSERT INTO tbl_category (name, photo) VALUES ('".$name."', '".$photo."')";

		// move_uploaded_file($photo_tmp, "image/".$photo);
		// mysqli_query($conn, $query);

	}
?>
<!DOCTYPE html>
<html>
<head>
	<title>FORM</title>
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
		<input type="text" name="name" value="<?php echo $row['name']; ?>"></br>
		<input type="text" name="harga" value="<?php echo $row['harga']; ?>"></br>
		<input type="text" name="deskripsi" value="<?php echo $row['deskripsi']; ?>"></br>
		<?php 
			include 'connection.php';
			$queryCat = "SELECT * FROM tbl_category";
			$msqlCat = mysqli_query($conn, $queryCat);
			$msqlCat1 = mysqli_query($conn, $queryCat);
			$result = mysqli_fetch_assoc($msqlCat);

		 ?>
		<select name="cat_product" class="form-control">
			<?php while ($data = mysqli_fetch_assoc($msqlCat1)) {
				
				if ($data['id'] == $result['id']) { ?>
					<option selected="<?php echo $result['id']?>" value="<?php echo $data['id']?>">
						<?php echo $data['name']?>
					</option>
				<?php } else { ?>

					<option value="<?php echo $data['id']?>">
						<?php echo $data['name']?>
					</option>
				} 

			<?php } }?>
		</select>
		<input type="file" name="photo" accept="image/*"></br>
		<img src="http://localhost/store/image/<?php echo $row['photo'] ;?>" height=100 width=100>
		<input type="submit" name="submit">
	</form>
</body>
</html>