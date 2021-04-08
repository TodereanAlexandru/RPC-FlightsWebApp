package presentation;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String failed = (String) request.getSession().getAttribute("failed");
		String failMsg = "";
		if(failed != null && failed.equals("true")) {
			failMsg = "Login failed! <br/>\n";
		}
		PrintWriter out = response.getWriter();
		out.println(
			"<html>\n" +
				"<head><style>\n" + 
				"table, th, td {\n" + 
				"    border: 1px solid black;\n" + 
				"}\n" + 
				"</style><title>Login page</title></head>\n" +
				"<body>\n" +
					failMsg +
					"<form action='LoginRedirect' method='POST'>\n" +
						"Username: <input type='text' name='username'\n /> <br/>\n" +
						"Password: <input type='password' name='password'\n /> <br/>\n" +
						"<input type='submit' value='Login'\n /> <br/>\n" +
					"</form>\n" +
				"</body>\n" + 
			"</html>"
		);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
