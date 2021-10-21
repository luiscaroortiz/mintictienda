<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="controladores.Proveedores"%>
<%@page import="java.util.ArrayList"%>
    <div class="container border border-primary rounded my-5 p-2  col-sm-6">
        <form method="GET" action="controlador">
            <input type="hidden" name="menu" value="Productos">
            <h1 class="text-center">Crear Productos</h1>
            <div class="row">
                <div class="col-sm-2">
                    <div class="p-1">
                        <label ><b>Codigo</b></label>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="p-1">
                        <input type="text" class="form-control" id="txtcodigo" name="txtcodigo" value="" placeholder="Ingrese el codigo">
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="p-1">
                        <label ><b>Nombre</b></label>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="p-1">
                        <input type="text" class="form-control" id="txtnombre" name="txtnombre" value="" placeholder="Ingrese el nombre">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">
                    <div class="p-1">
                        <label ><b>Proveedor</b></label>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="p-1">
                        <select class="form-select form-select-sm" aria-label=".form-select-sm example"  id="txtnit" name="txtnit">
                            <% ArrayList<Proveedores> lista= (ArrayList<Proveedores>) request.getAttribute("lista");
                                for (Proveedores proveedor:lista){
                                %>
                                <option value="<%=proveedor.getNitproveedor()%>"><%=proveedor.getNombre_proveedor()%></option>
                                <%}%>
                        </select>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="p-1">
                        <label ><b>Precio Compra</b></label>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="p-1">
                        <input type="text" class="form-control" id="txtprecio_compra" name="txtprecio_compra" value="" placeholder="Ingrese el precio compra">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">
                    <div class="p-1">
                        <label ><b>Iva Compra</b></label>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="p-1">
                        <input type="text" class="form-control" id="txtiva_compra" name="txtiva_compra" value="" placeholder="Ingrese el iva">
                    </div>
                </div>

                <div class="col-sm-2">
                    <div class="p-1">
                        <label ><b>Precio venta</b></label>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="p-1">
                        <input type="text" class="form-control" id="txtprecio_venta" name="txtprecio_venta" value="" placeholder="Ingrese el precio venta">
                    </div>
                </div>

            </div>
            <div class="row justify-content-center">
                <div class="col-6 text-center">
                    <div class="p-3">
                        <button type="submit" name="accion" value="Guardar" class="btn btn-primary">Guardar</button>
                    </div>
                </div>
            </div>
        </form>
    </div>


    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/sweetalert2.all.min.js"></script>

    <% if(request.getAttribute("respuesta")!= null){
        String respuesta = (String)request.getAttribute("respuesta");
        if(respuesta.equals("ok_guardar_producto")){ %>
            <script>
                Swal.fire(
                    {
                        icon: 'success',
                        title: 'Se guardo con exito!',
                        text: 'Se creo un nuevo producto',
                        confirmButtonText: 'Aceptar',
                        confirmButtonColor: '#3085d6'
                    }
                )
            </script>
        <%}
        if(respuesta.equals("error_guardar_producto")){ %>
            <script>
                Swal.fire(
                    {
                        icon: 'error',
                        title: 'Error al guardar!',
                        text: 'No se pudo crear el producto',
                        confirmButtonText: 'Aceptar',
                        confirmButtonColor: '#3085d6'
                    }
                )
            </script>
        <%}
    }%>