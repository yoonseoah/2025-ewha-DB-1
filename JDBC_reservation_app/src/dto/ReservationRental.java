package dto;

public class ReservationRental {
	private int reservationId;
	private int itemId;
	
	public ReservationRental(int reservationId, int itemId) {
		this.reservationId = reservationId;
		this.itemId = itemId;
	}
	
	public int getReservationId() {
		return reservationId;
	}
	
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	
	public int getItemId() {
		return itemId;
	}
	
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

}
