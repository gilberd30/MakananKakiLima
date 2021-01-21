<!DOCTYPE html>
<html>
<head>
	<title>VIEW</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css">
	<script src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
	<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.css">	
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
	<script type="text/javascript">
		$(document).ready(function(){
			$('#saya').DataTable();
		});
	</script>
</head>
<body>
	<div class="container">
		<div class="row">
		<div class="col-lg-12">
			<div class="panel-body">
				<?php include 'menu.php';?>
				<div class="table-responsive">
					<?php
						include 'connection.php';
						if (isset($_GET['id_del'])) {
							$iddel = $_GET['id_del'];

							$del = "DELETE FROM tbl_category WHERE id = '".$iddel."'";
							$img = "SELECT * FROM tbl_category WHERE id = '".$iddel."'";
							$ambil = mysqli_query($conn, $img);
							$dataimg = mysqli_fetch_assoc($ambil);

							if ($dataimg['photo']!="") {
								unlink('image/'.$dataimg['photo']);
							}

							$msql = mysqli_query($conn, $del);

						}
					?>
					<table class="table table-striped table-bordered table-hover" id="saya">
						<thead>
							<tr>
								<th>ID</th>
								<th>NAMA</th>
								<th>PHOTO</th>
								<th>ACTION</th>
							</tr>
						</thead>
						<tbody>
							<?php

								include 'connection.php';

								$view = mysqli_query($conn, "SELECT * FROM tbl_category");

								while ($rows = mysqli_fetch_array($view)) { ?>
									
									<tr>
										<td><?php echo $rows['id'] ;?></td>
										<td><?php echo $rows['name'] ;?></td>
										<td><img src="http://localhost/store/image/<?php echo $rows['photo'] ;?>" height=100 width=100></td>
										<td>
											<a href="edit-category.php?id=<?php echo $rows['id']?>">EDIT</a> | 
											<a href="?id_del=<?php echo $rows['id']?>" onclick="return confirm('Yakin kamu ingin menghapus?')">DELETE</a>
										</td>
									</tr>

								<?php }
							?>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>