package dao;

import dto.Studio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudioDAO {
	private Connection conn;
	
	public StudioDAO(Connection conn) {
		this.conn = conn;
	}
	
	public List<Studio> getAllStudios() throws SQLException {
		List<Studio> studios = new ArrayList<>();
		String sql = "select * from Studio";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				Studio studio = new Studio(
					rs.getInt("studio_id"),
					rs.getString("studio_name"),
			        rs.getString("location")				
				);
				studios.add(studio);
			}
		}
				
		return studios;	
	}

}
