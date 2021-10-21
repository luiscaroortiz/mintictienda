package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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


    public controlador() {
        super();
        // TODO Auto-generated constructor stub
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
            request.getRequestDispatcher("crear_ventas.jsp").forward(request, response);
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
