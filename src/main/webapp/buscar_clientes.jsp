<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <div class="container border border-primary rounded my-5 p-2  col-sm-6">
        <h1 class="text-center">Buscar cliente</h1>
        <form method="GET" action="controlador">
            <input type="hidden" name="menu" value="Clientes">
            <div class="row">
                <div class="col-sm-4">
                    <div class="p-1 text-center">
                        <label ><b>Cedula</b></label>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="p-1 text-center">
                        <input type="text" class="form-control" id="txtcedula" name="txtcedula" value="${clienteSeleccionado.getCedula_cliente()}" placeholder="Ingrese la cedula">
                    </div>
                </div>
                <div class="col-sm-4 text-center">
                    <div class="p-1">
                        <button type="submit" name="accion" value="Buscar" class="btn btn-primary">Buscar</button>
                    </div>
                </div>

            </div>

        </form>

        <% if(request.getAttribute("resultado_busqueda")!= null){
        String resultado_busqueda = (String)request.getAttribute("resultado_busqueda");
        if(resultado_busqueda.equals("encontrado")){
        %>

        <h3 class="text-center">Datos cliente</h3>
        <div class="row">
            <div class="col-sm-2">
                <div class="p-1">
                    <label ><b>Cedula</b></label>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="p-1">
                    <input type="text" class="form-control" id="txtcedula" name="txtcedula" value="${clienteSeleccionado.getCedula_cliente()}" placeholder="Ingrese la cedula" readonly>
                </div>
            </div>
            <div class="col-sm-2">
                <div class="p-1">
                    <label ><b>Telefono</b></label>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="p-1">
                    <input type="text" class="form-control" id="txttelefono" name="txttelefono" value="${clienteSeleccionado.getTelefono_cliente()}" placeholder="Ingrese el telefono" readonly>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-2">
                <div class="p-1">
                    <label ><b>Nombre Completo</b></label>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="p-1">
                    <input type="text" class="form-control" id="txtnombre" name="txtnombre" value="${clienteSeleccionado.getNombre_cliente()}" placeholder="Ingrese el nombre" readonly>
                </div>
            </div>

            <div class="col-sm-2">
                <div class="p-1">
                    <label ><b>Correo electronico</b></label>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="p-1">
                    <input type="text" class="form-control" id="txtemail" name="txtemail" value="${clienteSeleccionado.getEmail_cliente()}"  placeholder="Ingrese el correo" readonly>
                </div>
            </div>

        </div>

        <div class="row">
            <div class="col-sm-2">
                <div class="p-1">
                    <label ><b>Direccion</b></label>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="p-1">
                    <input type="text" class="form-control" id="txtdireccion" name="txtdireccion" value="${clienteSeleccionado.getDireccion_cliente()}" placeholder="Ingrese la direccion">
                </div>
            </div>

        </div>

        <div class="row justify-content-center">
            <div class="col-6 text-center">
                <div class="p-3">
                    <a class="btn btn-warning" href="controlador?menu=editar_clientes&id=${clienteSeleccionado.getCedula_cliente()}">Editar</a>

                    <a class="btn btn-danger" href="controlador?menu=Clientes&accion=Eliminar&id=${clienteSeleccionado.getCedula_cliente()}">Eliminar</a>
                </div>
            </div>
        </div>
        <%}}%>

    </div>


    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/sweetalert2.all.min.js"></script>

    <% if(request.getAttribute("resultado_busqueda")!= null){
        String resultado_busqueda = (String)request.getAttribute("resultado_busqueda");
        if(resultado_busqueda.equals("no_encontrado")){

        %>
            <script>
                Swal.fire(
                    {
                        icon: 'warning',
                        title: 'No encontrado!',
                        text: 'No se encontro el usuario ingresado',
                        confirmButtonText: 'Aceptar',
                        confirmButtonColor: '#3085d6'
                    }
                )
            </script>
        <%}
    }%>