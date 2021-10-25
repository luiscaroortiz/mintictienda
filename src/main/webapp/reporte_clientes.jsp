<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="controladores.Clientes"%>
<%@page import="java.util.ArrayList"%>
   
<div class="container border border-primary rounded my-5 p-2">
    <h1 class="text-center">Reporte Clientes</h1>
    <div class="table-responsive text-center">
        <table style="margin: 0 auto;"  class="table table-bordered table-sm w-75 text-center">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Cedula</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Email</th>
                    <th scope="col">Direccion</th>
                    <th scope="col">Telefono</th>
                </tr>
            </thead>
            <tbody>
                <% ArrayList<Clientes> lista= (ArrayList<Clientes>) request.getAttribute("lista");
                for (Clientes cliente:lista){
                %>
                <tr>
                    <td><%=cliente.getCedula_cliente()%></td>
                    <td><%=cliente.getNombre_cliente()%></td>
                    <td><%=cliente.getEmail_cliente()%></td>
                    <td><%=cliente.getDireccion_cliente()%></td>
                    <td><%=cliente.getTelefono_cliente()%></td>

                </tr>
                <%}%>
            </tbody>
        </table>
    </div>
    
</div>

<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/sweetalert2.all.min.js"></script>