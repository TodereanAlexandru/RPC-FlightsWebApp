package presentation;

import java.io.IOException;
import java.text.ParseException;
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
 * Servlet implementation class FlightPageRedirect
 */
@WebServlet("/FlightPageRedirect")
public class FlightPageRedirect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlightPageRedirect() {
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
		String action = request.getParameter("action");
		String flightIdString = request.getParameter("flightId");
		int flightId = 0;
		if(flightIdString != null) {
			flightId = Integer.parseInt(flightIdString);
		}
		if(action.equals("delete")) {
			mf.deleteFlight(flightId);
		}else {
			String number = request.getParameter("number");
			String type = request.getParameter("type");
			String departureCity = request.getParameter("departureCity");
			String departureDateString = request.getParameter("departureDate");
			String arrivalCity = request.getParameter("arrivalCity");
			String arrivalDateString = request.getParameter("arrivalDate");
			
			Date departureDate = null;
			Date arrivalDate = null;
			
			if(departureDateString != null) {
				try {
					departureDate = dateFormat.parse(departureDateString);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(arrivalDateString != null) {
				try {
					arrivalDate = dateFormat.parse(arrivalDateString);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(flightId == 0) {
				Flight flight = new Flight(number, type, departureCity, departureDate, arrivalCity, arrivalDate);
				mf.insertFlight(flight);
			}else {
				Flight flight = mf.findFlight(flightId);
				flight.setNumber(number);
				flight.setType(type);
				flight.setDepartureCity(departureCity);
				flight.setDepartureDate(departureDate);
				flight.setArrivalCity(arrivalCity);
				flight.setArrivalDate(arrivalDate);
				mf.updateFlight(flight);
			}
		}
		response.sendRedirect("AdminPage");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
