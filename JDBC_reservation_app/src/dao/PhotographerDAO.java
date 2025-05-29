package dao;

import dto.Photographer;
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
		
		String sql = """
				select 
					p.photographer_id,
					p.studio_id,
					p.name,
					p.phone,
					p.years,
					(select count(*)
					 from Reservation r 
					 where r.photographer_id = p.photographer_id) as reservation_count
				from Photographer p
				where p.studio_id = ?
				order by p.years desc, reservation_count desc
				""";
		
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

}
