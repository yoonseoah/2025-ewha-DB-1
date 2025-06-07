package dao;

import dto.Photographer;
import dto.PhotographerRank;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhotographerDAO {

	private Connection conn;

	public PhotographerDAO(Connection conn) {
		this.conn = conn;
	}

	public List<Photographer> getPhotographersByStudio(int studioId) {
		List<Photographer> photographers = new ArrayList<>();

		String sql = "SELECT " +
				"p.photographer_id, " +
				"p.studio_id, " +
				"p.name, " +
				"p.phone, " +
				"p.years, " +
				"(SELECT COUNT(*) " +
				" FROM Reservation r " +
				" WHERE r.photographer_id = p.photographer_id) AS reservation_count " +
				"FROM Photographer p " +
				"WHERE p.studio_id = ? " +
				"ORDER BY p.years DESC, reservation_count DESC";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, studioId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Photographer p = new Photographer(
						rs.getInt("photographer_id"),
						rs.getInt("studio_id"),
						rs.getString("name"),
						rs.getString("phone"),
						rs.getInt("years"),
						rs.getInt("reservation_count")
				);
				photographers.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return photographers;
	}

	public List<Photographer> getMyFavPhotographers(int userId) throws SQLException {
	        String sql = "select p.photographer_id, p.studio_id, p.name, p.phone, p.years, count(*) AS cnt " +
	                     "from Photographer p JOIN Reservation r ON p.photographer_id = r.photographer_id " +
	                     "where r.user_id = ? " +
	                     "group by p.photographer_id, p.studio_id, p.name, p.phone, p.years " +
	                     "having count(*) >= 3";
	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, userId);
	            try (ResultSet rs = pstmt.executeQuery()) {
	                List<Photographer> favorites = new ArrayList<>();
	                while (rs.next()) {
	                    Photographer photographer = new Photographer(
	                        rs.getInt("photographer_id"),
	                        rs.getInt("studio_id"),
	                        rs.getString("name"),
	                        rs.getString("phone"),
	                        rs.getInt("years"),
							rs.getInt("cnt")
	                    );
	                    favorites.add(photographer);
	                }
	                return favorites;
	            }
	        }
    	}

	public List<PhotographerRank> getPhotographerRanks() throws SQLException {
		String sql = "SELECT p.photographer_id, p.name, COUNT(r.reservation_id) AS reservation_count, " +
				"RANK() OVER (ORDER BY COUNT(r.reservation_id) DESC) AS rank_num " +
				"FROM Photographer p LEFT JOIN Reservation r ON p.photographer_id = r.photographer_id " +
				"GROUP BY p.photographer_id, p.name";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			List<PhotographerRank> ranks = new ArrayList<>();

			while (rs.next()) {
				PhotographerRank rank = new PhotographerRank();
				rank.setPhotographerId(rs.getInt("photographer_id"));
				rank.setName(rs.getString("name"));
				rank.setReservationCount(rs.getInt("reservation_count"));
				rank.setRank(rs.getInt("rank_num"));
				ranks.add(rank);
			}

			return ranks;
		}
	}
}
