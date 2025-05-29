package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservationRentalDAO {
	private final Connection conn;
	
	public ReservationRentalDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void addReservationRental(int reservationId, int itemId) {
		String sql = "insert into Reservation_Rental (reservation_id, item_id) values (?, ?)";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, reservationId);
			pstmt.setInt(2, itemId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
