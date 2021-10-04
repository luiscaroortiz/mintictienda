<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="controladores.Usuarios"%>
<%@page import="java.util.ArrayList"%>
   
<div class="container border border-primary rounded my-5 p-2  col-sm-6">
    <div class="col-md-8">
        <table class="table">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Cedula</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Email</th>
                    <th scope="col">Usuario</th>
                    <th scope="col">Password</th>
                    <th scope="col">Editar</th>
                    <th scope="col">Eliminar</th>
                </tr>
            </thead>
            <tbody>
                <% ArrayList<Usuarios> lista= (ArrayList<Usuarios>) request.getAttribute("lista");
                for (Usuarios usuario:lista){
                %>
                <tr>
                    <td><%=usuario.getCedula_usuario()%></td>
                    <td><%=usuario.getNombre_usuario()%></td>
                    <td><%=usuario.getEmail_usuario()%></td>
                    <td><%=usuario.getUsuario()%></td>
                    <td><%=usuario.getPassword()%></td>
                    <td>
                        <a class="btn btn-warning" href="controlador?menu=Usuarios&accion=Cargar&id=<%=usuario.getCedula_usuario()%>">Editar</a>
                    </td>
                    <td>
                        <a class="btn btn-danger" href="controlador?menu=Usuarios&accion=Eliminar&id=<%=usuario.getCedula_usuario()%>">Eliminar</a>
                    </td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </div>
    
</div>

<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/sweetalert2.all.min.js"></script>

<% if(request.getAttribute("respuesta")!= null){
    String respuesta = (String)request.getAttribute("respuesta");
    if(respuesta.equals("ok_eliminar_usuario")){ %>
        <script>
            Swal.fire(
                {
                    icon: 'success',
                    title: 'Se elimino con exito!',
                    text: 'Se elimino el usuario seleccionado',
                    confirmButtonText: 'Aceptar',
                    confirmButtonColor: '#3085d6'
                }
            )
        </script>
    <%}
    if(respuesta.equals("error_eliminar_usuario")){ %>
        <script>
            Swal.fire(
                {
                    icon: 'error',
                    title: 'Error al eliminar!',
                    text: 'No se pudo eliminar el usuario',
                    confirmButtonText: 'Aceptar',
                    confirmButtonColor: '#3085d6'
                }
            )
        </script>
    <%}
}%>