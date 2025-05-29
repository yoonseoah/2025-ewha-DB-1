package dao;

import dto.Background;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BackgroundDAO {
	private Connection conn;
	
	public BackgroundDAO(Connection conn) {
		this.conn = conn;
	}
	
	public List<Background> getAllBackgrounds() {
		List<Background> backgrounds = new ArrayList<>();
		String sql = "select * from Background";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery()) {
			
			while (rs.next()) {
				Background bg = new Background(
						rs.getInt("background_id"),
						rs.getString("color_name")
				);
				backgrounds.add(bg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return backgrounds;
	}

}
