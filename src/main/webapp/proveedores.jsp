<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="estilo.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
       Proveedores: <hr>
        <div class="etiform">
            <center>
            <form name="proveedores" method="post" action="action">
                <table border="0">
                    <tr><td>Nit: </td>  
                        <td><input type="text" style="text-align:left" name="nitproveedor" size="11" placeholder="Digite NIT"></td>
                        <td style="width:30px;"></td>
                        <td>Ciudad: </td> 
                        <td> <input type="text" style="text-align:left" name="ciudad_proveedor" size="20" placeholder="Digite la ciudad de sus instalaciones"> </td> </tr>
                    <tr><td>Direccion: </td> 
                        <td> <input type="text" style="text-align:left" name="direccion_proveedor" size="40" placeholder="Digite la direccion de sus instalaciones"> </td>
                        <td></td>
                        <td>Nombre: </td> 
                        <td><input type="password" style="text-align:left" name="nombre_proveedor" size="20" placeholder="Digite el Nombre del negocio"> </td> </tr>
                    <tr><td>telefono: </td>
                        <td><input type="email" style="text-align:left" name="telefono_proveedor" size="30" placeholder="digite su telefono">  </td> 
                        <td> </td> <td> </td> <td> </td> </tr> 
                    <tr><td colspan="5" height="20"></td></tr>
                    <tr><td colspan="5"> 
                            <input type="button" style="width: 150px;" name="consultar" Value="consultar">
                            <input type="button" style="width: 150px;" name="crear" Value="crear">
                            <input type="button" style="width: 150px;" name="actualizar" Value="actualizar">
                            <input type="button" style="width: 150px;" name="Borrar" Value="Borrar">
                        </td> </tr>
                </table>
            </form>
            </center>
        </div>

    </body>
</html>
