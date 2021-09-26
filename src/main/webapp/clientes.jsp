<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        Clientes: <hr>
        <div class="etiform">
            <center>
            <form name="clientes" method="post" action="action">
                <table border="0">
                    <tr><td>Cedula: </td>  
                        <td><input type="text" style="text-align:left" name="cedula_cliente" size="11" placeholder="Numero de Cédula"></td>
                        <td style="width:30px;"></td>
                        <td>Direccion: </td> 
                        <td> <input type="text" style="text-align:left" name="direccion_cliente" size="20" placeholder="Digite la direccion"> </td> </tr>
                    <tr><td>Email: </td> 
                        <td> <input type="text" style="text-align:left" name="email_cliente" size="40" placeholder="Digite su email"> </td>
                        <td></td>
                        <td>Nombre: </td> 
                        <td><input type="password" style="text-align:left" name="nombre_cliente" size="20" placeholder="Digite su nombre"> </td> </tr>
                    <tr><td>Telefono: </td>
                        <td><input type="email" style="text-align:left" name="telefono_cliente" size="30" placeholder="Digite su telefono">  </td> 
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