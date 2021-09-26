package controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String accion = request.getParameter("accion");
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


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
