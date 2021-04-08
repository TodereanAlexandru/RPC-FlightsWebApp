package presentation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessLayer.ManageFlight;
import dataAccess.Flight;

/**
 * Servlet implementation class UserPage
 */
@WebServlet("/ClientPage")
public class ClientPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginRole = (String)request.getSession().getAttribute("loginRole");
		if(loginRole == null || !loginRole.equals("client")) {
			response.sendRedirect("Login");
		}
		PrintWriter out = response.getWriter();
		ManageFlight mf = new ManageFlight();
		String city = request.getParameter("city");
		List<Flight> flights;
		if(city != null && city.length() > 0) {
			flights = mf.getFlightsByCity(city);
		}else {
			flights = mf.getFlights();
		}
		
		
		out.print(
			"<html>\n" +
				"<head><style>\n" + 
				"table, th, td {\n" + 
				"    border: 1px solid black;\n" + 
				"}\n" + 
				"</style><title>Client page</title></head>\n" +
				"<body>\n" +
				"<form action='ClientPage' method='POST'>\n" +
					"City: <input type='text' name='city'\n /> <br/>\n" +
					"<input type='submit' value='Search'\n /> <br/>\n" +
				"</form>\n" +
					"<table>\n" +
						"<tr>\n" +
							"<th>Flight nr.</th>\n" +
							"<th>Type</th>\n" +
							"<th>Departure city</th>\n" +
							"<th>Departure D&T</th>\n" +
							"<th>Arrival City</th>\n" +
							"<th>Arrival D&T</th>\n" +
						"</tr>\n"
		);
		for(Flight fl: flights) {
			out.print(
					"<tr>" +
						"<td>" + fl.getNumber() + "</td>\n" +
						"<td>" + fl.getType() + "</td>\n" +
						"<td>" + fl.getDepartureCity() + "</td>\n" +
						"<td>" + fl.getDepartureDate() + "</td>\n" +
						"<td>" + fl.getArrivalCity() + "</td>\n" +
						"<td>" + fl.getArrivalDate() + "</td>\n" +
					"</tr>"
			);
		}
		out.print(
					"</table>\n" +
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
