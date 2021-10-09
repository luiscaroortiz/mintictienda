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
