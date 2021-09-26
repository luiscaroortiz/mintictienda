<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
    </head>
    <body>
        <div class="container position-absolute top-50 start-50 translate-middle center-block">

            <div class="row justify-content-center">
                <div class="card" style="width: 18rem;">
                    <img src="imagenes/logo2.png" class="card-img-top" alt="...">

                    <div class="card-body">
                        <form method="get" action="validar_servlet">
                            <div class="form-group">
                                <label>Usuario</label>
                                <input type="text" class="form-control" id="txtusuario" name="txtusuario">

                            </div>
                            <div class="form-group">
                                    <label for="exampleInputPassword1">Contraseña</label>
                                    <input type="password" class="form-control" id="txtpassword" name="txtpassword">
                            </div>
                            <div class="text-center">
                                <button type="submit" class="btn btn-primary m-3" name="accion" value="Ingresar">Ingresar</button>

                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <link href="css/bootstrap.min.css" rel="stylesheet">
	<script src="js/bootstrap.bundle.min.js"></script>
</html>	