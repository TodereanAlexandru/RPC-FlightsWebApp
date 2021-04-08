package dataAccess;

import java.util.Date;

public class Flight {
	private int idflight;
	private String number;
	private String type;
	private String departureCity;
	private Date departureDate;
	private String arrivalCity;
	private Date arrivalDate;
	
	public int getIdflight() {
		return idflight;
	}
	public void setIdflight(int idflight) {
		this.idflight = idflight;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDepartureCity() {
		return departureCity;
	}
	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	public String getArrivalCity() {
		return arrivalCity;
	}
	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	public Flight() {
		this("", "T1", "City1", new Date(), "City2", new Date());
	}
	
	public Flight(String number, String type, String departureCity, Date departureDate, String arrivalCity,
			Date arrivalDate) {
		super();
		this.number = number;
		this.type = type;
		this.departureCity = departureCity;
		this.departureDate = departureDate;
		this.arrivalCity = arrivalCity;
		this.arrivalDate = arrivalDate;
	}
	
	
	
}
