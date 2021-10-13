<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="controladores.Proveedores"%>
<%@page import="java.util.ArrayList"%>
   
<div class="container border border-primary rounded my-5 p-2">
    <h1 class="text-center">Proveedores Creados</h1>
    <div class="table-responsive text-center">
        <table style="margin: 0 auto;"  class="table table-bordered table-sm w-75 text-center">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">NIT</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Ciudad</th>
                    <th scope="col">Direccion</th>
                    <th scope="col">Telefono</th>
                    <th scope="col">Editar</th>
                    <th scope="col">Eliminar</th>
                </tr>
            </thead>
            <tbody>
                <% ArrayList<Proveedores> lista= (ArrayList<Proveedores>) request.getAttribute("lista");
                for (Proveedores proveedor:lista){
                %>
                <tr>
                    <td><%=proveedor.getNitproveedor()%></td>
                    <td><%=proveedor.getNombre_proveedor()%></td>
                    <td><%=proveedor.getCiudad_proveedor()%></td>
                    <td><%=proveedor.getDireccion_proveedor()%></td>
                    <td><%=proveedor.getTelefono_proveedor()%></td>
                    <td>
                        <a class="btn btn-warning" href="controlador?menu=editar_proveedores&id=<%=proveedor.getNitproveedor()%>">Editar</a>
                    </td>
                    <td>
                        <a class="btn btn-danger" href="controlador?menu=Proveedores&accion=Eliminar&id=<%=proveedor.getNitproveedor()%>">Eliminar</a>
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

    if(respuesta.equals("ok_eliminar_proveedor")){ %>
        <script>
            Swal.fire(
                {
                    icon: 'success',
                    title: 'Se elimino con exito!',
                    text: 'Se elimino el proveedor seleccionado',
                    confirmButtonText: 'Aceptar',
                    confirmButtonColor: '#3085d6'
                }
            )
        </script>
    <%}
    if(respuesta.equals("error_eliminar_proveedor")){ %>
        <script>
            Swal.fire(
                {
                    icon: 'error',
                    title: 'Error al eliminar!',
                    text: 'No se pudo eliminar el proveedor',
                    confirmButtonText: 'Aceptar',
                    confirmButtonColor: '#3085d6'
                }
            )

        </script>
    <%}
    if(respuesta.equals("ok_editar_proveedor")){ %>
        <script>
            Swal.fire(
                {
                    icon: 'success',
                    title: 'Se edito con exito!',
                    text: 'Se modificao el proveedor seleccionado',
                    confirmButtonText: 'Aceptar',
                    confirmButtonColor: '#3085d6'
                }
            )
        </script>
    <%}
    if(respuesta.equals("error_eliminar_proveedor")){ %>
        <script>
            Swal.fire(
                {
                    icon: 'error',
                    title: 'Error al editar!',
                    text: 'No se pudo modificar el proveedor',
                    confirmButtonText: 'Aceptar',
                    confirmButtonColor: '#3085d6'
                }
            )

        </script>
    <%}
}%>