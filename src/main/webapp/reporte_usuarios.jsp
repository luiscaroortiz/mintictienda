<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="controladores.Usuarios"%>
<%@page import="java.util.ArrayList"%>
   
<div class="container border border-primary rounded my-5 p-2">
    <h1 class="text-center">Reporte Usuarios</h1>
    <div class="table-responsive text-center">
        <table style="margin: 0 auto;"  class="table table-bordered table-sm w-75 text-center">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Cedula</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Email</th>
                    <th scope="col">Usuario</th>
                    <th scope="col">Password</th>

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
                </tr>
                <%}%>
            </tbody>
        </table>
    </div>
    
</div>

<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/sweetalert2.all.min.js"></script>
