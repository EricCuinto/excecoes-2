package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {

	private Integer number;
	private Date checkIn;
	private Date checkOut;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Reservation() {
	}

	public Reservation(int number, Date checkIn, Date checkOut) {
		if (!checkOut.after(checkIn)) {
			throw new DomainException("Data de Check-out deve ser depois da data de Check-in");
		}
		this.number = number;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public void updateDates(Date checkIn, Date checkOut) {

		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException("As datas de atualização da reserva devem ser futuras !!");
		}
		if (!checkOut.after(checkIn)) {
			throw new DomainException("Data de Check-out deve ser depois da data de Check-in");
		}

		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Quarto: " + getNumber());
		sb.append(", check-in: " + sdf.format(checkIn));
		sb.append(", check-out: " + sdf.format(checkOut));
		sb.append(", " + duration() + " noites");

		return sb.toString();
	}
}
