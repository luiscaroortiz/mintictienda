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

        ///////MENU
		if (menu.equals("Principal")) {
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        }
        if (menu.equals("crear_usuarios")) {
            request.getRequestDispatcher("crear_usuarios.jsp").forward(request, response);
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
		///////FIN MENU

        ///////ACCIONES
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
        }
        ///////FIN ACCIONES

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
