package presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessLayer.ManageUser;
import dataAccess.User;

/**
 * Servlet implementation class LoginRedirect
 */
@WebServlet("/LoginRedirect")
public class LoginRedirect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginRedirect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		ManageUser me = new ManageUser();
		User usr = me.findUser(username, password);
		if(usr == null) {
			request.getSession().setAttribute("failed", "true");
			response.sendRedirect("Login");
		}else {
			String role = usr.getRole();
			if(role.equals("admin")) {
				request.getSession().setAttribute("loginRole", "admin");
				response.sendRedirect("AdminPage");
			}else {
				request.getSession().setAttribute("loginRole", "client");
				response.sendRedirect("ClientPage");
			}
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
