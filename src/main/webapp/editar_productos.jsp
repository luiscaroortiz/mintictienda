<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="controladores.Proveedores"%>
    <%@page import="controladores.Productos"%>
<%@page import="java.util.ArrayList"%>
    <div class="container border border-primary rounded my-5 p-2  col-sm-6">
        <form method="GET" action="controlador">
            <input type="hidden" name="menu" value="Productos">
            <h1 class="text-center">Editar Producto</h1>
            <div class="row">
                <div class="col-sm-2">
                    <div class="p-1">
                        <label ><b>Codigo</b></label>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="p-1">
                        <input type="text" class="form-control" id="txtcodigo" name="txtcodigo" value="${productoSeleccionado.getCodigo_producto()}" placeholder="Ingrese el codigo" readonly>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="p-1">
                        <label ><b>Nombre</b></label>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="p-1">
                        <input type="text" class="form-control" id="txtnombre" name="txtnombre" value="${productoSeleccionado.getNombre_producto()}" placeholder="Ingrese el telefono">
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
                            <% 
                     
                            Productos producto = (Productos) request.getAttribute("productoSeleccionado");
                            
                            ArrayList<Proveedores> lista= (ArrayList<Proveedores>) request.getAttribute("lista");
                                for (Proveedores proveedor:lista){
                                    String selected;
                                    selected = "selected";

                                if (producto.getNitproveedor() == proveedor.getNitproveedor())
                                    {
                                        selected = "";
                                    }

                                %>
                                <option value="<%=proveedor.getNitproveedor()%>"  <%=selected%>><%=proveedor.getNombre_proveedor()%></option>
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
                        <input type="text" class="form-control" id="txtprecio_compra" name="txtprecio_compra" value="${productoSeleccionado.getPrecio_compra()}" placeholder="Ingrese el precio compra">
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
                        <input type="text" class="form-control" id="txtiva_compra" name="txtiva_compra" value="${productoSeleccionado.getIvacompra()}" placeholder="Ingrese el iva compra">
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="p-1">
                        <label ><b>Precio venta</b></label>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="p-1">
                        <input type="text" class="form-control" id="txtprecio_venta" name="txtprecio_venta" value="${productoSeleccionado.getPrecio_compra()}" placeholder="Ingrese el precio venta">
                    </div>
                </div>

            </div>
            <div class="row justify-content-center">
                <div class="col-6 text-center">
                    <div class="p-3">
                        <button type="submit" name="accion" value="Actualizar" class="btn btn-primary">Editar</button>
                    </div>
                </div>
            </div>
        </form>
    </div>


    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/sweetalert2.all.min.js"></script>