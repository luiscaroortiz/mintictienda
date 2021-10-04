<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>
</head>

<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class=" container-fluid">
			<a class="navbar-brand" href="#">
				<img src="imagenes/logo2.png" width="50px" alt="...">
				Tienda
			</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>|
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item dropdown ">
						<a class="nav-link dropdown-toggle active" href="#" id="navbarDropdown" role="button"
							data-bs-toggle="dropdown" aria-expanded="false">
							Usuarios
						</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="controlador?menu=crear_usuarios" target="miContenedor">Crear</a></li>
							<li><a class="dropdown-item" href="controlador?menu=listar_usuarios" target="miContenedor">Listar</a></li>
							<li><a class="dropdown-item" href="controlador?menu=listar_usuarios" target="miContenedor">buscar</a></li>
							<li><a class="dropdown-item" href="#">Eliminar</a></li>
						</ul>
					</li>
					<li class="nav-item dropdown ">
						<a class="nav-link dropdown-toggle active" href="#" id="navbarDropdown" role="button"
							data-bs-toggle="dropdown" aria-expanded="false">
							Clientes
						</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="#">Crear</a></li>
							<li><a class="dropdown-item" href="#">Editar</a></li>
							<li><a class="dropdown-item" href="#">buscar</a></li>
							<li><a class="dropdown-item" href="#">Eliminar</a></li>
						</ul>
					</li>
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle active" href="#" id="navbarDropdown" role="button"
							data-bs-toggle="dropdown" aria-expanded="false">
							Proveedores
						</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="#">Crear</a></li>
							<li><a class="dropdown-item" href="#">Editar</a></li>
							<li><a class="dropdown-item" href="#">buscar</a></li>
							<li><a class="dropdown-item" href="#">Eliminar</a></li>
						</ul>
					</li>
					<li class="nav-item dropdown ">
						<a class="nav-link dropdown-toggle active" href="#" id="navbarDropdown" role="button"
							data-bs-toggle="dropdown" aria-expanded="false">
							Ventas
						</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="#">Crear</a></li>
						</ul>
					</li>

				</ul>
				<div class="dropdown mx-5">
					<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
					${usuario.getUsuario()}
					</button>
					<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
						<li><a class="dropdown-item" href="#">${usuario.getNombre_usuario()}</a></li>
						<li><a class="dropdown-item" href="#">${usuario.getEmail_usuario()}</a></li>
						
						<div class="dropdown-divider"></div>
                        <form class="dropdown-item" method="POST" action="validar_servlet">
                            <button class="btn btn-danger center-block" type="submit" name="accion" value="Salir">Cerrar Sesion</button>
                        </form>
					</ul>
				</div>
			</div>
		</div>
	</nav>

	<div class="m-3" style="height:550px;">
		<iframe name="miContenedor" id="miContenedor" style="height:100%; width:100%;" frameBorder="1"></iframe>
	</div>


</body>


<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.bundle.min.js"></script>


<!--
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</html>
-->