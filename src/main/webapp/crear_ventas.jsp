<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <div class="container border border-primary rounded my-5 p-2  col-sm-6">
        <form method="GET" action="controlador">
            <input type="hidden" name="menu" value="Proveedores">
            <h1 class="text-center">Crear Proveedores</h1>
            <div class="row">
                <div class="col-sm-2">
                    <div class="p-1">
                        <label ><b>NIT</b></label>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="p-1">
                        <input type="text" class="form-control" id="txtnit" name="txtnit" value="" placeholder="Ingrese el nit">
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="p-1">
                        <label ><b>Telefono</b></label>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="p-1">
                        <input type="text" class="form-control" id="txttelefono" name="txttelefono" value="" placeholder="Ingrese el telefono">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">
                    <div class="p-1">
                        <label ><b>Nombre Proveedor</b></label>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="p-1">
                        <input type="text" class="form-control" id="txtnombre" name="txtnombre" value="" placeholder="Ingrese el nombre">
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="p-1">
                        <label ><b>Ciudad</b></label>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="p-1">
                        <input type="text" class="form-control" id="txtciudad" name="txtciudad" value="" placeholder="Ingrese el correo">
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
                        <input type="text" class="form-control" id="txtdireccion" name="txtdireccion" value="" placeholder="Ingrese la ciudad">
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

    yyyyyyyyyyyyyyyy

    <div class="row">
		<div class="col-md-5 seccion1">
			<form method="get" action="Controlador">
				<div class="card">
					<div class="card-body">
						<div class="form-group">
							<label> Datos Clientes</label>
						</div>
						<input type="hidden" name="menu" value="ventas"> <input
							type="hidden" name="UsuarioActivo" value="">
						<div class="form-group d-flex">
							<div class="col-sm-6 d-flex">
								<input type="number" name="cedulacliente" class="form-control"
									placeholder="cedula cliente" value=""> <input
									type="submit" name="accion" value="BuscarCliente"
									class="btn btn-outline-info">
							</div>
							<div class="col-sm-6">
								<input type="text" name="nombrecliente" class="form-control"
									placeholder="Nombre Cliente" value="">
							</div>
						</div>
					</div>
				</div>
				<div class="card">
					<div class="card-body">
						<div class="form-group">
							<label> Datos Productos </label>
						</div>
						<div class="form-group d-flex">
							<div class="col-sm-6 d-flex">
								<input type="number" name="codigoproducto" class="form-control"
									placeholder="cod producto" value=""> <input
									type="submit" name="accion" value="BuscarProducto"
									class="btn btn-outline-info">
							</div>
							<div class="col-sm-6">
								<input type="text" name="nombreproducto" class="form-control"
									placeholder="Nombre producto" value="">
							</div>
						</div>
						<div class="form-group d-flex">
							<div class="col-sm-6 d-flex">
								<input type="text" name="precioproducto" class="form-control"
									placeholder="$  0000.00" value="">

							</div>
							<div class="col-sm-3">
								<input type="text" name="cantidadproducto" class="form-control"
									placeholder="Cantidad" value="">
							</div>
							<div class="col-sm-3">
								<input type="text" name="ivaproducto" class="form-control"
									placeholder="Valor Iva" value="">
							</div>
						</div>
						<div class="form-group d-flex">
							<input type="submit" name="accion" value="AgregarProducto"
								class="btn btn-outline-primary">
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>


    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/sweetalert2.all.min.js"></script>

    <% if(request.getAttribute("respuesta")!= null){
        String respuesta = (String)request.getAttribute("respuesta");
        if(respuesta.equals("ok_guardar_proveedor")){ %>
            <script>
                Swal.fire(
                    {
                        icon: 'success',
                        title: 'Se guardo con exito!',
                        text: 'Se creo un nuevo proveedor',
                        confirmButtonText: 'Aceptar',
                        confirmButtonColor: '#3085d6'
                    }
                )
            </script>
        <%}
        if(respuesta.equals("error_guardar_proveedor")){ %>
            <script>
                Swal.fire(
                    {
                        icon: 'error',
                        title: 'Error al guardar!',
                        text: 'No se pudo crear el proveedor',
                        confirmButtonText: 'Aceptar',
                        confirmButtonColor: '#3085d6'
                    }
                )
            </script>
        <%}
    }%>