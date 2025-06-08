package dao;

import dto.Studio;
import dto.StudioStatistics;

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

    public List<StudioStatistics> getStudioSummary(Connection conn) throws SQLException {
        String sql = "SELECT studio_id, COUNT(*) AS total_reservations " +
                "FROM Reservation GROUP BY ROLLUP(studio_id)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            List<StudioStatistics> stats = new ArrayList<>();
            while (rs.next()) {
                StudioStatistics stat = new StudioStatistics();
                stat.setStudioId(rs.getInt("studio_id"));
                stat.setTotalReservations(rs.getInt("total_reservations"));
                stats.add(stat);
            }
            return stats;
        }
    }

}