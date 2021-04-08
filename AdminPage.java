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
 * Servlet implementation class AdminPage
 */
@WebServlet("/AdminPage")
public class AdminPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginRole = (String)request.getSession().getAttribute("loginRole");
		if(loginRole == null || !loginRole.equals("admin")) {
			response.sendRedirect("Login");
		}
		
		ManageFlight mf = new ManageFlight();
		List<Flight> flights = mf.getFlights();
		
		PrintWriter out = response.getWriter();
		out.print(
			"<html>\n" +
				"<head><style>\n" + 
				"table, th, td {\n" + 
				"    border: 1px solid black;\n" + 
				"}\n" + 
				"</style><title>Admin page</title></head>\n" +
				"<body>\n" +
					"<table>\n" +
						"<tr>\n" +
							"<th>Flight nr.</th>\n" +
							"<th>Type</th>\n" +
							"<th>Departure city</th>\n" +
							"<th>Departure D&T</th>\n" +
							"<th>Arrival City</th>\n" +
							"<th>Arrival D&T</th>\n" +
							"<th></th>\n" +
							"<th></th>\n" +
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
						"<td><form action='FlightPage' method='POST'>" +
							"<input type='hidden' name='flightId' value='" + fl.getIdflight() + "' />" +
							"<input type='submit' value='Edit'\n />" +
						"</form></td>\n" +
						"<td><form action='FlightPageRedirect' method='POST'>" +
							"<input type='hidden' name='flightId' value='" + fl.getIdflight() + "' />" +
							"<input type='hidden' name='action' value='delete' />" +
							"<input type='submit' value='Delete'\n />" +
						"</form></td>\n" +
					"</tr>"
			);
		}
		out.print(
					"</table>\n" +
					"<form action='FlightPage' method='POST'><input type='submit' value='Add flight'\\n /></form>" +
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
