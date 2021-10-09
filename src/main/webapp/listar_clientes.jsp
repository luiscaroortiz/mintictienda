<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="controladores.Clientes"%>
<%@page import="java.util.ArrayList"%>
   
<div class="container border border-primary rounded my-5 p-2">
    <h1 class="text-center">Clientes Creados</h1>
    <div class="table-responsive text-center">
        <table style="margin: 0 auto;"  class="table table-bordered table-sm w-75 text-center">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Cedula</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Email</th>
                    <th scope="col">Direccion</th>
                    <th scope="col">Telefono</th>
                    <th scope="col">Editar</th>
                    <th scope="col">Eliminar</th>
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
                    <td>
                        <a class="btn btn-warning" href="controlador?menu=editar_clientes&id=<%=cliente.getCedula_cliente()%>">Editar</a>
                    </td>
                    <td>
                        <a class="btn btn-danger" href="controlador?menu=Clientes&accion=Eliminar&id=<%=cliente.getCedula_cliente()%>">Eliminar</a>
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

    if(respuesta.equals("ok_eliminar_cliente")){ %>
        <script>
            Swal.fire(
                {
                    icon: 'success',
                    title: 'Se elimino con exito!',
                    text: 'Se elimino el cliente seleccionado',
                    confirmButtonText: 'Aceptar',
                    confirmButtonColor: '#3085d6'
                }
            )
        </script>
    <%}
    if(respuesta.equals("error_eliminar_cliente")){ %>
        <script>
            Swal.fire(
                {
                    icon: 'error',
                    title: 'Error al eliminar!',
                    text: 'No se pudo eliminar el cliente',
                    confirmButtonText: 'Aceptar',
                    confirmButtonColor: '#3085d6'
                }
            )

        </script>
    <%}
    if(respuesta.equals("ok_editar_cliente")){ %>
        <script>
            Swal.fire(
                {
                    icon: 'success',
                    title: 'Se edito con exito!',
                    text: 'Se modificao el cliente seleccionado',
                    confirmButtonText: 'Aceptar',
                    confirmButtonColor: '#3085d6'
                }
            )
        </script>
    <%}
    if(respuesta.equals("error_eliminar_cliente")){ %>
        <script>
            Swal.fire(
                {
                    icon: 'error',
                    title: 'Error al editar!',
                    text: 'No se pudo modificar el cliente',
                    confirmButtonText: 'Aceptar',
                    confirmButtonColor: '#3085d6'
                }
            )

        </script>
    <%}
}%>