package controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.Json;
import controladores.Usuarios;

/**
 * Servlet implementation class validar_servlet
 */
@WebServlet("/validar_servlet")
public class validar_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public validar_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void validarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ArrayList<Usuarios> lista = Json.getJSON();
			request.setAttribute("lista", lista);
			String usua = request.getParameter("txtusuario");
			String pass = request.getParameter("txtpassword");
			int respuesta =0;
			for (Usuarios usuario:lista){
				if (usuario.getUsuario().equals(usua) && usuario.getPassword().equals(pass)) {
					request.setAttribute("usuario", usuario);
					request.getRequestDispatcher("controlador?menu=Principal").forward(request, response);
					respuesta =1;
				}

			}

			if (respuesta==0) {
				request.setAttribute("error", "error_login");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				System.out.println("No se encontraron datos");
			}

		} catch (Exception e) {
				// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


/*		String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("Ingresar")) {

			String usua = request.getParameter("txtusuario");
			String pass = request.getParameter("txtpassword");
			if (usua.equals("admin") && pass.equals("admin")) {
				request.setAttribute("usuario", "admin");
				//request.getRequestDispatcher("menu.jsp").forward(request, response);
				request.getRequestDispatcher("controlador?menu=Principal").forward(request, response);
			}
			else
			{
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
		else
		{
			request.getRequestDispatcher("index.jsp").forward(request, response);

		}
	*/

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String accion = request.getParameter("accion");

		if (accion.equals("Ingresar")) {
			this.validarUsuarios(request, response);
		}
		else
		{
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
