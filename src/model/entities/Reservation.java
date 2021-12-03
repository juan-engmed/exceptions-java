package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	//Conversão de modelos de data
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation() {
		
	}
	
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainException {
		
		if(!checkOut.after(checkIn)) {
			throw new DomainException("Error, dates for update must be in future");
		}
		
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}



	public Integer getRoomNumber() {
		return roomNumber;
	}



	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}



	public Date getCheckIn() {
		return checkIn;
	}



	public Date getCheckOut() {
		return checkOut;
	}

	//Métodos
	public long duration() {
		//Java recebe a diferença em MS e usamos o TimeUnit para conversão
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public void updateDates(Date checkIn, Date checkOut) throws DomainException {
		
		Date now = new Date();
		if(checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException("Error, dates for update must be in future");
		}
		
		if(!checkOut.after(checkIn)) {
			throw new DomainException("Error, dates for update must be in future");
		}
		
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	//Retorno toString -> Sempre usar Override, pois é um caso de Sobreposição
	@Override
	public String toString() {
		return "Room "
				+ roomNumber
				+ ", check-in"
				+ sdf.format(checkIn)
				+ ", check-out"
				+ sdf.format(checkOut)
				+ ", "
				+ duration()
				+ " nights";
	}

}
