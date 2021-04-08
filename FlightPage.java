package presentation;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessLayer.ManageFlight;
import dataAccess.Flight;

/**
 * Servlet implementation class FlightPage
 */
@WebServlet("/FlightPage")
public class FlightPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlightPage() {
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
		String flightId = request.getParameter("flightId");
		Flight flight = null;
		if(flightId != null) {
			flight = mf.findFlight(Integer.parseInt(flightId));
		}else {
			flight = new Flight();
		}
		String departureDate = dateFormat.format(flight.getDepartureDate());
		String arrivalDate = dateFormat.format(flight.getArrivalDate());
		PrintWriter out = response.getWriter();
		out.println(
			"<html>\n" +
				"<head><style>\n" + 
				"table, th, td {\n" + 
				"    border: 1px solid black;\n" + 
				"}\n" + 
				"</style><title>Flight information</title></head>\n" +
				"<body>\n" +
					"<form action='FlightPageRedirect' method='POST'>\n" +
					"<input type='hidden' name='flightId' value='" + flight.getIdflight() + "'\n /> <br/>\n" +
					"Flight number: <input type='text' name='number' value='" + flight.getNumber() + "'\n /> <br/>\n" +
					"Type: <input type='text' name='type' value='" + flight.getType() + "'\n /> <br/>\n" +
					"Departure city: <input type='text' name='departureCity' value='" + flight.getDepartureCity() + "'\n /> <br/>\n" +
					"Departure Date&Time: <input type='text' name='departureDate' value='" + departureDate +  "'\n /> <br/>\n" +
					"Arrival City: <input type='text' name='arrivalCity' value='" + flight.getArrivalCity() + "'\n /> <br/>\n" +
					"Arrival Date&Time: <input type='text' name='arrivalDate' value='" + arrivalDate + "'\n /> <br/>\n" +
					"<input type='hidden' name='action' value='insertOrUpdate' />" +
					"<input type='submit' value='Submit'\n /> <br/>\n" +
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
