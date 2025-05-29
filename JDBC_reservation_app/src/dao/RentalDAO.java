package dao;

import dto.Rental;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentalDAO {
	private final Connection conn;
	
	public RentalDAO(Connection conn) {
		this.conn = conn;
	}
	
	public List<Rental> getAllRentals() {
		List<Rental> rentals = new ArrayList<>();
		String sql = "select * from Rental";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery()) {
			
			while (rs.next()) {
				Rental r = new Rental(
					rs.getInt("item_id"),
					rs.getString("item_name"),
					rs.getString("item_type")
				);
				rentals.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rentals;
	}

}
