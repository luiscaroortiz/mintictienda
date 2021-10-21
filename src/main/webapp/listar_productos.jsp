<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="controladores.Productos"%>
<%@page import="java.util.ArrayList"%>
   
<div class="container border border-primary rounded my-5 p-2">
    <h1 class="text-center">Productos Creados</h1>
    <div class="table-responsive text-center">
        <table style="margin: 0 auto;"  class="table table-bordered table-sm w-75 text-center">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Codigo</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Nit Proveedor</th>
                    <th scope="col">Precio Compra</th>
                    <th scope="col">Iva Compra</th>
                    <th scope="col">Precio venta</th>
                    <th scope="col">Editar</th>
                    <th scope="col">Eliminar</th>
                </tr>
            </thead>
            <tbody>
                <% ArrayList<Productos> lista= (ArrayList<Productos>) request.getAttribute("lista");
                for (Productos producto:lista){
                %>
                <tr>
                    <td><%=producto.getCodigo_producto()%></td>
                    <td><%=producto.getNombre_producto()%></td>
                    <td><%=producto.getNitproveedor()%></td>
                    <td><%=producto.getPrecio_compra()%></td>
                    <td><%=producto.getIvacompra()%></td>
                    <td><%=producto.getPrecio_venta()%></td>
                    <td>
                        <a class="btn btn-warning" href="controlador?menu=editar_productos&id=<%=producto.getCodigo_producto()%>">Editar</a>
                    </td>
                    <td>
                        <a class="btn btn-danger" href="controlador?menu=Productos&accion=Eliminar&id=<%=producto.getCodigo_producto()%>">Eliminar</a>
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

    if(respuesta.equals("ok_eliminar_producto")){ %>
        <script>
            Swal.fire(
                {
                    icon: 'success',
                    title: 'Se elimino con exito!',
                    text: 'Se elimino el producto seleccionado',
                    confirmButtonText: 'Aceptar',
                    confirmButtonColor: '#3085d6'
                }
            )
        </script>
    <%}
    if(respuesta.equals("error_eliminar_producto")){ %>
        <script>
            Swal.fire(
                {
                    icon: 'error',
                    title: 'Error al eliminar!',
                    text: 'No se pudo eliminar el producto',
                    confirmButtonText: 'Aceptar',
                    confirmButtonColor: '#3085d6'
                }
            )

        </script>
    <%}
    if(respuesta.equals("ok_editar_producto")){ %>
        <script>
            Swal.fire(
                {
                    icon: 'success',
                    title: 'Se edito con exito!',
                    text: 'Se modificao el producto seleccionado',
                    confirmButtonText: 'Aceptar',
                    confirmButtonColor: '#3085d6'
                }
            )
        </script>
    <%}
    if(respuesta.equals("error_eliminar_producto")){ %>
        <script>
            Swal.fire(
                {
                    icon: 'error',
                    title: 'Error al editar!',
                    text: 'No se pudo modificar el prodcuto',
                    confirmButtonText: 'Aceptar',
                    confirmButtonColor: '#3085d6'
                }
            )

        </script>
    <%}
}%>