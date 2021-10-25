package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class controlador
 */
@WebServlet("/controlador")
public class controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Usuarios usuarios = new Usuarios();
	long numfac = 0;
	Detalle_ventas detalle_venta = new Detalle_ventas();
	int cantidad = 0, item = 0;
	long totalapagar = 0;
	long codProducto = 0, subtotaliva = 0, acusubtotal = 0;
	Double precio = 0.0, subtotal = 0.0,  valor_iva = 0.0, iva = 0.0;
	String descripcion, cedulaCliente;
	List<Detalle_ventas> listaVentas = new ArrayList<>();


    public controlador() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void buscarCliente(Long id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ArrayList<Clientes> listac = Json.getJSON_clientes();
			for (Clientes clientes : listac) {
				if (clientes.getCedula_cliente() == (id)) {
					request.setAttribute("clienteSeleccionado", clientes);//temporal
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public void buscarProducto(Long cod, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ArrayList<Productos> listap = Json.getJSON_productos();
			for (Productos productos : listap) {
				if (productos.getCodigo_producto() == (cod)) {
					request.setAttribute("productoSeleccionado", productos); // envio a ventas
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public void buscarFactura(String numFact, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (numFact == null) {
			numfac = Long.parseLong(numFact) + 1; // variable declarada arriba con valor 0
			//
		} else {
			numfac = Long.parseLong(numFact) + 1; // variable declarada arriba con valor 0
		}
		request.setAttribute("numerofactura", numfac);

	}
    
    public void grabarDetalle(Long numFact, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		for (int i = 0; i < listaVentas.size(); i++) {
			detalle_venta = new Detalle_ventas();
			detalle_venta.setCodigo_detalle_venta(String.valueOf(i + 1));
			detalle_venta.setCodigo_venta(numFact);
			detalle_venta.setCodigo_producto(listaVentas.get(i).getCodigo_producto());
			detalle_venta.setCantidad_producto(listaVentas.get(i).getCantidad_producto());
			detalle_venta.setValor_total(listaVentas.get(i).getValor_total());
			detalle_venta.setValor_venta(listaVentas.get(i).getValor_venta());
			detalle_venta.setValor_iva(listaVentas.get(i).getValor_iva());

			int respuesta = 0;
			try {
				respuesta = Json.postJSON_Detalle_Venta(detalle_venta);
				PrintWriter write = response.getWriter();
				if (respuesta == 200) {
					request.getRequestDispatcher("Controlador?menu=Ventas&accion=default").forward(request, response);
					// linea de codigo para que vuelva a cargar la venta
					System.out.println("Registros Grabados detalle ventas");
				} else {
					write.println("Error Detalle venta" + respuesta);
				}
				write.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			listaVentas.clear();
			item = 0;
			totalapagar = 0;
			subtotal = 0.0;
			valor_iva = 0.0;
			acusubtotal = 0;
			subtotaliva = 0;
			totalapagar = 0;

		}
    }	


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        //////////////MENU

		if (menu.equals("Principal")) {
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        }
		//////usuarios
        if (menu.equals("crear_usuarios")) {
            request.getRequestDispatcher("crear_usuarios.jsp").forward(request, response);
        }
		if (menu.equals("buscar_usuarios")) {
            request.getRequestDispatcher("buscar_usuarios.jsp").forward(request, response);
        }
		if (menu.equals("listar_usuarios")) {
			try {
				ArrayList<Usuarios> lista = Json.getJSON();
				request.setAttribute("lista", lista);
			} catch (Exception e) {
				e.printStackTrace();
			}
            request.getRequestDispatcher("listar_usuarios.jsp").forward(request, response);
        }
		if (menu.equals("editar_usuarios")) {

			Long id= Long.parseLong(request.getParameter("id"));
			try {
				ArrayList<Usuarios> lista1 = Json.getJSON();
				System.out.println("Parametro: " + id);
				for (Usuarios usuarios:lista1){
					if (usuarios.getCedula_usuario()==id) {
						request.setAttribute("usuarioSeleccionado", usuarios);
						request.getRequestDispatcher("editar_usuarios.jsp").forward(request, response);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

        }
		//////clientes
		if (menu.equals("crear_clientes")) {
            request.getRequestDispatcher("crear_clientes.jsp").forward(request, response);
        }
		if (menu.equals("buscar_clientes")) {
            request.getRequestDispatcher("buscar_clientes.jsp").forward(request, response);
        }
		if (menu.equals("listar_clientes")) {
			try {
				ArrayList<Clientes> lista = Json.getJSON_clientes();
				request.setAttribute("lista", lista);
			} catch (Exception e) {
				e.printStackTrace();
			}
            request.getRequestDispatcher("listar_clientes.jsp").forward(request, response);
        }
		if (menu.equals("editar_clientes")) {

			Long id= Long.parseLong(request.getParameter("id"));
			try {
				ArrayList<Clientes> lista1 = Json.getJSON_clientes();
				System.out.println("Parametro: " + id);
				for (Clientes clientes:lista1){
					if (clientes.getCedula_cliente()==id) {
						request.setAttribute("clienteSeleccionado", clientes);
						request.getRequestDispatcher("editar_clientes.jsp").forward(request, response);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
        }

		//////proveedores
		if (menu.equals("crear_proveedores")) {
            request.getRequestDispatcher("crear_proveedores.jsp").forward(request, response);
        }

		if (menu.equals("buscar_proveedores")) {
            request.getRequestDispatcher("buscar_proveedores.jsp").forward(request, response);
        }
		if (menu.equals("listar_proveedores")) {
			try {
				ArrayList<Proveedores> lista = Json.getJSON_proveedores();
				request.setAttribute("lista", lista);
			} catch (Exception e) {
				e.printStackTrace();
			}
            request.getRequestDispatcher("listar_proveedores.jsp").forward(request, response);
        }
		if (menu.equals("editar_proveedores")) {

			Long id= Long.parseLong(request.getParameter("id"));
			try {
				ArrayList<Proveedores> lista1 = Json.getJSON_proveedores();
				System.out.println("Parametro: " + id);
				for (Proveedores proveedores:lista1){
					if (proveedores.getNitproveedor()==id) {
						request.setAttribute("proveedorSeleccionado", proveedores);
						request.getRequestDispatcher("editar_proveedores.jsp").forward(request, response);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

        }

		//////productos
		if (menu.equals("crear_productos")) {
			try {
				ArrayList<Proveedores> lista = Json.getJSON_proveedores();
				request.setAttribute("lista", lista);
			} catch (Exception e) {
				e.printStackTrace();
			}
            request.getRequestDispatcher("crear_productos.jsp").forward(request, response);
        }

		if (menu.equals("buscar_productos")) {
            request.getRequestDispatcher("buscar_productos.jsp").forward(request, response);
        }
		if (menu.equals("listar_productos")) {
			try {
				ArrayList<Productos> lista = Json.getJSON_productos();
				request.setAttribute("lista", lista);
			} catch (Exception e) {
				e.printStackTrace();
			}
            request.getRequestDispatcher("listar_productos.jsp").forward(request, response);
        }
		if (menu.equals("editar_productos")) {

			try {
				ArrayList<Proveedores> lista = Json.getJSON_proveedores();
				request.setAttribute("lista", lista);
			} catch (Exception e) {
				e.printStackTrace();
			}

			Long id= Long.parseLong(request.getParameter("id"));
			try {
				ArrayList<Productos> lista1 = Json.getJSON_productos();
				System.out.println("Parametro: " + id);
				for (Productos productos:lista1){
					if (productos.getCodigo_producto()==id) {
						request.setAttribute("productoSeleccionado", productos);
						request.getRequestDispatcher("editar_productos.jsp").forward(request, response);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

        }
		//////ventas
		if (menu.equals("crear_ventas")) {
            request.getRequestDispatcher("ventas.jsp").forward(request, response);
        }
		
	////reportes
			if (menu.equals("reporte_usuarios")) {
				try {
					ArrayList<Usuarios> lista = Json.getJSON();
					request.setAttribute("lista", lista);
				} catch (Exception e) {
					e.printStackTrace();
				}
	            request.getRequestDispatcher("reporte_usuarios.jsp").forward(request, response);
	        }
			if (menu.equals("reporte_clientes")) {
				try {
					ArrayList<Clientes> lista = Json.getJSON_clientes();
					request.setAttribute("lista", lista);
				} catch (Exception e) {
					e.printStackTrace();
				}
	            request.getRequestDispatcher("reporte_clientes.jsp").forward(request, response);
	        }

		///////////////FIN MENU

        ////////////ACCIONES

		/////usuarios
		if (menu.equals("Usuarios")) {
			if(accion.equals("Guardar")) {
				Usuarios usuario = new Usuarios();
				usuario.setCedula_usuario(Long.parseLong(request.getParameter("txtcedula")));
				usuario.setNombre_usuario(request.getParameter("txtnombre"));
				usuario.setEmail_usuario(request.getParameter("txtemail"));
				usuario.setUsuario(request.getParameter("txtusuario"));
				usuario.setPassword(request.getParameter("txtpassword"));

				int respuesta=0;
				try {
					respuesta = Json.postJSON(usuario);
					if (respuesta==200) {
						request.setAttribute("respuesta", "ok_guardar_usuario");
						request.getRequestDispatcher("crear_usuarios.jsp").forward(request, response);

					}
					else
					{
						System.out.println("Error: " +  respuesta);
						request.setAttribute("respuesta", "error_guardar_usuario");
						request.getRequestDispatcher("crear_usuarios.jsp").forward(request, response);
					}
					System.out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(accion.equals("Eliminar")){
				Long id= Long.parseLong(request.getParameter("id"));
				int respuesta=0;
				try {
					respuesta = Json.deleteJSON(id);
					PrintWriter write = response.getWriter();
					if (respuesta==200) {
						request.setAttribute("respuesta", "ok_eliminar_usuario");
						request.getRequestDispatcher("controlador?menu=listar_usuarios").forward(request, response);
					}
					else {
						request.setAttribute("respuesta", "error_eliminar_usuario");
						request.getRequestDispatcher("controlador?menu=listar_usuarios").forward(request, response);
					}
					//write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(accion.equals("Actualizar")) {
				Usuarios usuario = new Usuarios();
				usuario.setCedula_usuario(Long.parseLong(request.getParameter("txtcedula")));
				usuario.setNombre_usuario(request.getParameter("txtnombre"));
				usuario.setEmail_usuario(request.getParameter("txtemail"));
				usuario.setUsuario(request.getParameter("txtusuario"));
				usuario.setPassword(request.getParameter("txtpassword"));

				int respuesta=0;
				try {
				respuesta = Json.putJSON(usuario,usuario.getCedula_usuario());
				PrintWriter write = response.getWriter();

				if (respuesta==200) {
					request.setAttribute("respuesta", "ok_editar_usuario");
					request.getRequestDispatcher("controlador?menu=listar_usuarios").forward(request, response);
				}
				else {
					request.setAttribute("respuesta", "error_editar_usuario");
					request.getRequestDispatcher("controlador?menu=listar_usuarios").forward(request, response);
				}
				write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(accion.equals("Buscar")) {

				Long txtcedula= Long.parseLong(request.getParameter("txtcedula"));
				try {
					ArrayList<Usuarios> lista1 = Json.getJSON();
					System.out.println("Parametro: " + txtcedula);
					for (Usuarios usuarios:lista1){
						if (usuarios.getCedula_usuario()== txtcedula) {
							request.setAttribute("usuarioSeleccionado", usuarios);
							request.setAttribute("resultado_busqueda", "encontrado");
							request.getRequestDispatcher("buscar_usuarios.jsp").forward(request, response);
						}
					}
					request.setAttribute("resultado_busqueda", "no_encontrado");
					request.getRequestDispatcher("buscar_usuarios.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
        }
		/////clientes
		if (menu.equals("Clientes")) {
			if(accion.equals("Guardar")) {
				Clientes cliente = new Clientes();
				cliente.setCedula_cliente(Long.parseLong(request.getParameter("txtcedula")));
				cliente.setNombre_cliente(request.getParameter("txtnombre"));
				cliente.setEmail_cliente(request.getParameter("txtemail"));
				cliente.setTelefono_cliente(request.getParameter("txttelefono"));
				cliente.setDireccion_cliente(request.getParameter("txtdireccion"));

				int respuesta=0;
				try {
					respuesta = Json.postJSON_clientes(cliente);
					if (respuesta==200) {
						request.setAttribute("respuesta", "ok_guardar_cliente");
						request.getRequestDispatcher("crear_clientes.jsp").forward(request, response);

					}
					else
					{
						System.out.println("Error: " +  respuesta);
						request.setAttribute("respuesta", "error_guardar_cliente");
						request.getRequestDispatcher("crear_clientes.jsp").forward(request, response);

					}
					System.out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(accion.equals("Eliminar")){
				Long id= Long.parseLong(request.getParameter("id"));
				int respuesta=0;
				try {
					respuesta = Json.deleteJSON_clientes(id);
					PrintWriter write = response.getWriter();
					if (respuesta==200) {
						request.setAttribute("respuesta", "ok_eliminar_cliente");
						request.getRequestDispatcher("controlador?menu=listar_clientes").forward(request, response);
					}
					else {
						request.setAttribute("respuesta", "error_eliminar_cliente");
						request.getRequestDispatcher("controlador?menu=listar_clientes").forward(request, response);
					}
					//write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(accion.equals("Actualizar")) {
				Clientes cliente = new Clientes();
				cliente.setCedula_cliente(Long.parseLong(request.getParameter("txtcedula")));
				cliente.setNombre_cliente(request.getParameter("txtnombre"));
				cliente.setEmail_cliente(request.getParameter("txtemail"));
				cliente.setTelefono_cliente(request.getParameter("txttelefono"));
				cliente.setDireccion_cliente(request.getParameter("txtdireccion"));

				int respuesta=0;
				try {
				respuesta = Json.putJSON_clientes(cliente,cliente.getCedula_cliente());
				PrintWriter write = response.getWriter();

				if (respuesta==200) {
					request.setAttribute("respuesta", "ok_editar_cliente");
					request.getRequestDispatcher("controlador?menu=listar_clientes").forward(request, response);
				}
				else {
					request.setAttribute("respuesta", "error_editar_cliente");
					request.getRequestDispatcher("controlador?menu=listar_clientes").forward(request, response);
				}
				write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(accion.equals("Buscar")) {

				Long txtcedula= Long.parseLong(request.getParameter("txtcedula"));
				try {
					ArrayList<Clientes> lista1 = Json.getJSON_clientes();
					System.out.println("Parametro: " + txtcedula);
					for (Clientes clientes:lista1){
						if (clientes.getCedula_cliente()== txtcedula) {
							request.setAttribute("clienteSeleccionado", clientes);
							request.setAttribute("resultado_busqueda", "encontrado");
							request.getRequestDispatcher("buscar_clientes.jsp").forward(request, response);
						}
					}
					request.setAttribute("resultado_busqueda", "no_encontrado");
					request.getRequestDispatcher("buscar_clientes.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
        }
		/////proveedores
		if (menu.equals("Proveedores")) {
			if(accion.equals("Guardar")) {
				Proveedores proveedor = new Proveedores();
				proveedor.setNitproveedor(Long.parseLong(request.getParameter("txtnit")));
				proveedor.setNombre_proveedor(request.getParameter("txtnombre"));
				proveedor.setCiudad_proveedor(request.getParameter("txtciudad"));
				proveedor.setTelefono_proveedor(request.getParameter("txttelefono"));
				proveedor.setDireccion_proveedor(request.getParameter("txtdireccion"));

				int respuesta=0;
				try {
					respuesta = Json.postJSON_proveedores(proveedor);
					if (respuesta==200) {
						request.setAttribute("respuesta", "ok_guardar_proveedor");
						request.getRequestDispatcher("crear_proveedores.jsp").forward(request, response);

					}
					else
					{
						System.out.println("Error: " +  respuesta);
						request.setAttribute("respuesta", "error_guardar_proveedor");
						request.getRequestDispatcher("crear_proveedores.jsp").forward(request, response);

					}
					System.out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(accion.equals("Eliminar")){
				Long id= Long.parseLong(request.getParameter("id"));
				int respuesta=0;
				try {
					respuesta = Json.deleteJSON_proveedores(id);
					PrintWriter write = response.getWriter();
					if (respuesta==200) {
						request.setAttribute("respuesta", "ok_eliminar_proveedor");
						request.getRequestDispatcher("controlador?menu=listar_proveedores").forward(request, response);
					}
					else {
						request.setAttribute("respuesta", "error_eliminar_proveedor");
						request.getRequestDispatcher("controlador?menu=listar_proveedores").forward(request, response);
					}
					//write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(accion.equals("Actualizar")) {
				Proveedores proveedor = new Proveedores();
				proveedor.setNitproveedor(Long.parseLong(request.getParameter("txtnit")));
				proveedor.setNombre_proveedor(request.getParameter("txtnombre"));
				proveedor.setCiudad_proveedor(request.getParameter("txtciudad"));
				proveedor.setTelefono_proveedor(request.getParameter("txttelefono"));
				proveedor.setDireccion_proveedor(request.getParameter("txtdireccion"));

				int respuesta=0;
				try {
				respuesta = Json.putJSON_proveedores(proveedor,proveedor.getNitproveedor());
				PrintWriter write = response.getWriter();

				if (respuesta==200) {
					request.setAttribute("respuesta", "ok_editar_proveedor");
					request.getRequestDispatcher("controlador?menu=listar_proveedores").forward(request, response);
				}
				else {
					request.setAttribute("respuesta", "error_editar_proveedor");
					request.getRequestDispatcher("controlador?menu=listar_proveedores").forward(request, response);
				}
				write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(accion.equals("Buscar")) {

				Long txtnit= Long.parseLong(request.getParameter("txtnit"));
				try {
					ArrayList<Proveedores> lista1 = Json.getJSON_proveedores();
					System.out.println("Parametro: " + txtnit);
					for (Proveedores proveedores:lista1){
						if (proveedores.getNitproveedor()== txtnit) {
							request.setAttribute("proveedorSeleccionado", proveedores);
							request.setAttribute("resultado_busqueda", "encontrado");
							request.getRequestDispatcher("buscar_proveedores.jsp").forward(request, response);
						}
					}
					request.setAttribute("resultado_busqueda", "no_encontrado");
					request.getRequestDispatcher("buscar_proveedores.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
        }
		/////productos
		if (menu.equals("Productos")) {
			if(accion.equals("Guardar")) {
				Productos producto = new Productos();
				producto.setCodigo_producto(Long.parseLong(request.getParameter("txtcodigo")));
				producto.setNombre_producto(request.getParameter("txtnombre"));
				producto.setNitproveedor(Long.parseLong(request.getParameter("txtnit")));
				producto.setPrecio_compra(Double.parseDouble(request.getParameter("txtprecio_compra")));
				producto.setIvacompra(Double.parseDouble(request.getParameter("txtiva_compra")));
				producto.setPrecio_venta(Double.parseDouble(request.getParameter("txtprecio_venta")));

				int respuesta=0;
				try {
					respuesta = Json.postJSON_productos(producto);
					if (respuesta==200) {
						request.setAttribute("respuesta", "ok_guardar_producto");
						try {
							ArrayList<Proveedores> lista = Json.getJSON_proveedores();
							request.setAttribute("lista", lista);
						} catch (Exception e) {
							e.printStackTrace();
						}
						request.getRequestDispatcher("crear_productos.jsp").forward(request, response);

					}
					else
					{
						System.out.println("Error: " +  respuesta);
						request.setAttribute("respuesta", "error_guardar_producto");
						try {
							ArrayList<Proveedores> lista = Json.getJSON_proveedores();
							request.setAttribute("lista", lista);
						} catch (Exception e) {
							e.printStackTrace();
						}
						request.getRequestDispatcher("crear_productos.jsp").forward(request, response);

					}
					System.out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(accion.equals("Eliminar")){
				Long id= Long.parseLong(request.getParameter("id"));
				int respuesta=0;
				try {
					respuesta = Json.deleteJSON_productos(id);
					PrintWriter write = response.getWriter();
					if (respuesta==200) {
						request.setAttribute("respuesta", "ok_eliminar_producto");
						request.getRequestDispatcher("controlador?menu=listar_productos").forward(request, response);
					}
					else {
						request.setAttribute("respuesta", "error_eliminar_producto");
						request.getRequestDispatcher("controlador?menu=listar_productos").forward(request, response);
					}
					//write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(accion.equals("Actualizar")) {
				Productos producto = new Productos();
				producto.setCodigo_producto(Long.parseLong(request.getParameter("txtcodigo")));
				producto.setNombre_producto(request.getParameter("txtnombre"));
				producto.setNitproveedor(Long.parseLong(request.getParameter("txtnit")));
				producto.setPrecio_compra(Double.parseDouble(request.getParameter("txtprecio_compra")));
				producto.setIvacompra(Double.parseDouble(request.getParameter("txtiva_compra")));
				producto.setPrecio_venta(Double.parseDouble(request.getParameter("txtprecio_venta")));

				int respuesta=0;
				try {
				respuesta = Json.putJSON_productos(producto,producto.getCodigo_producto());
				PrintWriter write = response.getWriter();

				if (respuesta==200) {
					request.setAttribute("respuesta", "ok_editar_producto");
					request.getRequestDispatcher("controlador?menu=listar_productos").forward(request, response);
				}
				else {
					request.setAttribute("respuesta", "error_editar_producto");
					request.getRequestDispatcher("controlador?menu=listar_productos").forward(request, response);
				}
				write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(accion.equals("Buscar")) {



				Long txtcodigo= Long.parseLong(request.getParameter("txtcodigo"));
				try {
					ArrayList<Productos> lista1 = Json.getJSON_productos();
					System.out.println("Parametro: " + txtcodigo);
					for (Productos productos:lista1){
						if (productos.getCodigo_producto()== txtcodigo) {
							request.setAttribute("productoSeleccionado", productos);
							request.setAttribute("resultado_busqueda", "encontrado");
							request.getRequestDispatcher("buscar_productos.jsp").forward(request, response);
						}
					}
					request.setAttribute("resultado_busqueda", "no_encontrado");
					request.getRequestDispatcher("buscar_productos.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
        }
		
		/////ventas
		if (menu.equals("Ventas")) {
				
				// ******************** enviaremos la cedula del usuario al formulario ventas
							request.setAttribute("usuarioSeleccionado", usuarios);
							// ***********************************************************************

							// ******************** enviaremos la Numero de Factura ******************
							request.setAttribute("numerofactura", numfac);
							// ***********************************************************************
							if (accion.equals("BuscarCliente")) {
								String id = request.getParameter("cedulacliente");// como esta en ventas
								this.buscarCliente(Long.parseLong(id), request, response);

							} else if (accion.equals("BuscarProducto")) {
								String id = request.getParameter("cedulacliente");// como esta en ventas y repite
								this.buscarCliente(Long.parseLong(id), request, response);

								String cod = request.getParameter("codigoproducto");// como esta en ventas
								this.buscarProducto(Long.parseLong(cod), request, response);

							} else if (accion.equals("AgregarProducto")) {
								String id = request.getParameter("cedulacliente");// como esta en ventas y lo repite
								this.buscarCliente(Long.parseLong(id), request, response);

								detalle_venta = new Detalle_ventas();
								item++; // contador
								acusubtotal = 0;
								subtotaliva = 0;
								totalapagar = 0;
								codProducto = Long.parseLong(request.getParameter("codigoproducto"));
								descripcion = request.getParameter("nombreproducto");
								precio = Double.parseDouble(request.getParameter("precioproducto"));
								cantidad = Integer.parseInt(request.getParameter("cantidadproducto"));
								iva = Double.parseDouble(request.getParameter("ivacompra"));

								subtotal = (precio * cantidad);
								valor_iva = subtotal * iva / 100;
								// almacena temporalmente cada producto
								detalle_venta.setCodigo_detalle_venta(String.valueOf(item));
								detalle_venta.setCodigo_producto(codProducto);
								detalle_venta.setDescripcion_producto(descripcion);
								detalle_venta.setPrecio_producto(precio);
								detalle_venta.setCantidad_producto(cantidad);
								detalle_venta.setCodigo_venta(numfac);
								detalle_venta.setValor_iva(valor_iva);
								detalle_venta.setValor_venta(subtotal);
								listaVentas.add(detalle_venta);

								for (int i = 0; i < listaVentas.size(); i++) {
									acusubtotal += listaVentas.get(i).getValor_venta();
									subtotaliva += listaVentas.get(i).getValor_iva();
								}
								totalapagar = acusubtotal + subtotaliva;
								detalle_venta.setValor_total(totalapagar);
								// una vez hecho todos los calculos ahora hacemos el envio de la info al
								// formulario ventas seccion2
								request.setAttribute("listaventas", listaVentas); //temporal
								request.setAttribute("totalsubtotal", acusubtotal);
								request.setAttribute("totaliva", subtotaliva);
								request.setAttribute("totalapagar", totalapagar);

							} else if (accion.equals("GenerarVenta")) {
								cedulaCliente = request.getParameter("cedulacliente");
								String numFact = request.getParameter("numerofactura");
								Ventas ventas = new Ventas();
								ventas.setCodigo_venta(Long.parseLong(numFact));
								ventas.setCedula_cliente(Long.parseLong(cedulaCliente));
								ventas.setCedula_usuario(usuarios.getCedula_usuario());
								ventas.setIvaventa(subtotaliva);
								ventas.setValor_venta(acusubtotal);
								ventas.setTotal_venta(totalapagar);

								int respuesta = 0;
								try {
									respuesta = Json.postJSON_ventas(ventas);
									PrintWriter write = response.getWriter();
									if (respuesta == 200) {
										System.out.println("Grabacion Exitosa " + respuesta);
										this.grabarDetalle(ventas.getCodigo_venta(), request, response);
									} else {
										write.println("error ventas");
									}
									write.close();
								} catch (Exception e) {
									e.printStackTrace();
								}

							} else {
								String factura = request.getParameter("numerofactura");
								if (factura == null) {
									factura = "1";
									this.buscarFactura(factura, request, response);
								}

							}
						}else if (accion.equals("NuevaVenta")) {
								

							}
							

							request.getRequestDispatcher("/ventas.jsp").forward(request, response);
		
		
		
		
        /////////////FIN ACCIONES

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
		System.out.println("Error: " +  menu + "Error: " +  accion);
        if (menu.equals("Principal")) {
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        }

	}
}
